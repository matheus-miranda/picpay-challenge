package com.picpay.desafio.android.contacts.data.local

import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.core.database.contacts.entity.UserEntity

fun UserEntity.toModel() = User(
    img = this.img, name = this.name, id = this.id, username = this.username
)

fun List<UserEntity>.toModel() = map { it.toModel() }

fun User.toEntity() = UserEntity(
    id = this.id, img = this.img, name = this.name, username = this.username
)
