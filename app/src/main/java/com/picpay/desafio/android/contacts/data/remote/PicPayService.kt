package com.picpay.desafio.android.contacts.data.remote

import com.picpay.desafio.android.contacts.data.remote.dto.UserResponse
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
