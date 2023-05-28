package com.mahapp1397.codelearn2.repository

import com.mahapp1397.codelearn2.models.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val localDataSource: LocalDataSource, )
{
    suspend fun savePhoneNumber(phoneNumber: String)
    {
        dataStore.savePhoneNumber(phoneNumber)
    }

    fun getPhoneNumber(): Flow<String>
    {
        return dataStore.getPhoneNumber()
    }

    suspend fun saveProfile(profile: Profile)
    {
        localDataSource.saveProfile(profile)
    }

    fun getProfile(): Flow<List<Profile>> = localDataSource.getProfiles()

    fun getSelectedProfile(profileId: Int): Flow<Profile> = localDataSource.getSelectedProfile(profileId)
}