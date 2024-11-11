package com.picpay.desafio.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.core.database.contacts.dao.UserDao
import com.picpay.desafio.android.core.database.contacts.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
internal abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
