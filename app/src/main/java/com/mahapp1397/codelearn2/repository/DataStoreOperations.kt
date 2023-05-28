package com.mahapp1397.codelearn2.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations
{
    suspend fun savePhoneNumber(phoneNumber: String)

    fun getPhoneNumber(): Flow<String>
}