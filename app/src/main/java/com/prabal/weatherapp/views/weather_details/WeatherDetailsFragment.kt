package com.prabal.weatherapp.views.weather_details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prabal.weatherapp.databinding.FragmentWeatherDetailsBinding


/**
 * A simple [Fragment] subclass.
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
    //private lateinit var viewModel: WeatherViewModel

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
        viewDataBinding.setLifecycleOwner(this.viewLifecycleOwner)
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewModel?.startLoad()
    }
  /*  override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }*/



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private const val TAG = "WeatherDetailsFragment"
        fun newInstance() = WeatherDetailsFragment()


    }

}
