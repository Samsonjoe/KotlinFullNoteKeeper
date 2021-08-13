package com.kotlin.notekeeper.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlin.notekeeper.CourseRecyclerAdapter
import com.kotlin.notekeeper.DataManager
import com.kotlin.notekeeper.R

class CourseFragment : Fragment() {

  private lateinit var courseViewModel: CourseViewModel

  private val courseLayoutManager by lazy {
    GridLayoutManager(activity,resources.getInteger(R.integer.course_grid_span))
  }

  private val courseRecyclerAdapter by lazy {
    context?.applicationContext?.let {CourseRecyclerAdapter(it,DataManager.courses.values.toList())}
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    courseViewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_course, container, false)

    /*val textView: TextView = root.findViewById(R.id.text_gallery)
    courseViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/

    root.findViewById<RecyclerView>(R.id.listItemsCourses).layoutManager = courseLayoutManager
    root.findViewById<RecyclerView>(R.id.listItemsCourses).adapter = courseRecyclerAdapter

    getActivity()?.let {
      Snackbar.make(it.findViewById(android.R.id.content),
              "Courses", Snackbar.LENGTH_LONG).show()
    }

    return root
  }

}