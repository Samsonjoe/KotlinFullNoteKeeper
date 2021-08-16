package com.kotlin.notekeeper.ui.howMany

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HowManyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is How Many Fragment"
    }
    val text: LiveData<String> = _text
}