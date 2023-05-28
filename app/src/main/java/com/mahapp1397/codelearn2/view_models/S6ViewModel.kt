package com.mahapp1397.codelearn2.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.codelearn2.models.Profile
import com.mahapp1397.codelearn2.use_cases.UseCases
import com.mahapp1397.codelearn2.utils.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class S6ViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle): ViewModel()
{
    private val _profile : MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profiles : StateFlow<Profile?> = _profile

    init
    {
        viewModelScope.launch(Dispatchers.IO) {

            val profileId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)

            profileId?.let { useCases.getSelectedProfileUseCase(profileId).collect{
                _profile.value = it
            } }
        }
    }
}