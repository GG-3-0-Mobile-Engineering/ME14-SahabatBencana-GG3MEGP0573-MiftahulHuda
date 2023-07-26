package com.miftahulhudaf.sahabatbencana

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class SplashScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)


        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_mapsFragment)
        }, 3000)


        val handler = context?.let { WorkManagerHandler(it) }
        if (handler != null) {
            handler.cancelNotificationWorker()
            handler.scheduleNotificationWorker()
        }

        return view
    }

}