package com.picpay.desafio.android.contacts.presentation.di

import com.picpay.desafio.android.contacts.presentation.contactlist.ContactListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {
    viewModelOf(::ContactListViewModel)
}
