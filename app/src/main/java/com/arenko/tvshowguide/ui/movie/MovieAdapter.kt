package com.arenko.tvshowguide.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arenko.tvshowguide.R
import com.arenko.tvshowguide.data.Movie
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
//    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, i: Int) {
//        if (this.listMovie.results != null && this.listMovie.results!!.size > 0) {
//            var movie = this.listMovie.results!!.get(i);
//            Picasso.get().load(Constants.imagePath + movie.poster_path)
//                .into(movieViewHolder.iv_movie)
//            movieViewHolder.tv_movie_title.setText(movie.name);
//            movieViewHolder.tv_movie_date.setText(movie.first_air_date);
//            movieViewHolder.tv_vote_average.setText(movie.vote_average.toString());
//        }
//    }

//    override fun getItemCount(): Int {
//        return this.listMovie.results!!.size
//    }

//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        super.onAttachedToRecyclerView(recyclerView)
//    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_movie_date = itemView.tv_movie_date
        val tv_vote_average = itemView.tv_vote_average
        val tv_movie_title = itemView.tv_movie_title
        val iv_movie = itemView.iv_movie

        fun bindPost(movie: Movie) {
            with(movie) {
                tv_movie_date.text = movie.first_air_date
                tv_vote_average.text = movie.vote_average.toString()
                tv_movie_title.text = movie.name
                Picasso.get().load(Constants.imagePath + movie.poster_path)
                    .into(iv_movie)
            }
        }
    }

//    inner class MovieViewHolder internal constructor(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        var tv_movie_date: TextView
//        var tv_vote_average: TextView
//        var tv_movie_title: TextView
//        var iv_movie: ImageView
//
//        init {
//            tv_movie_title = itemView.findViewById<View>(R.id.tv_movie_title) as TextView
//            tv_movie_date = itemView.findViewById<View>(R.id.tv_movie_date) as TextView
//            tv_vote_average = itemView.findViewById<View>(R.id.tv_vote_average) as TextView
//            iv_movie = itemView.findViewById<View>(R.id.iv_movie) as ImageView
//
//        }
//    }

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
