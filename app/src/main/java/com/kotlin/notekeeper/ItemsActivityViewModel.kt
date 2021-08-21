package com.kotlin.notekeeper

import androidx.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {
    var navDrawerDisplaySelection = R.id.nav_notes

    private val maxRecentlyViewedNotes = 5
    val recentlyViewedNotes = ArrayList<NoteInfo> (maxRecentlyViewedNotes)


    fun addToRecentlyViewedNotes(note: NoteInfo) {
        //check if selection is already in the list
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if (existingIndex == -1) {
            //it isn't in the list....
            //ad new one to beginning of the list and remove any beyond max we want to keep
            recentlyViewedNotes.add(0,note)
            for (index in recentlyViewedNotes.lastIndex downTo maxRecentlyViewedNotes)
                recentlyViewedNotes.removeAt(index)
        } else {
            //it is in the list...
            //shift the ones above down the list and make it first member of the list
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            recentlyViewedNotes[0] = note
        }
    }

}