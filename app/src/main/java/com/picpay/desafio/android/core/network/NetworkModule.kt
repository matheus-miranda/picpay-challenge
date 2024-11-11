package com.picpay.desafio.android.core.network

import org.koin.dsl.module

val coreNetworkModule = module {
    single { RetrofitBuilder.createService }
}
