package com.arenko.tvshowguide.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arenko.tvshowguide.data.ResultResponse
import com.arenko.tvshowguide.network.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val movieList = MutableLiveData<ResultResponse>()

    fun tvShowResponse(): MutableLiveData<ResultResponse> {
        return movieList
    }

    fun getTvShows(page: Int) {
        disposable.add(
            movieRepository.getPopularTvShows(page).subscribeOn(
                Schedulers.io()
            )
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<ResultResponse>() {
                    override fun onSuccess(value: ResultResponse) {
                        movieList.value = value;
                    }

                    override fun onError(e: Throwable) {
                        Log.w("", "error");
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
