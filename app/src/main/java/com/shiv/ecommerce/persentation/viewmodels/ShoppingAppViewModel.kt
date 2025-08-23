package com.shiv.ecommerce.persentation.viewmodels

import com.shiv.ecommerce.domain.models.CartDataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.models.ProductsDataModels


import com.shiv.ecommerce.domain.models.UserDataParent
import javax.inject.Inject


class ShoppingAppViewModel @Inject constructor(

){

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



