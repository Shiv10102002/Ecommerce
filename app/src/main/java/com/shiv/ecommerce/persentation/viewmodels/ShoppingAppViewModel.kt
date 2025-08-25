package com.shiv.ecommerce.persentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shiv.ecommerce.common.HomeScreenState
import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.CartDataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.models.ProductsDataModels
import com.shiv.ecommerce.domain.models.UserData


import com.shiv.ecommerce.domain.models.UserDataParent
import com.shiv.ecommerce.domain.useCase.AddToCartUseCase
import com.shiv.ecommerce.domain.useCase.AddToFavUseCase
import com.shiv.ecommerce.domain.useCase.CreateUserUseCase
import com.shiv.ecommerce.domain.useCase.GetAllCategoryUseCase
import com.shiv.ecommerce.domain.useCase.GetAllFavUseCase
import com.shiv.ecommerce.domain.useCase.GetAllProductUseCase
import com.shiv.ecommerce.domain.useCase.GetAllSuggestedProductsUseCase
import com.shiv.ecommerce.domain.useCase.GetBannerUseCase
import com.shiv.ecommerce.domain.useCase.GetCartUseCase
import com.shiv.ecommerce.domain.useCase.GetCategoryInLimitUseCase
import com.shiv.ecommerce.domain.useCase.GetCheckoutUseCase
import com.shiv.ecommerce.domain.useCase.GetProductByCategoryUseCase
import com.shiv.ecommerce.domain.useCase.GetProductByIdUseCase
import com.shiv.ecommerce.domain.useCase.GetProductsInLimitUseCase
import com.shiv.ecommerce.domain.useCase.GetUserUseCase
import com.shiv.ecommerce.domain.useCase.LoginUserUseCase
import com.shiv.ecommerce.domain.useCase.UpdateUserDataUseCase
import com.shiv.ecommerce.domain.useCase.UpdateUserProfileImageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


class ShoppingAppViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val loginUseCase: LoginUserUseCase,
    private val getUserByIdUseCase: GetUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val updateUserProfileImageUseCase: UpdateUserProfileImageUseCase,
    private val getCategoryInLimitUseCase: GetCategoryInLimitUseCase,
    private val getProductsInLimitUseCase: GetProductsInLimitUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToFavUseCase: AddToFavUseCase,
    private val getAllFavUseCase: GetAllFavUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getCheckoutUseCase: GetCheckoutUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getProductByCategoryUseCase: GetProductByCategoryUseCase,
    private val getAllSuggestedProductsUseCase: GetAllSuggestedProductsUseCase,
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getCartUseCase: GetCartUseCase,

    ):ViewModel() {

    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreeState = _profileScreenState.asStateFlow()

    private val _updateUserScreenState = MutableStateFlow(UpdateUserState())
    val updateUserState = _updateUserScreenState.asStateFlow()

    private val _updateProfileImageState = MutableStateFlow(UpdateProfileImageState())
    val updateProfileImageState = _updateProfileImageState.asStateFlow()

    private val _addToCartState = MutableStateFlow(AddToCartState())
    val addToCartState = _addToCartState.asStateFlow()

    private val _getProductByIdState = MutableStateFlow(GetProductByIdState())
    val getProductByIdState = _getProductByIdState.asStateFlow()

    private val _addToFavState = MutableStateFlow(AddToFavState())
    val addToFavState = _addToFavState.asStateFlow()

    private val _getAllFavState = MutableStateFlow(GetAllFavState())
    val getAllFavState = _getAllFavState.asStateFlow()

    private val _getAllProductState = MutableStateFlow(GetAllProductState())
    val getAllProductState = _getAllProductState.asStateFlow()

    private val _getCartState = MutableStateFlow(GetCartState())
    val getCartState = _getCartState.asStateFlow()

    private val _getAllCategoriesState = MutableStateFlow(GetAllCategoriesState())
    val getAllCategoriesState = _getAllCategoriesState.asStateFlow()

    private val _getCheckOutState = MutableStateFlow(GetCheckOutState())
    val getCheckOutState = _getCheckOutState.asStateFlow()

    private val _getProductsByCategoryState = MutableStateFlow(GetProductsByCategoryState())
    val getProductsByCategoryState = _getProductsByCategoryState.asStateFlow()

    private val _getAllSuggestedProductsState = MutableStateFlow(GetAllSuggestedProductsState())
    val getAllSuggestedProductsState = _getAllSuggestedProductsState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun getProductByCategory(categoryName: String) {
        viewModelScope.launch {
            getProductByCategoryUseCase.getProductByCategory(categoryName).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getProductsByCategoryState.value = _getProductsByCategoryState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getProductsByCategoryState.value = _getProductsByCategoryState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getProductsByCategoryState.value = _getProductsByCategoryState.value.copy(
                            isLoading = true
                        )

                    }
                }

            }
        }
    }

    fun getCheckOut(productId: String) {
        viewModelScope.launch {
            getCheckoutUseCase.getCheckOut(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }


        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            getAllCategoryUseCase.getAllCategory().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = true
                        )
                    }
                }

            }

        }
    }

    fun getCart() {
        viewModelScope.launch {
            getCartUseCase.getCart().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductUseCase.getAllProduct().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllProductState.value = _getAllProductState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getAllProductState.value = _getAllProductState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllProductState.value = _getAllProductState.value.copy(
                            isLoading = true
                        )
                    }
                }

            }
        }
    }

    fun getAllFav() {
        viewModelScope.launch {
            getAllFavUseCase.getAllFav().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }

    }

    fun addToFav(productDataModels: ProductsDataModels) {
        viewModelScope.launch {
            addToFavUseCase.addToFav(productDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun getProductById(productId: String) {
        viewModelScope.launch {
            getProductByIdUseCase.getProductById(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }

    }

    fun addToCart(cartDataModels: CartDataModels) {
        viewModelScope.launch {
            addToCartUseCase.addToCart(cartDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = true
                        )
                    }

                }
            }

        }
    }

    init {
        loadHomeScreenData()
    }

    private fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                getCategoryInLimitUseCase.getCategoryInLimit(),
                getProductsInLimitUseCase.getProductsInLimit(),
                getBannerUseCase.getBanner()

            ) { categoriesResult, productsResult, bannerResult ->
                when {
                    categoriesResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = categoriesResult.message)
                    }

                    productsResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = productsResult.message)
                    }

                    bannerResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = bannerResult.message)

                    }

                    categoriesResult is ResultState.Success && productsResult is ResultState.Success && bannerResult is ResultState.Success -> {
                        HomeScreenState(
                            isLoading = false,
                            categories = categoriesResult.data,
                            products = productsResult.data,
                            banner = bannerResult.data
                        )
                    }


                    else -> {
                        HomeScreenState(isLoading = true)
                    }
                }

            }.collect { state ->
                _homeScreenState.value = state

            }
        }
    }

    fun updateProfileImage(uri: Uri) {
        viewModelScope.launch {
            updateUserProfileImageUseCase.updateUserProfileImage(uri).collect {
                when (it) {
                    is ResultState.Error -> {
                        _updateProfileImageState.value = _updateProfileImageState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _updateProfileImageState.value = _updateProfileImageState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _updateProfileImageState.value = _updateProfileImageState.value.copy(
                            isLoading = true
                        )
                    }

                }
            }
        }

    }

    fun updateUserData(userDataParent: UserDataParent) {
        viewModelScope.launch {
            updateUserDataUseCase.updateUserData(userDataParent).collect {
                when (it) {
                    is ResultState.Error -> {
                        _updateUserScreenState.value = _updateUserScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Success -> {
                        _updateUserScreenState.value = _updateUserScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _updateUserScreenState.value = _updateUserScreenState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun createUser(userData:UserData) {
        viewModelScope.launch {
            createUserUseCase.createUser(userData).collect{
                when(it){
                    is ResultState.Error -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )

                    }
                    is ResultState.Success -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                    is ResultState.Loading -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = true
                        )
                    }

                }

            }
        }

    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.loginUserWithEmailAndPassword(email, password).collect{
                when(it){
                    is ResultState.Error -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Success -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                    is ResultState.Loading -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }

                }

            }

        }

    }

    fun getUserById(userId: String) {
        viewModelScope.launch {
            getUserByIdUseCase.getUserById(userId).collect{
                when(it){
                    is ResultState.Error -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                    is ResultState.Loading -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }

    }

    fun getAllSuggestedProducts() {
        viewModelScope.launch {
            getAllSuggestedProductsUseCase.getAllSuggestedProducts().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                    }

                    is ResultState.Success -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }

                    is ResultState.Loading -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = true
                            )
                    }
                }

            }

        }
    }



}
    data class ProfileScreenState(
        val isLoading: Boolean = false,
        val userData: UserDataParent? = null,
        val errorMessage: String? = null
    )

    data class SignUpScreenState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class LoginScreenState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class UpdateUserState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class UpdateProfileImageState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class CategoryScreenState(
        val isLoading: Boolean = false,
        val categories: List<CategoryDataModels> = emptyList(),
        val errorMessage: String? = null
    )

    data class AddToCartState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class GetProductByIdState(
        val isLoading: Boolean = false,
        val userData: ProductsDataModels? = null,
        val errorMessage: String? = null
    )

    data class AddToFavState(
        val isLoading: Boolean = false,
        val userData: String? = null,
        val errorMessage: String? = null
    )

    data class GetAllFavState(
        val isLoading: Boolean = false,
        val userData: List<ProductsDataModels?> = emptyList(),
        val errorMessage: String? = null
    )

    data class GetAllProductState(
        val isLoading: Boolean = false,
        val userData: List<ProductsDataModels?> = emptyList(),
        val errorMessage: String? = null
    )

    data class GetCartState(
        val isLoading: Boolean = false,
        val userData: List<CartDataModels?> = emptyList(),
        val errorMessage: String? = null
    )

    data class GetCartCountState(
        val isLoading: Boolean = false,
        val userData: Int? = 0,
        val errorMessage: String? = null
    )

    data class GetFavCountState(
        val isLoading: Boolean = false,
        val userData: Int? = 0,
        val errorMessage: String? = null
    )

    data class GetAllCategoriesState(
        val isLoading: Boolean = false,
        val userData: List<CategoryDataModels?> = emptyList(),
        val errorMessage: String? = null
    )

    data class GetCheckOutState(
        val isLoading: Boolean = false,
        val userData: ProductsDataModels? = null,
        val errorMessage: String? = null
    )

    data class GetProductsByCategoryState(
        val isLoading: Boolean = false,
        val userData: List<ProductsDataModels?> = emptyList(),
        val errorMessage: String? = null
    )

    data class GetAllSuggestedProductsState(
        val isLoading: Boolean = false,
        val userData: List<ProductsDataModels?> = emptyList(),
        val errorMessage: String? = null
    )




