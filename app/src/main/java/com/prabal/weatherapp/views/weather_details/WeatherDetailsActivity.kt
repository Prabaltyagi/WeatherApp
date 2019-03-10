package com.prabal.weatherapp.views.weather_details

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.prabal.weatherapp.R
import com.prabal.weatherapp.constants.LATITUDE_PREF
import com.prabal.weatherapp.constants.LONGITUDE_PREF
import com.prabal.weatherapp.utils.Event
import com.prabal.weatherapp.utils.getViewModel
import com.prabal.weatherapp.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_weather_detail.*

class WeatherDetailsActivity : AppCompatActivity(),LifecycleOwner {

    private lateinit var binding: com.prabal.weatherapp.databinding.ActivityWeatherDetailBinding
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_weather_detail)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        setPrefrenceLocation()

        binding.viewmodel = getViewModel().apply {
            // Subscribe to "new location" event
            newLocationEvent.observe(this@WeatherDetailsActivity, Observer<Event<String>> { event ->
                event.getContentIfNotHandled()?.let {
                    this@WeatherDetailsActivity.addNewLocation(it)
                }
            })

        }
        setSupportActionBar(toolbar)
        setupViewFragment()
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
    public override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
    private fun setPrefrenceLocation(){
        val sharedPref: SharedPreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val lat = sharedPref.getString(LATITUDE_PREF, "")
        val lon = sharedPref.getString(LONGITUDE_PREF, "")
        //set default
        if (lat.isBlank()) {
            with (sharedPref.edit()) {
                putString(LATITUDE_PREF, "28.644800")
                commit()
            }
        }
        if (lon.isBlank()) {
            with (sharedPref.edit()) {
                putString(LONGITUDE_PREF, "77.216721")
                commit()
            }
        }
    }
    private fun setPrefrenceLocation(lat:String,lon:String){
        val sharedPref: SharedPreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        //set default
        if (lat.isBlank()) {
            with (sharedPref.edit()) {
                putString(LATITUDE_PREF, "28.644800")
                commit()
            }
        }else{
            with (sharedPref.edit()) {
                putString(LATITUDE_PREF, lat)
                commit()
            }

        }
        if (lon.isBlank()) {
            with (sharedPref.edit()) {
                putString(LONGITUDE_PREF, "77.216721")
                commit()
            }
        }else{
            with (sharedPref.edit()) {
                putString(LATITUDE_PREF, lon)
                commit()
            }
        }
    }

    /*Use to test only
    * */
     fun addNewLocation(location:String) {
         //recieve updated location
         Log.d("LatLong:","27,28")
         val separate1 = location.split(",".toRegex())
         var lat:String=separate1.get(0)
         var lon:String=separate1.get(1)

         setPrefrenceLocation(lat,lon)
        //can start new load

    }
    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
            ?: replaceFragmentInActivity(WeatherDetailsFragment.newInstance(), R.id.contentFrame)
    }
    fun getViewModel(): WeatherViewModel = getViewModel(WeatherViewModel::class.java)
}
