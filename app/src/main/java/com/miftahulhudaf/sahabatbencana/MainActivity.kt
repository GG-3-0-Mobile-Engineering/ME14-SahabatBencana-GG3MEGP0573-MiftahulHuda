package com.miftahulhudaf.sahabatbencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.miftahulhudaf.sahabatbencana.data.api.FloodResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DisasterListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")

        var serializesData = FloodResponse(
            statusCode = 200
        )
        val serialized = Gson().toJson(serializesData)
        Log.d( "TypeSerialized", serialized)
    }
}