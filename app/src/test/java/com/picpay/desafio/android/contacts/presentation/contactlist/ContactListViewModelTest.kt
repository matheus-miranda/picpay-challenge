@file:OptIn(ExperimentalCoroutinesApi::class)

package com.picpay.desafio.android.contacts.presentation.contactlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.contacts.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ContactListViewModelTest {

    private lateinit var viewModel: ContactListViewModel
    private val repository = mock<UserRepository>()

    @Before
    fun setUp() {
        viewModel = ContactListViewModel(repository)
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() {
        assertEquals(ListUiState.Loading, viewModel.userListUiState.value)
    }

    @Test
    fun uiState_whenFetchListIsSuccessful_thenEmitSuccess() = runTest {
        val expectedUsers = userList()
        whenever(repository.getUsers()).thenReturn(flowOf(expectedUsers))

        val vmStateCollector = launch {
            viewModel.userListUiState.collect {
                if (it is ListUiState.Success) {
                    assertEquals(expectedUsers, it.data)
                }
            }
        }

        vmStateCollector.cancel()
    }

    @Test
    fun uiState_whenFetchReturnsEmpty_thenEmitEmptyListSuccess() = runTest {
        val expectedUsers = emptyList<User>()
        whenever(repository.getUsers()).thenReturn(flowOf(expectedUsers))

        val vmStateCollector = launch {
            viewModel.userListUiState.collect {
                if (it is ListUiState.Success) {
                    assertEquals(expectedUsers, it.data)
                }
            }
        }

        vmStateCollector.cancel()
    }

    @Test(expected = Exception::class)
    fun uiState_whenFetchListFails_thenEmitError() = runTest {
        val expectedError = "Error"
        whenever(repository.getUsers()).thenThrow(Exception(expectedError))

        val vmStateCollector = launch {
            viewModel.userListUiState.collect {
                if (it is ListUiState.Error) {
                    assertEquals(expectedError, it.error)
                }
            }
        }

        vmStateCollector.cancel()
    }

    private fun userList() =
        listOf(User(img = "img", name = "name", id = 8235, username = "username"))
}
