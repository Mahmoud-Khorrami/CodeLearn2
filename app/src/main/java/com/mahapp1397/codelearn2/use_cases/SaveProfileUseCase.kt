package com.mahapp1397.codelearn2.use_cases

import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.repository.Repository

class SaveProfileUseCase(private val repository: Repository)
{
    suspend operator fun invoke(profile: Profile)
    {
        repository.saveProfile(profile)
    }
}