package com.kotlin.notekeeper.ui.share

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

class ShareFragment : Fragment() {

  private lateinit var shareViewModel: ShareViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    shareViewModel =
            ViewModelProvider(this).get(ShareViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_share, container, false)

   /* val textView: TextView = root.findViewById(R.id.text_slideshow)
    shareViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              getString(R.string.nav_share_message), Snackbar.LENGTH_LONG).show()
    }

    return root
  }
}