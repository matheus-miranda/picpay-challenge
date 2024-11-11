package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import com.picpay.desafio.android.contacts.presentation.contactlist.UserListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    viewModel { UserListViewModel(FakeRepository()) }
}

class FakeRepository : UserRepository {
    override fun getUsers(): Flow<List<User>> = flowOf(testUser)
}

val testUser = listOf(
    User(
        id = 1001,
        name = "Eduardo Santos",
        username = "@eduardo.santos",
        img = "https://randomuser.me/api/portraits/men/9.jpg",
    )
)
