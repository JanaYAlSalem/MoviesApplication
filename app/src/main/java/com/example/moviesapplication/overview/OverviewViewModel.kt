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


//    val title=MutableLiveData<String>()

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _descriptions = MutableLiveData<String>()
    val descriptions: LiveData<String> = _descriptions

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        getItemMovies()
    }


    private fun getItemMovies() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING // loading in first call of getflagPhotos.
            try {
//
                val listResult = MoviesApi.retrofitService.getItemMovies().results
                _infoItem.value = listResult
//               _Num.value = MoviesApi.retrofitService.getItemMovies().results
                _status.value = MoviesApiStatus.DONE // when data is returned
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR // when data is not return
                _infoItem.value = listOf()

            }
        }
    }
    fun informationll(index:Int){
        var item = _infoItem.value?.get(index)

        _photos.value= item?.posterPath
        _title.value=item?.title
        _descriptions.value=item?.overview
        _date.value=item?.releaseDate

    }



}