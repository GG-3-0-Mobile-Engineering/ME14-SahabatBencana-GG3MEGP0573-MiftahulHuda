package com.miftahulhudaf.sahabatbencana.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.miftahulhudaf.sahabatbencana.R
import com.miftahulhudaf.sahabatbencana.databinding.ModalBottomSheetBinding
import com.miftahulhudaf.sahabatbencana.ui.main.adapter.DisasterAdapter

class ModalBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: ModalBottomSheetBinding
    private val adapter = DisasterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModalBottomSheetBinding.inflate(layoutInflater)
        binding.recyclerview.adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.modal_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}