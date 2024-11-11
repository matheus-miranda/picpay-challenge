package com.picpay.desafio.android.contacts.domain.repository

import com.picpay.desafio.android.contacts.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}
