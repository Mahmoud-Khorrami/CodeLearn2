package com.mahapp1397.codelearn2.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class S5ViewModel @Inject constructor(private val useCases: UseCases): ViewModel()
{
    fun saveProfile(profile: Profile)
    {
        viewModelScope.launch(Dispatchers.IO) {

            useCases.saveProfileUseCase(profile)

        }
    }
}