package com.mahapp1397.codelearn2.view_models

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.codelearn2.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class S3ViewModel @Inject constructor(private val useCases: UseCases): ViewModel()
{
    private val _time = mutableIntStateOf(0)
    val time = _time

    private val timer = object : CountDownTimer(20000, 1000)
    {
        @SuppressLint("AutoboxingStateValueProperty")
        override fun onTick(p0: Long)
        {
            _time.value = p0.toInt()
        }
        override fun onFinish()
        {
        }
    }

    fun startTimer() = timer.start()!!

    fun savePhoneNumber(phoneNumber: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.savePhoneNumberUseCase(phoneNumber)
        }
    }
}