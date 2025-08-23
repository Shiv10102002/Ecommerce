package com.shiv.ecommerce.domain.repo

import android.net.Uri
import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.BannerdataModels
import com.shiv.ecommerce.domain.models.CartDataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.models.ProductsDataModels
import com.shiv.ecommerce.domain.models.UserData
import com.shiv.ecommerce.domain.models.UserDataParent
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun loginUserWithEmailAndPassword(email: String, password: String): Flow<ResultState<String>>
    fun getUserById(userId: String): Flow<ResultState<UserDataParent>>
    fun updateUserData(userDataParent: UserDataParent): Flow<ResultState<String>>
    fun updateUserProfileImage(uri:Uri):Flow<ResultState<String>>
    fun getCategoriesInLimited():Flow<ResultState<List<CategoryDataModels>>>
    fun getProductsInLimit():Flow<ResultState<List<ProductsDataModels>>>
    fun getAllProducts():Flow<ResultState<List<ProductsDataModels>>>
    fun getProductsByCategory(category:String):Flow<ResultState<List<ProductsDataModels>>>
    fun getProductById(productId:String):Flow<ResultState<ProductsDataModels>>
    fun addToFav(productDataModels: ProductsDataModels):Flow<ResultState<String>>
    fun addToCart(cartDataModels: CartDataModels):Flow<ResultState<String>>
    fun getAllFav():Flow<ResultState<List<ProductsDataModels>>>
    fun getCart():Flow<ResultState<List<CartDataModels>>>
//    fun deleteFromFav(productId: String):Flow<ResultState<String>>
//    fun deleteFromCart(productId: String):Flow<ResultState<String>>
    fun getFavCount():Flow<ResultState<Int>>
    fun getCartCount():Flow<ResultState<Int>>
    fun getAllCategories():Flow<ResultState<List<CategoryDataModels>>>
    fun getCheckOut(productId:String):Flow<ResultState<ProductsDataModels>>
    fun getBanner():Flow<ResultState<List<BannerdataModels>>>
    fun getAllSuggestedProducts():Flow<ResultState<List<ProductsDataModels>>>


}