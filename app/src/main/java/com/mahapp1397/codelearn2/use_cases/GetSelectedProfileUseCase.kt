package com.mahapp1397.codelearn2.use_cases

import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetSelectedProfileUseCase(private val repository: Repository)
{
    operator fun invoke(profileId: Int): Flow<Profile>
    {
        return repository.getSelectedProfile(profileId)
    }
}