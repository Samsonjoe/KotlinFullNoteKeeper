package com.kotlin.notekeeper.ui.recentlyViewedNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlin.notekeeper.DataManager
import com.kotlin.notekeeper.R

class RecentlyViewedNotesFragment : Fragment() {

  private lateinit var recentlyViewedNotesViewModel: RecentlyViewedNotesViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    recentlyViewedNotesViewModel =
            ViewModelProvider(this).get(RecentlyViewedNotesViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_recently_viewed_notes, container, false)

    /*val textView: TextView = root.findViewById(R.id.text_slideshow)
    sendViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    /*root.findViewById<RecyclerView>(R.id.listItemsRecentlyViewedNotes).layoutManager = noteLayoutManager
    root.findViewById<RecyclerView>(R.id.listItemsRecentlyViewedNotes).adapter = noteRecyclerAdapter*/

    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              "Recently viewed Notes", Snackbar.LENGTH_LONG).show()
    }


    return root
  }

  /*private fun displayRecentlyNotes() {
    listItems.layoutManager = noteLayoutManager
    listItems.adapter = recentlyViewNotesRecycleAdapter

    nav_view.menu.findItem(R.id.nav_courses).isCheckable = true
  }*/

}