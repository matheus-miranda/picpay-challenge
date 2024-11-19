package com.picpay.desafio.android.contacts.data.repository

import android.util.Log
import com.picpay.desafio.android.contacts.data.local.toEntity
import com.picpay.desafio.android.contacts.data.local.toModel
import com.picpay.desafio.android.contacts.data.remote.PicPayService
import com.picpay.desafio.android.contacts.data.remote.mapper.toModel
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import com.picpay.desafio.android.core.database.contacts.dao.UserDao
import com.picpay.desafio.android.core.network.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val api: PicPayService,
    private val dao: UserDao,
    private val connectivityManager: ConnectivityManager,
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        if (connectivityManager.isOnline()) {
            try {
                val apiUsers = api.getUsers().map { it.toModel() }
                dao.save(apiUsers.map { it.toEntity() })
            } catch (e: Exception) {
                Log.e("UserRepoImpl", "Failed: ${e.message}")
            } finally {
                emitAll(dao.getAll().map { it.toModel() })
            }
        } else {
            emitAll(dao.getAll().map { it.toModel() })
        }
    }
}
