package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import com.picpay.desafio.android.di.testModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivityTest {

    private lateinit var server: MockWebServer
    private val robot by lazy { MainActivityRobot() }

    @Before
    fun setUp() {
        server = MockWebServer().apply { start(port = 8080) }
        loadKoinModules(testModule)
        launchActivity<MainActivity>()
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
