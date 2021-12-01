package com.example.moviesapplication.network

import com.example.moviesapplication.overview.OverviewViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org"
private const val Key = "91e0a4801858875d926759b32da2ac56"
private lateinit var viewModel: OverviewViewModel


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MoviesApiService {

    /*
    * return types of movies
    * (values as u like u can call it)
     */
    @GET("/3/movie/{type}?api_key=$Key")
    suspend fun getItemMovies(@Path("type") typeMovies: String): Response

    /*
     * return genres of movies
     * (values call it like website specific query)
     */
    @GET("/3/discover/movie?api_key=$Key")
    suspend fun getGenersMovies(@Query("with_genres") genreId: Int): Response


}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
}