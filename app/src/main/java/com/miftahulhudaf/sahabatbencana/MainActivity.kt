package com.miftahulhudaf.sahabatbencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.miftahulhudaf.sahabatbencana.data.api.RetrofitBuilder
import com.miftahulhudaf.sahabatbencana.data.model.Disaster
import com.miftahulhudaf.sahabatbencana.databinding.ActivityMainBinding
import com.miftahulhudaf.sahabatbencana.ui.base.ViewModelFactory
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterAdapter
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter = DisasterAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var rvItems: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RetrofitBuilder.apiService)
        ).get(MainViewModel::class.java)

        val bottomSheet = findViewById<View>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        rvItems = bottomSheet.findViewById<RecyclerView?>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        viewModel.disasterList.observe(this) {
            showDisasterList(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getAllDisaster()
    }


    private fun showDisasterList(disasters: List<Disaster>) {
        adapter.setDisaster(disasters)
        rvItems.adapter = adapter
    }


    override fun onResume() {
        super.onResume()

//        val modalBottomSheet = ModalBottomSheet()
//        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

}