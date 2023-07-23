package com.miftahulhudaf.sahabatbencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.gson.Gson
import androidx.lifecycle.ViewModelProvider
import com.miftahulhudaf.sahabatbencana.data.api.ApiHelper
import com.miftahulhudaf.sahabatbencana.data.api.RetrofitBuilder
import com.miftahulhudaf.sahabatbencana.ui.base.ViewModelFactory
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterAdapter
import com.miftahulhudaf.sahabatbencana.ui.main.view.ModalBottomSheet
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter = DisasterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        val modalBottomSheet = ModalBottomSheet()
        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RetrofitBuilder.apiService)
        ).get(MainViewModel::class.java)

        viewModel.disasterList.observe(this) {
            adapter.setDisaster(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getAllDisaster()
    }

}