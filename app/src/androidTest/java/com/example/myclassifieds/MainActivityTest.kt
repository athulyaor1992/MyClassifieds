package com.example.myclassifieds


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.myclassifieds.adapter.ClassifyAdapter
import com.example.myclassifieds.network.ApiService
import com.example.myclassifieds.ui.MainActivity
import com.example.myclassifieds.viewmodel.ClassifyViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private var viewModel: ClassifyViewModel? = null
    private val apiService: ApiService = App.api!!

    @Before
    fun initialize() {
        viewModel = ClassifyViewModel(apiService)
    }

    @Test
    fun viewClassifyList() {

        onView(withId(R.id.classifyView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun classifyItemSelect() {

        onView(withId(R.id.classifyView))
            .perform(actionOnItemAtPosition<ClassifyAdapter.DataViewHolder>(0,
                click()
            ))
    }
}