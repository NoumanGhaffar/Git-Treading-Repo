package com.nouman.gittreadingrepo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nouman.gittreadingrepo.R.id
import com.nouman.gittreadingrepo.ui.MainActivity
import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun whenActivityLaunched_ShimmerViewShouldBeVisible() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(id.shimmerLayout))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun whenActivityLaunched_internetIsConnected_recyclerViewShouldContainItems(){
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(id.rvRepos)).check(RecyclerViewItemCountAssertion(30))
    }

    @Test
    fun whenActivityLaunched_AndUserClicksOnLoadFromDb_recyclerViewShouldContainItems(){
        ActivityScenario.launch(MainActivity::class.java)
        //Waiting for view to be visible
        Thread.sleep(4000)
        onView(withId(id.btnShowFromDB)).perform(click())
        onView(withId(id.rvRepos)).check(RecyclerViewItemCountAssertion(30))
    }
}
