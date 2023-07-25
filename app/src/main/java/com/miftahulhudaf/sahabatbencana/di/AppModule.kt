package com.miftahulhudaf.sahabatbencana.di

import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.SettingsViewModel
import com.miftahulhudaf.sahabatbencana.ui.setting.AppPreferences
import com.miftahulhudaf.sahabatbencana.utils.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferenceModule = module {
    single { AppPreferences(androidContext().dataStore) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}