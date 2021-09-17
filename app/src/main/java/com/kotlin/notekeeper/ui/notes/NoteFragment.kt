package com.kotlin.notekeeper.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlin.notekeeper.*

class NoteFragment : Fragment() {

  private lateinit var noteViewModel: NoteViewModel

  private val noteLayoutManager by lazy {
    LinearLayoutManager(activity)
  }

  private val noteRecyclerAdapter by lazy {
    context?.applicationContext?.let { NoteRecyclerAdapter(it, DataManager.notes) }

  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)[ItemsActivityViewModel::class.java]
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    noteViewModel =
            ViewModelProvider(this).get(NoteViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_note, container, false)

    /*val textView: TextView = root.findViewById(R.id.text_home)
    noteViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    root.findViewById<RecyclerView>(R.id.listItemsNotes).layoutManager = noteLayoutManager
    root.findViewById<RecyclerView>(R.id.listItemsNotes).adapter = noteRecyclerAdapter


    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              "Notes", Snackbar.LENGTH_LONG).show()
    }

    return root
  }

  override fun onResume() {
    super.onResume()
    noteRecyclerAdapter?.notifyDataSetChanged()
  }

  fun onNoteSelected(note: NoteInfo){
    viewModel.addToRecentlyViewedNotes(note)
  }


}