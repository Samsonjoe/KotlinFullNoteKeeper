package com.kotlin.notekeeper.ui.recentlyViewedNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecentlyViewedNotesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is How Many Fragment"
    }
    val text: LiveData<String> = _text
}