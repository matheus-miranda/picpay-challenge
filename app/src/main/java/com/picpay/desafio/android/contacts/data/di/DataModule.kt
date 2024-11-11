package com.picpay.desafio.android.contacts.data.di

import com.picpay.desafio.android.contacts.data.remote.PicPayService
import com.picpay.desafio.android.contacts.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {
    singleOf(::provideUserApi)
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }
}

fun provideUserApi(retrofit: Retrofit): PicPayService {
    return retrofit.create(PicPayService::class.java)
}
