package com.miftahulhudaf.sahabatbencana

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.miftahulhudaf.sahabatbencana.data.api.RetrofitBuilder
import com.miftahulhudaf.sahabatbencana.data.response.archive.Disaster
import com.miftahulhudaf.sahabatbencana.databinding.ActivityMainBinding
import com.miftahulhudaf.sahabatbencana.ui.base.ViewModelFactory
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterAdapter
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var rvItems: RecyclerView

    private val adapter = DisasterAdapter()

    private val mainViewModel by viewModel<MainViewModel>()
    private val boundsBuilder = LatLngBounds.Builder()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        setupViewModel(view)
    }


    private val callback = OnMapReadyCallback { googleMap ->
        mainViewModel.disasterList.observe(this) {
            Log.d("MAPS FRAGMENT", "testing")
            renderDisasterMarker(googleMap, it)
        }
        mainViewModel.getAllDisaster()
    }


    private fun setupViewModel(view: View) {
        val bottomSheet = view.findViewById<View>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        rvItems = bottomSheet.findViewById<RecyclerView?>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        mainViewModel.disasterList.observe(viewLifecycleOwner) {
            showDisasterList(it)
        }

        mainViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.getAllDisaster()
    }


    private fun showDisasterList(disasters: List<Disaster>) {
        adapter.setDisaster(disasters)
        rvItems.adapter = adapter
    }


    private fun renderDisasterMarker(gmaps: GoogleMap, disasters: List<Disaster>) {
        gmaps.clear()

        disasters.forEach { feature ->
            val lat = feature.geometry?.coordinates?.get(1)
            val lon = feature.geometry?.coordinates?.get(0)

            if(lat != null && lon != null) {
                val latLng = LatLng(lat, lon)
                Log.d("renderDisasterMarker in", latLng.toString())
                gmaps.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(feature.properties?.title)
                        .snippet(feature.properties?.text)
                )
                boundsBuilder.include(latLng)
            }
        }

        val bounds = boundsBuilder.build()
        gmaps.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300
            )
        )
    }
}