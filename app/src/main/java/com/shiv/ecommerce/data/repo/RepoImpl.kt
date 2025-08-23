package com.shiv.ecommerce.data.repo

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shiv.ecommerce.common.ADD_TO_CART
import com.shiv.ecommerce.common.ADD_TO_FAV
import com.shiv.ecommerce.common.BANNER_COLLECTION
import com.shiv.ecommerce.common.CATEGORY_COLLECTION
import com.shiv.ecommerce.common.PRODUCT_COLLECTION
import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.common.USER_CART
import com.shiv.ecommerce.common.USER_COLLECTION
import com.shiv.ecommerce.common.USER_FAV
import com.shiv.ecommerce.domain.models.BannerdataModels
import com.shiv.ecommerce.domain.models.CartDataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.models.ProductsDataModels
import com.shiv.ecommerce.domain.models.UserData
import com.shiv.ecommerce.domain.models.UserDataParent
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(
     var firebaseAuth: FirebaseAuth,
     var firebaseFirestore: FirebaseFirestore
)  : Repo {
    override fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val userId = it.result.user?.uid
                    firebaseFirestore.collection(USER_COLLECTION).document(userId.toString()).set(userData)
                        .addOnCompleteListener{status->
                            if(status.isSuccessful){
                                trySend(ResultState.Success("User Registered Successfully"))
                            }else{
                                trySend(ResultState.Error(status.exception?.localizedMessage.toString()))
                            }
                        }

                }else{
                    if(it.exception != null){
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }

                }
            }
        awaitClose{
            close()
        }
    }

    override fun loginUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

       firebaseAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener {
               if(it.isSuccessful){
                   trySend(ResultState.Success("User Logged In Successfully"))
               }else{
                   trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
               }
           }

        awaitClose{
            close()
        }

    }

    override fun getUserById(userId: String): Flow<ResultState<UserDataParent>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(USER_COLLECTION).document(userId).get().addOnCompleteListener {
            if(it.isSuccessful){
                val data = it.result.toObject(UserData::class.java)
                if(data != null){
                    trySend(ResultState.Success(UserDataParent(it.result.id,data)))
                }else{
                    trySend(ResultState.Error("User Data is Empty"))
                }
            }else{
                trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
            }
        }
        awaitClose{
            close()
        }
    }

    override fun updateUserData(userDataParent: UserDataParent): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)
        firebaseFirestore.collection(USER_COLLECTION).document(userDataParent.nodeId).update(userDataParent.userData.toMap())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    trySend(ResultState.Success("User Data Updated Successfully"))
                }else{
                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                }
            }
        awaitClose{
            close()
        }
    }

    override fun updateUserProfileImage(uri: Uri): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)
        FirebaseStorage.getInstance().reference.child("userProfileImage/${System.currentTimeMillis()}+ ${firebaseAuth.currentUser?.uid}").putFile(uri?:Uri.EMPTY).addOnCompleteListener {uploadImageStatus->
            if(uploadImageStatus.isSuccessful){
                uploadImageStatus.result.storage.downloadUrl.addOnSuccessListener {downloadUri->
                    firebaseFirestore.collection(USER_COLLECTION).document(firebaseAuth.currentUser?.uid.toString()).update("profileImage",downloadUri.toString()).addOnCompleteListener {updateStatus->
                        if(updateStatus.isSuccessful){
                            trySend(ResultState.Success("Profile Image Updated Successfully"))
                        }else{
                            trySend(ResultState.Error(updateStatus.exception?.localizedMessage.toString()))
                        }
                    }
                }
            }
            if(uploadImageStatus.exception != null){
                trySend(ResultState.Error(uploadImageStatus.exception?.localizedMessage.toString()))
            }
        }
        awaitClose{
            close()
        }

    }

    override fun getCategoriesInLimited(): Flow<ResultState<List<CategoryDataModels>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(CATEGORY_COLLECTION).limit(7).get()
            .addOnSuccessListener { querySnapShot->
                val categories = querySnapShot.documents.mapNotNull { document->
                    document.toObject(CategoryDataModels::class.java)
                    }
                trySend(ResultState.Success(categories))
            }.addOnFailureListener {exception->
                trySend(ResultState.Error(exception.toString()))
            }
            awaitClose{
                close()
            }


    }

    override fun getProductsInLimit(): Flow<ResultState<List<ProductsDataModels>>> = callbackFlow {

        trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT_COLLECTION).limit(10).get().addOnSuccessListener {
            val products = it.documents.mapNotNull { document->
                document.toObject(ProductsDataModels::class.java)
            }
            trySend(ResultState.Success(products))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }

    }

    override fun getAllProducts(): Flow<ResultState<List<ProductsDataModels>>> = callbackFlow{
       trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT_COLLECTION).get().addOnSuccessListener {
            val products = it.documents.mapNotNull { document->
                document.toObject(ProductsDataModels::class.java)?.apply {
                    productId = document.id
                }
            }
            trySend(ResultState.Success(products))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

    override fun getProductsByCategory(category: String): Flow<ResultState<List<ProductsDataModels>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT_COLLECTION).whereEqualTo("category",category).get().addOnSuccessListener {
            val products = it.documents.mapNotNull { document->
                document.toObject(ProductsDataModels::class.java)?.apply {
                    productId = document.id
                }
            }
            trySend(ResultState.Success(products))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }


    override fun getProductById(productId: String): Flow<ResultState<ProductsDataModels>> = callbackFlow{

        trySend(ResultState.Loading)

        firebaseFirestore.collection(PRODUCT_COLLECTION).document(productId).get().addOnSuccessListener {
            val product = it.toObject(ProductsDataModels::class.java)
            if(product != null){
                trySend(ResultState.Success(product))
            }else{
                trySend(ResultState.Error("Product Data is Empty"))
            }
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

    override fun addToFav(productDataModels: ProductsDataModels): Flow<ResultState<String>> = callbackFlow{
       trySend(ResultState.Loading)
        firebaseFirestore.collection(ADD_TO_FAV).document(firebaseAuth.currentUser?.uid.toString()).collection(
            USER_FAV).add(productDataModels).addOnSuccessListener {
                trySend(ResultState.Success("Product Added To Fav"))
            }
            .addOnFailureListener {exception->
                trySend(ResultState.Error(exception.toString()))
            }
        awaitClose{
            close()
        }
    }

    override fun addToCart(cartDataModels: CartDataModels): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(ADD_TO_CART).document(firebaseAuth.currentUser?.uid.toString()).collection(
            USER_CART).add(cartDataModels).addOnSuccessListener {
                trySend(ResultState.Success("Product Added To Cart"))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }

    }

    override fun getAllFav(): Flow<ResultState<List<ProductsDataModels>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(ADD_TO_FAV).document(firebaseAuth.currentUser?.uid.toString()).collection(
            USER_FAV).get().addOnSuccessListener {
               val fav = it.documents.mapNotNull { document->
                   document.toObject(ProductsDataModels::class.java)?.apply {
                       productId = document.id
                   }
               }
            trySend(ResultState.Success(fav))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

    override fun getCart(): Flow<ResultState<List<CartDataModels>>> = callbackFlow {

        trySend(ResultState.Loading)
        firebaseFirestore.collection(ADD_TO_CART).document(firebaseAuth.currentUser?.uid.toString()).collection(
            USER_CART).get().addOnSuccessListener {
            val cart = it.documents.mapNotNull { document ->
                document.toObject(CartDataModels::class.java)?.apply {
                    cartId = document.id
                }
            }
            trySend(ResultState.Success(cart))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

//    override fun deleteFromFav(productId: String): Flow<ResultState<String>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteFromCart(productId: String): Flow<ResultState<String>> {
//        TODO("Not yet implemented")
//    }

    override fun getFavCount(): Flow<ResultState<Int>> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseFirestore.collection(ADD_TO_FAV).document(firebaseAuth.currentUser?.uid.toString()).collection(USER_FAV ).get()
            .addOnSuccessListener {
                trySend(ResultState.Success(it.documents.size))
            }.addOnFailureListener {exception->
                trySend(ResultState.Error(exception.toString()))
            }
            awaitClose{
                close()
            }

    }

    override fun getCartCount(): Flow<ResultState<Int>> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseFirestore.collection(ADD_TO_CART).document(firebaseAuth.currentUser?.uid.toString()).collection(USER_CART).get()
            .addOnSuccessListener {
                trySend(ResultState.Success(it.documents.size))
            }.addOnFailureListener {exception->
                trySend(ResultState.Error(exception.toString()))
            }
            awaitClose{
                close()
            }
    }

    override fun getAllCategories(): Flow<ResultState<List<CategoryDataModels>>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseFirestore.collection(CATEGORY_COLLECTION).get().addOnSuccessListener {
            val categories = it.documents.mapNotNull { document->
                document.toObject(CategoryDataModels::class.java)
            }
            trySend(ResultState.Success(categories))
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

    override fun getCheckOut(productId: String): Flow<ResultState<ProductsDataModels>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT_COLLECTION).document(productId).get().addOnSuccessListener {
            val product = it.toObject(ProductsDataModels::class.java)
            if(product != null){
                trySend(ResultState.Success(product))
            }else{
                trySend(ResultState.Error("Product Data is Empty"))
            }
        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))
        }
        awaitClose{
            close()
        }
    }

    override fun getBanner(): Flow<ResultState<List<BannerdataModels>>> = callbackFlow {
        trySend(ResultState.Loading)

       firebaseFirestore.collection(BANNER_COLLECTION).get().addOnSuccessListener {
           val banner = it.documents.mapNotNull { document->
               document.toObject(BannerdataModels::class.java)
           }
           trySend(ResultState.Success(banner))
       }.addOnFailureListener {exception->
           trySend(ResultState.Error(exception.toString()))
       }
       awaitClose{
           close()
       }
    }

    override fun getAllSuggestedProducts(): Flow<ResultState<List<ProductsDataModels>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(ADD_TO_FAV).document(firebaseAuth.currentUser?.uid.toString())
            .collection(USER_FAV)
            .get().addOnSuccessListener {
                val fav = it.documents.mapNotNull { document->
                    document.toObject(ProductsDataModels::class.java)?.apply {
                        productId = document.id
                    }

                }
                trySend(ResultState.Success(fav))
            }.addOnFailureListener {exception->
                trySend(ResultState.Error(exception.toString()))
            }
            awaitClose{
                close()
            }

    }

}