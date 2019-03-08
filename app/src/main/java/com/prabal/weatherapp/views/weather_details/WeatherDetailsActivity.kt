package com.prabal.weatherapp.views.weather_details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.prabal.weatherapp.R
import com.prabal.weatherapp.utils.getViewModel
import com.prabal.weatherapp.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_weather_detail.*

class WeatherDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
       // binding= DataBindingUtil.setContentView(this,R.layout.activity_weather_detail)
        setSupportActionBar(toolbar)
        viewModel = getViewModel()
        setupViewFragment()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
            ?: replaceFragmentInActivity(WeatherDetailsFragment.newInstance(), R.id.contentFrame)
    }
    fun getViewModel(): WeatherViewModel = getViewModel(WeatherViewModel::class.java)
}
