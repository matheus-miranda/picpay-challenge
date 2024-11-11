package com.picpay.desafio.android.contacts.presentation.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val userListUiState: StateFlow<ListUiState> = flow {
        try {
            repository.getUsers().collect {
                emit(ListUiState.Success(it))
            }
        } catch (e: Exception) {
            emit(ListUiState.Error(e.toString()))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        ListUiState.Loading
    )
}

sealed interface ListUiState {
    data object Loading : ListUiState
    data class Error(val error: String?) : ListUiState
    data class Success(val data: List<User>) : ListUiState
}
