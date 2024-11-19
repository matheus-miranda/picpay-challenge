package com.picpay.desafio.android.core.network

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreNetworkModule = module {
    single { RetrofitBuilder.createService }
    factoryOf(::ConnectivityManagerImpl) { bind<ConnectivityManager>() }
}
