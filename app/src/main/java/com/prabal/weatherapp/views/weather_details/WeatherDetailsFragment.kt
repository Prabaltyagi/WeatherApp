package com.prabal.weatherapp.views.weather_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.prabal.weatherapp.R
import com.prabal.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.prabal.weatherapp.utils.setupSnackbar


/**
 * A  [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WeatherDetailsFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewDataBinding: com.prabal.weatherapp.databinding.FragmentWeatherDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentWeatherDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as WeatherDetailsActivity).getViewModel()

        }

        return viewDataBinding.root
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewModel?.let {
            view?.setupSnackbar(this, it.snackbarMessage, Snackbar.LENGTH_LONG)
        }
        viewDataBinding.setLifecycleOwner(this.viewLifecycleOwner)
        setupFab()
    }

    private fun setupFab() {
        activity?.findViewById<FloatingActionButton>(R.id.fab_new_location)?.let {
            it.setOnClickListener {
                //set  latitude and longitude
                //TODO for testing
               // viewDataBinding.viewModel?.addNewLocaion("28.457523,77.026344")
                viewDataBinding.viewModel?.visibleAddLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewModel?.startLoad()
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private const val TAG = "WeatherDetailsFragment"
        fun newInstance() = WeatherDetailsFragment()


    }

}
