package com.example.moviesapplication.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.network.MoviesApi
import com.example.moviesapplication.network.Response
import com.example.moviesapplication.network.ResultsItem
import kotlinx.coroutines.launch

enum class MoviesApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    /*
    * status of notworks
     */
    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    private val _infoItem = MutableLiveData<List<ResultsItem?>?>()
    val infoItem: LiveData<List<ResultsItem?>?> = _infoItem


    private val _photos = MutableLiveData<String>()
    val photos: LiveData<String> = _photos


    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _descriptions = MutableLiveData<String>()
    val descriptions: LiveData<String> = _descriptions

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        getItemMovies("popular")
    }

fun getItemMovies(type : String ){

    viewModelScope.launch {
        _status.value = MoviesApiStatus.LOADING
        try {
            when (type) {
                "popular" ->
                    _infoItem.value = MoviesApi.retrofitService.getItemMovies("popular").results?.sortedBy { it?.releaseDate}
                "upcoming" ->
                    _infoItem.value = MoviesApi.retrofitService.getItemMovies("upcoming").results?.sortedBy { it?.releaseDate}
                "now_playing" ->
                    _infoItem.value = MoviesApi.retrofitService.getItemMovies("now_playing").results?.sortedBy { it?.releaseDate}
                "top_rated" ->
                    _infoItem.value = MoviesApi.retrofitService.getItemMovies("top_rated").results?.sortedBy { it?.releaseDate}

                else ->
                    _infoItem.value = MoviesApi.retrofitService.getItemMovies("popular").results?.sortedBy { it?.releaseDate}
            }
            _status.value = MoviesApiStatus.DONE

        } catch (e: Exception) {
            _status.value = MoviesApiStatus.ERROR
            _infoItem.value = listOf()

        }
    }
}

    fun getGenersMovies (genreId : Int) {

        viewModelScope.launch {
            _infoItem.value= MoviesApi.retrofitService.getGenersMovies(genreId).results
        }
    }


    fun informationll(index: Int) {
        var item = _infoItem.value?.get(index)

        _photos.value = item?.posterPath
        _title.value = item?.title
        _descriptions.value = item?.overview
        _date.value = item?.releaseDate
    }

}