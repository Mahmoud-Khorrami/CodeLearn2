package com.mahapp1397.codelearn2.use_cases

import com.mahapp1397.codelearn2.repository.Repository

class SavePhoneNumberUseCase(private val repository: Repository)
{
    suspend operator fun invoke(phoneNumber: String)
    {
        repository.savePhoneNumber(phoneNumber)
    }
}