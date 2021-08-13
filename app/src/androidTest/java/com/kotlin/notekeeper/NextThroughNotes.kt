package com.kotlin.notekeeper

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test

class NextThroughNotes : TestCase(){

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes(){
        onData(allOf(IsInstanceOf(NoteInfo::class.java),equalTo(DataManager.notes[0]))).perform(click())

        for (index in  0..DataManager.notes.lastIndex){
            val note = DataManager.notes[index]

            onView(withId(R.id.spinnerCourses)).check(
                    matches(withSpinnerText(note.course?.title)))
            onView(withId(R.id.textNoteTitle)).check(
                    matches(withText(note.title)))
            onView(withId(R.id.textNoteText)).check(
                    matches(withText(note.text)))

            if (index!= DataManager.notes.lastIndex)
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
        }

        onView(withId(R.id.action_next)).check(matches(isEnabled()))
    }
}