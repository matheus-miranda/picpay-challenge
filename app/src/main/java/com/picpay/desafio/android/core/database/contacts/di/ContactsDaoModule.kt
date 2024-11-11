package com.picpay.desafio.android.core.database.contacts.di

import com.picpay.desafio.android.core.database.PicPayDatabase
import com.picpay.desafio.android.core.database.contacts.dao.UserDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val contactsDaoModule = module {
    singleOf(::provideUserDao)
}

internal fun provideUserDao(database: PicPayDatabase): UserDao = database.userDao()
