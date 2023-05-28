package com.mahapp1397.codelearn2.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mahapp1397.codelearn2.utils.Constants.PREFERENCES_KEY1
import com.mahapp1397.codelearn2.utils.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context): DataStoreOperations
{
    private object PreferencesKey{
        val key1 = stringPreferencesKey(PREFERENCES_KEY1)
    }

    private val dataStore = context.dataStore

    override suspend fun savePhoneNumber(phoneNumber: String)
    {
        dataStore.edit {
            it[PreferencesKey.key1] = phoneNumber
        }
    }

    override fun getPhoneNumber(): Flow<String>
    {
        return dataStore.data.catch {
            if (it is IOException)
                emit(emptyPreferences())
            else
                throw it
        }
            .map {
                val phoneNumber = it[PreferencesKey.key1] ?: ""
                phoneNumber
            }
    }
}