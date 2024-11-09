package com.picpay.desafio.android.contacts.domain.repository

import com.picpay.desafio.android.contacts.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
