package com.picpay.desafio.android.core.database.contacts.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.picpay.desafio.android.core.database.contacts.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun save(users: List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserEntity>>
}
