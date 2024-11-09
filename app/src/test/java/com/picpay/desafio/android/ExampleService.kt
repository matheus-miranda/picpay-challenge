package com.picpay.desafio.android

import com.picpay.desafio.android.contacts.data.PicPayService
import com.picpay.desafio.android.contacts.data.UserResponse

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}
