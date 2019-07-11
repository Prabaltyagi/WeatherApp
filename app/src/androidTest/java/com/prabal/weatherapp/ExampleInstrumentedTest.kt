package com.prabal.weatherapp

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.prabal.weatherapp.constants.CITY_NAME
import com.prabal.weatherapp.constants.DEFAULT_LATITUDE
import com.prabal.weatherapp.constants.DEFAULT_LONGITUDE
import com.prabal.weatherapp.views.weather_details.WeatherDetailsActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 *
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityTestRule(WeatherDetailsActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("com.prabal.weatherapp", appContext.packageName)
    }
    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(WeatherDetailsActivity::class.java)
    }

    @Test
    fun enter_latitude(){
        Espresso.onView(withId(R.id.editLatitude)).perform(typeText("28.4595"))
    }

    @Test
    fun enter_longitude(){
        Espresso.onView(withId(R.id.editLongitude)).perform(typeText("77.0266"))
    }


    @Test
    fun when_user_enter_latitude_longitude_check_cityname(){
        onView(withId(R.id.fab_new_location)).perform(click())

        onView(withId(R.id.editLatitude)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.editLongitude)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.editLatitude)).perform(typeText(DEFAULT_LATITUDE))
        onView(withId(R.id.editLongitude)).perform(typeText(DEFAULT_LONGITUDE))
        onView(withId(R.id.buttonGo)).perform(click())

        onView(withId(R.id.city)).check(matches(withText(CITY_NAME)))

    }
}
