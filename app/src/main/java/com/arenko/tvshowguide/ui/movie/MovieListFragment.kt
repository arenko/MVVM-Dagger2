package com.arenko.tvshowguide.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arenko.tvshowguide.MovieApplication
import com.arenko.tvshowguide.R
import com.arenko.tvshowguide.base.BaseFragment
import com.arenko.tvshowguide.utils.ViewModelFactory
import kotlinx.android.synthetic.main.layout_progress_spinner.*
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject


class MovieListFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModelMovie: MovieListViewModel
    private lateinit var movieAdapter: MovieAdapter

    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MovieApplication).appComponent.doInjection(this)
        viewModelMovie =
            ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelMovie.initializePaging()
        initAdapter()
        showResult()
    }

    private fun showResult() {
        viewModelMovie.movieList.observe(this, Observer {
            anim_loading.visibility = View.GONE
            movieAdapter.submitList(it)
        })
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter()
        rv_movie_list.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
        rv_movie_list.adapter = movieAdapter
        swipe_refresh_layout.setOnRefreshListener {
            refreshItems()
        }
    }

    fun refreshItems() {
        initAdapter()
        showResult()
        swipe_refresh_layout.isRefreshing = false
    }
}