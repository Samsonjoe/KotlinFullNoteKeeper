package com.kotlin.notekeeper.ui.howMany

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kotlin.notekeeper.DataManager
import com.kotlin.notekeeper.R

class HowManyFragment : Fragment() {

  private lateinit var howManyViewModel: HowManyViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    howManyViewModel =
            ViewModelProvider(this).get(HowManyViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_how_many, container, false)

    /*val textView: TextView = root.findViewById(R.id.text_slideshow)
    sendViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    val message = getString(R.string.nav_how_many_message_format,
    DataManager.notes.size,DataManager.courses.size)

    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              message, Snackbar.LENGTH_LONG).show()
    }

    return root
  }
}