package com.picpay.desafio.android.contacts.data.remote.mapper

import com.picpay.desafio.android.contacts.data.remote.dto.UserResponse
import com.picpay.desafio.android.contacts.domain.model.User

fun UserResponse.toModel() = User(
    img = this.img, name = this.name, id = this.id, username = this.username
)
