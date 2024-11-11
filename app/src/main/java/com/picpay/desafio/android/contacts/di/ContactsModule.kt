package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.data.di.dataModule
import com.picpay.desafio.android.contacts.presentation.di.presentationModule
import org.koin.dsl.module

val contactsFeature = module {
    includes(
        dataModule,
        presentationModule
    )
}
