package com.miftahulhudaf.sahabatbencana.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.ImageView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun ImageView.loadImageFromUrl(url: String, options: RequestOptions = RequestOptions()) {

    var formatUrl = url
    if(url.trim() == "") {
        formatUrl = "https://propertywiselaunceston.com.au/wp-content/themes/property-wise/images/no-image.png"
    }

    Glide.with(this.context)
        .load(formatUrl)
        .apply(options)
        .into(this)
}

fun isDarkMode(context: Context): Boolean {
    val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
}