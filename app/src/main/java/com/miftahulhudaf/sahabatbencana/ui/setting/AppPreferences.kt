package com.miftahulhudaf.sahabatbencana.ui.setting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {

    @Suppress("PrivatePropertyName")
    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getDarkMode(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveDarkMode(darkModeState: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = darkModeState
        }
    }
}