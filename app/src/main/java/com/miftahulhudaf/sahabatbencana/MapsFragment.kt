package com.miftahulhudaf.sahabatbencana

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.graphics.Color
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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
import com.miftahulhudaf.sahabatbencana.data.model.DisasterType
import com.miftahulhudaf.sahabatbencana.data.model.LocationData
import com.miftahulhudaf.sahabatbencana.data.response.archive.Disaster
import com.miftahulhudaf.sahabatbencana.data.response.archive.DisasterProperty
import com.miftahulhudaf.sahabatbencana.data.utils.DateHelper
import com.miftahulhudaf.sahabatbencana.data.utils.Resource
import com.miftahulhudaf.sahabatbencana.databinding.ActivityMainBinding
import com.miftahulhudaf.sahabatbencana.databinding.FragmentMapsBinding
import com.miftahulhudaf.sahabatbencana.ui.base.ViewModelFactory
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterAdapter
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterTypeAdapter
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.SettingsViewModel
import com.miftahulhudaf.sahabatbencana.utils.SpaceItemDecoration
import com.miftahulhudaf.sahabatbencana.utils.isDarkMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment(),
    DisasterTypeAdapter.OnDisasterTypeClickListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var rvItems: RecyclerView
    private lateinit var pgLoading: LinearLayout

    private val adapter = DisasterAdapter()

    private val mainViewModel by viewModel<MainViewModel>()
    private val settingsViewModel by viewModel<SettingsViewModel>()
    private val boundsBuilder = LatLngBounds.Builder()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        binding.imageButton.setOnClickListener {
            findNavController(binding.root).navigate(
                R.id.action_mapsFragment_to_settingFragment
            )
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        setupDarkMode()
        initSearchView()
        showDisasterTypeList(mainViewModel.disasterType)

        setupViewModel(view)
    }


    private val callback = OnMapReadyCallback { googleMap ->
        mainViewModel.filteredArchiveFeatures.observe(this) { resource ->
            when (resource) {
                is Resource.Error -> {
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    pgLoading.setVisibility(View.VISIBLE)
                }

                is Resource.Success -> {
                    pgLoading.setVisibility(View.GONE)
                    rvItems.setVisibility(View.VISIBLE)

                    val properties = resource.data!!.map { it.properties }
                    showDisasterList(properties)
                    renderDisasterMarker(googleMap, resource.data)
                }
            }
        }
    }

    private fun setupDarkMode() {
        settingsViewModel.getDarkMode().observe(viewLifecycleOwner){ isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


    private fun setupViewModel(view: View) {
        val bottomSheet = view.findViewById<View>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet).apply {
            state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }

        val lblDate = view.findViewById<TextView>(R.id.lblDate)
        lblDate.text = DateHelper.selectedDateLabel()

        rvItems = bottomSheet.findViewById<RecyclerView?>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        pgLoading = bottomSheet.findViewById(R.id.circleLoading)

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(activity).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(16))
        }

        showDisasterTypeList(mainViewModel.disasterType)
    }



    private fun initSearchView() {
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        val fromColumns = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val toViews = intArrayOf(android.R.id.text1)
        val suggestionAdapter = SimpleCursorAdapter(
            activity,
            android.R.layout.simple_list_item_1,
            null,
            fromColumns,
            toViews,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

        binding.searchView.suggestionsAdapter = suggestionAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.searchCity(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) mainViewModel.searchCity()

                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                val suggestions = getFilteredSuggestions(newText)
                suggestions.forEachIndexed { index, suggestion ->
                    cursor.addRow(arrayOf(index, suggestion.province))
                }
                suggestionAdapter.changeCursor(cursor)

                return true
            }
        })

        binding.searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = binding.searchView.suggestionsAdapter.getItem(position) as Cursor
                val columnIndex = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)

                if (columnIndex != -1) {
                    val suggestionText = cursor.getString(columnIndex)
                    binding.searchView.setQuery(suggestionText, true)
                }

                return true
            }

        })
    }


    private fun renderDisasterMarker(gmaps: GoogleMap, disasters: List<Disaster>) {
        gmaps.clear()

        disasters.forEach { feature ->
            val lat = feature.geometry.coordinates?.get(1)
            val lon = feature.geometry.coordinates?.get(0)

            if(lat != null && lon != null) {
                val latLng = LatLng(lat, lon)
                gmaps.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(feature.properties.title)
                        .snippet(feature.properties.createdAt)
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

    private fun showDisasterList(disasters: List<DisasterProperty>) {
        adapter.setDisaster(disasters)
        rvItems.adapter = adapter
    }

    private fun showDisasterTypeList(disasterTypes: List<DisasterType>) {
        val adapter = DisasterTypeAdapter(this)
        adapter.submitList(disasterTypes)

        binding.rvCategory.adapter = adapter
    }

    private fun getFilteredSuggestions(query: String?): List<LocationData> {
        return mainViewModel.supportedLocations.filter {
            it.province.contains(query.orEmpty(), ignoreCase = true)
        }
    }

    override fun onDisasterTypeClick(view: View, disasterType: DisasterType) {
        val hasFilter = mainViewModel.applyFilter(disasterType.en)

        var cardDefaultBg = if(isDarkMode(requireContext())) Color.rgb(66, 66, 66) else Color.WHITE
        val cardBackgroundColor = if (hasFilter) Color.rgb(0, 136, 12) else cardDefaultBg
        (view as CardView).setCardBackgroundColor(cardBackgroundColor)
    }
}