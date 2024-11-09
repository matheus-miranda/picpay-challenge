package com.picpay.desafio.android.contacts.data.repository

import com.picpay.desafio.android.contacts.data.PicPayService
import com.picpay.desafio.android.contacts.data.remote.mapper.toModel
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: PicPayService,
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { it.toModel() }
    }
}
