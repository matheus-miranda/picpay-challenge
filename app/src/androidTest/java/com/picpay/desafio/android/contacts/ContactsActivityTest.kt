package com.picpay.desafio.android.contacts

import androidx.test.core.app.launchActivity
import com.picpay.desafio.android.di.testModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ContactsActivityTest {

    private lateinit var server: MockWebServer
    private val robot by lazy { ContactsActivityRobot() }

    @Before
    fun setUp() {
        server = MockWebServer().apply { start(port = 8080) }
        loadKoinModules(testModule)
        launchActivity<ContactsActivity>()
    }

    @After
    fun tearDown() {
        server.shutdown()
        unloadKoinModules(testModule)
    }

    @Test
    fun shouldDisplayTitle() {
        robot { checkTitleIsDisplayed() }
    }

    @Test
    fun shouldDisplayListItem() {
        robot {
            checkIfUserIsDisplayed()
            checkIfProgressBarNotVisible()
        }
    }
}
