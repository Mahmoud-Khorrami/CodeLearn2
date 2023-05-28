package com.mahapp1397.codelearn2.repository

import com.mahapp1397.codelearn2.database.ColdLearn2Database
import com.mahapp1397.codelearn2.models.Profile
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(codeLearn2Database: ColdLearn2Database): LocalDataSource
{
    private val myDao = codeLearn2Database.myDao()

    override suspend fun saveProfile(profile: Profile)
    {
        myDao.saveProfile(profile)
    }

    override fun getProfiles(): Flow<List<Profile>>
    {
        return myDao.getProfiles()
    }

    override fun getSelectedProfile(profileId: Int): Flow<Profile>
    {
        return myDao.getSelectedProfile(profileId)
    }
}