package com.rheasan.assignment_2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class StoreHelper(val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "TextData")
        val key = stringPreferencesKey("SavedData")
    }
    val readData : Flow<String> = context.dataStore.data.map { preferences ->
        preferences[key] ?: ""
    }
    suspend fun saveData(data: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }
}

