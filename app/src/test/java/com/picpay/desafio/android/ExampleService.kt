package com.picpay.desafio.android

import com.picpay.desafio.android.contacts.data.PicPayService
import com.picpay.desafio.android.contacts.data.remote.dto.UserResponse

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<UserResponse> {
        val users = service.getUsers()

        return users
    }
}
