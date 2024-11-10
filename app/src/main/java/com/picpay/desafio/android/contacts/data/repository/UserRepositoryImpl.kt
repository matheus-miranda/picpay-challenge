package com.picpay.desafio.android.contacts.data.repository

import com.picpay.desafio.android.contacts.data.local.toEntity
import com.picpay.desafio.android.contacts.data.local.toModel
import com.picpay.desafio.android.contacts.data.remote.PicPayService
import com.picpay.desafio.android.contacts.data.remote.mapper.toModel
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import com.picpay.desafio.android.core.database.contacts.dao.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val api: PicPayService,
    private val dao: UserDao,
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        val apiUsers = api.getUsers().map { it.toModel() }

        dao.save(apiUsers.map { it.toEntity() })

        emitAll(dao.getAll().map { it.toModel() })
    }
}
