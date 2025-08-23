package com.shiv.ecommerce.domain.models

import androidx.compose.runtime.mutableStateMapOf

data class UserData(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val profileImage: String = "",
    val phoneNumber: String = "",
    val address: String = "",
){
    fun toMap(): Map<String, Any> {
        val map = mutableStateMapOf<String,Any>()
        map["firstName"] = firstName
        map["lastName"] = lastName
        map["email"] = email
        map["password"] = password
        map["profileImage"] = profileImage
        map["phoneNumber"] = phoneNumber
        map["address"] = address
        return map

    }

}

data class UserDataParent(val nodeId:String = "",val userData: UserData = UserData())
