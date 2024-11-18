package com.example.pr78

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkEditTextIsDisplayed() {
        onView(withId(R.id.urlEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkButtonIsDisplayed() {
        onView(withId(R.id.downloadButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkImageViewIsDisplayed() {
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

    @Test
    fun enterUrlAndDownloadImage() {
        onView(withId(R.id.urlEditText)).perform(typeText("https://example.com/image.png"))
        onView(withId(R.id.downloadButton)).perform(click())
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkButtonClickWithoutUrl() {
        onView(withId(R.id.downloadButton)).perform(click())
        onView(withId(R.id.urlEditText)).check(matches(hasErrorText("URL is required")))
    }
}