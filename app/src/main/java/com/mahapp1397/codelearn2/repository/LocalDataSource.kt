package com.mahapp1397.codelearn2.repository

import com.mahapp1397.codelearn2.models.Profile
import kotlinx.coroutines.flow.Flow

interface LocalDataSource
{
    suspend fun saveProfile(profile: Profile)

    fun getProfiles(): Flow<List<Profile>>

    fun getSelectedProfile(profileId: Int): Flow<Profile>
}