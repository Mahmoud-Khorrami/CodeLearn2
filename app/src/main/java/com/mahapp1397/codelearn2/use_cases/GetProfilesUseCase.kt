package com.mahapp1397.codelearn2.use_cases

import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetProfilesUseCase(private val repository: Repository)
{
    operator fun invoke(): Flow<List<Profile>>
    {
        return repository.getProfile()
    }
}