package com.miftahulhudaf.sahabatbencana.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.miftahulhudaf.sahabatbencana.ui.setting.AppPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: AppPreferences) : ViewModel() {

    fun getDarkMode(): LiveData<Boolean> {
        return pref.getDarkMode().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveDarkMode(isDarkModeActive)
        }
    }
}