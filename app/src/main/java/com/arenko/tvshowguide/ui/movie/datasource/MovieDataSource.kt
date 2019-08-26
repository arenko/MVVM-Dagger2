package com.arenko.tvshowguide.ui.movie.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.arenko.tvshowguide.model.Movie
import com.arenko.tvshowguide.model.State
import com.arenko.tvshowguide.network.MovieRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val movieRepository: MovieRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    private var retryCompletable: Completable? = null
    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(
            movieRepository.getPopularTvShows(1)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.results!!.toMutableList(), null, 2)
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            movieRepository.getPopularTvShows(params.key)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.results!!.toMutableList(), params.key + 1)
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }


    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    //In case we want to use afte error
    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}