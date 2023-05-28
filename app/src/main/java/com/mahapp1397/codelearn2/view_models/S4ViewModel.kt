package com.mahapp1397.codelearn2.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class S4ViewModel @Inject constructor(private val useCases: UseCases): ViewModel()
{
    private val _profiles : MutableStateFlow<List<Profile>> = MutableStateFlow(emptyList())
    val profiles : StateFlow<List<Profile>> = _profiles

    init
    {
        getProfiles()
    }

    private fun getProfiles()
    {
        viewModelScope.launch(Dispatchers.IO) {

            useCases.getProfilesUseCase().collect{
                _profiles.value = it
            }
        }
    }
}