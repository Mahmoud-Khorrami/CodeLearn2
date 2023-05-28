package com.mahapp1397.codelearn2.use_cases

import com.mahapp1397.codelearn2.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPhoneNumberUseCase(private val repository: Repository)
{
    operator fun invoke(): Flow<String>
    {
        return repository.getPhoneNumber()
    }
}