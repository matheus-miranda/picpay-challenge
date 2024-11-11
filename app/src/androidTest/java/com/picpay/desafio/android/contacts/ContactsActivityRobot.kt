package com.picpay.desafio.android.contacts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R

class ContactsActivityRobot {

    operator fun invoke(block: ContactsActivityRobot.() -> Unit) = block(this)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    fun checkTitleIsDisplayed() {
        val expectedTitle = context.getString(R.string.title)
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    fun checkIfUserIsDisplayed() {
        onView(withText("Eduardo Santos")).check(matches(isDisplayed()))
        onView(withText("@eduardo.santos")).check(matches(isDisplayed()))
        onView(withId(R.id.picture)).check(matches(isDisplayed()))
    }

    fun checkIfProgressBarNotVisible() {
        onView(withId(R.id.user_list_progress_bar)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
    }
}
