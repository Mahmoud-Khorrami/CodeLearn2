package com.mahapp1397.codelearn2.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.codelearn2.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class S2ViewModel @Inject constructor(private val useCases: UseCases): ViewModel()
{
    private val _phoneNumber = MutableStateFlow("")
    var phoneNumber: StateFlow<String> = _phoneNumber

    init
    {
        getPhoneNumber()
    }

    fun getPhoneNumber()
    {
        viewModelScope.launch {
            _phoneNumber.value = useCases.getPhoneNumberUseCase().stateIn(viewModelScope).value
        }
    }
}