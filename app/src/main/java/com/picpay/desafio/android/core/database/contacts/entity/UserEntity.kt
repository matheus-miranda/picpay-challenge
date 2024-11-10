package com.picpay.desafio.android.core.database.contacts.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val img: String,
    val name: String,
    val username: String,
)
