package com.example.chatapplive.modals

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id") val _id: String,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("img") val img: String,
    @SerializedName("friends") val friends: List<String>,
    @SerializedName("posts") val posts: List<String>,
    @SerializedName("requests") val requests: List<String>,
    @SerializedName("requested") val requested: List<String>,
    @SerializedName("chats") val chats: List<String>,
    @SerializedName("date") val date: String,
    @SerializedName("isAdmin") val isAdmin: Boolean
)

//data class GoogleAccount(
//    val isgoogle: Boolean,
//    val googlename: String?
//)

data class LoginPayload(val username: String, val password: String)

data class OtpPayload(val otp: String, val useremail: String)

data class ErrorResponse(val message: String, val code: Int)

data class SuccessResponse(val message: String, val code: Int)