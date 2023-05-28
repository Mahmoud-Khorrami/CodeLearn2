package com.mahapp1397.codelearn2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahapp1397.codelearn2.models.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: Profile): Long

    @Query("SELECT * FROM profile_table")
    fun getProfiles(): Flow<List<Profile>>

    @Query("SELECT * FROM profile_table WHERE id = :profileId")
    fun getSelectedProfile(profileId: Int): Flow<Profile>
}