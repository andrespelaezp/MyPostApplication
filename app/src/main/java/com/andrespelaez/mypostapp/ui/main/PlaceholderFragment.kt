package com.andrespelaez.mypostapp.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.andrespelaez.mypostapp.R
import com.andrespelaez.mypostapp.data.Post
import com.andrespelaez.mypostapp.data.PostType
import com.andrespelaez.mypostapp.di.Injectable
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), Injectable {

    private lateinit var pageViewModel: PostViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var posts : MutableList<Post>

    lateinit var rvPosts : RecyclerView
    lateinit var rvAdapter : AdapterPost

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pageViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java).apply {
            setType(arguments?.getString(ARG_SECTION_NUMBER)?.equals(PostType.ALL))
        }

        pageViewModel.posts.observe(this, Observer {
            posts = it.data!!
            rvAdapter.setData(it.data!!)
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        rvAdapter = AdapterPost(null)
        rvPosts = root.findViewById(R.id.posts_list)

        return root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: String): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}