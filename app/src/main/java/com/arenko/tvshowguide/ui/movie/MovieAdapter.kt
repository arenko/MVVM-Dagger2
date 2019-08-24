package com.arenko.tvshowguide.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arenko.tvshowguide.R
import com.arenko.tvshowguide.data.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(internal var listMovie: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_movie, viewGroup, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, i: Int) {
        var movie = this.listMovie[i];
        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movie.poster_path)
            .into(movieViewHolder.iv_movie)
        movieViewHolder.tv_movie_title.setText(movie.name);
        movieViewHolder.tv_movie_date.setText(movie.first_air_date);
        movieViewHolder.tv_vote_average.setText(movie.vote_average.toString());
    }

    override fun getItemCount(): Int {
        return this.listMovie.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class MovieViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv_movie_date: TextView
        var tv_vote_average: TextView
        var tv_movie_title: TextView
        var iv_movie: ImageView

        init {
            tv_movie_title = itemView.findViewById<View>(R.id.tv_movie_title) as TextView
            tv_movie_date = itemView.findViewById<View>(R.id.tv_movie_date) as TextView
            tv_vote_average = itemView.findViewById<View>(R.id.tv_vote_average) as TextView
            iv_movie = itemView.findViewById<View>(R.id.iv_movie) as ImageView

        }
    }
}
