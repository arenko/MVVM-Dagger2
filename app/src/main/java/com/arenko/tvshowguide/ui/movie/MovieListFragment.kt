package com.arenko.tvshowguide.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arenko.tvshowguide.MovieApplication
import com.arenko.tvshowguide.R
import com.arenko.tvshowguide.base.BaseFragment
import com.arenko.tvshowguide.data.Movie
import com.arenko.tvshowguide.data.ResultResponse
import com.arenko.tvshowguide.utils.ViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject

class MovieListFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModelMovie: MovieListViewModel

    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MovieApplication).appComponent.doInjection(this)
        viewModelMovie =
            ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        viewModelMovie.tvShowResponse().observe(
            this,
            Observer<ResultResponse> { this.consumeResponse(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        viewModelMovie.getTvShows(1);
    }

    private fun consumeResponse(response: ResultResponse) {
        if (response.results!!.size > 0) {
            setRecyclerViewAdapter(response.results!!)
        }
    }

    private fun setRecyclerViewAdapter(movie: List<Movie>) {
        val adapter = MovieAdapter(movie)
        rv_movie_list.adapter = adapter
    }


    private fun setupRecyclerView() {
        rv_movie_list.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(baseContext)
        rv_movie_list.layoutManager = linearLayoutManager
        rv_movie_list.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}
