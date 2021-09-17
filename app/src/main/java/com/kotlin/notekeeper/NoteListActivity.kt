package com.kotlin.notekeeper

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val activityIntent = Intent(this,MainActivity::class.java)
            startActivity(activityIntent)
        }

        findViewById<RecyclerView>(R.id.listItems).layoutManager = LinearLayoutManager(this)

        //findViewById<RecyclerView>(R.id.listItems).adapter = NoteRecyclerAdapter(this, DataManager.notes)
       // findViewById<RecyclerView>(R.id.listItems).adapter = NoteRecyclerAdapter(this)


    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.listItems).adapter?.notifyDataSetChanged()
    }
}