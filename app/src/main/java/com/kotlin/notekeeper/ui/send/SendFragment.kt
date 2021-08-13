package com.kotlin.notekeeper.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kotlin.notekeeper.R

class SendFragment : Fragment() {

  private lateinit var sendViewModel: SendViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    sendViewModel =
            ViewModelProvider(this).get(SendViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_send, container, false)

    /*val textView: TextView = root.findViewById(R.id.text_slideshow)
    sendViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              "Send", Snackbar.LENGTH_LONG).show()
    }

    return root
  }
}