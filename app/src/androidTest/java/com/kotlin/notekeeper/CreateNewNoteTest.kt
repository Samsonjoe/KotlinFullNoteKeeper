package com.kotlin.notekeeper

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*

@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest : TestCase() {

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)


    @Test
    fun createNewNote (){
        val course = DataManager.courses["android_async"]
        val noteTitle = "This is a test note"
        val noteText = "This is the body of my test note"

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java),equalTo(course))).perform(click())

        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.textNoteText)).perform(typeText(noteText),closeSoftKeyboard())

        pressBack()

        val newNote = DataManager.notes.last()
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)


    }
}