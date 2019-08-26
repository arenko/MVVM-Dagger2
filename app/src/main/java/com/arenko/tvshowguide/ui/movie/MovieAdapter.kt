package com.arenko.tvshowguide.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arenko.tvshowguide.R
import com.arenko.tvshowguide.model.Movie
import com.arenko.tvshowguide.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapter() :
    PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_movie, viewGroup, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_movie_date = itemView.tv_movie_date
        val tv_vote_average = itemView.tv_vote_average
        val tv_movie_title = itemView.tv_movie_title
        val iv_movie = itemView.iv_movie

        fun bindPost(movie: Movie) {
                tv_movie_date.text = movie.first_air_date
                tv_vote_average.text = movie.vote_average.toString()
                tv_movie_title.text = movie.name
                Picasso.get().load(Constants.imagePath + movie.poster_path)
                    .into(iv_movie)

        }
    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
