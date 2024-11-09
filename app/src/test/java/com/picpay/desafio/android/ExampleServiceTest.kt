package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contacts.data.remote.PicPayService
import com.picpay.desafio.android.contacts.data.remote.dto.UserResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runTest {
        // given
        val call = mock<List<UserResponse>>()
        val expectedUserResponses = emptyList<UserResponse>()

        whenever(call).thenReturn(expectedUserResponses)
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        Assert.assertEquals(users, expectedUserResponses)
    }
}
