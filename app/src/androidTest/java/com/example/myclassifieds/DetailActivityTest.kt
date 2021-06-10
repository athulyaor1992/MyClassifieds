package com.example.myclassifieds

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.myclassifieds.ui.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(DetailActivity::class.java)


    @Test
    fun test() {
        onView(withId(R.id.classifyName))
            .check(matches(isDisplayed()))
        onView(withId(R.id.classifyDate))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detailImage))
            .check(matches(isDisplayed()))

    }

    @Test
    fun goHome() {
        onView(withId(R.id.home)).perform(ViewActions.click())
    }
}

