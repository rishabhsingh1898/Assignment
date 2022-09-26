package com.rishabhsingh.asteroidneo.api

import com.rishabhsingh.asteroidneo.BuildConfig
import com.rishabhsingh.asteroidneo.api.data.Feed
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    companion object {
        const val BASE_URL = "https://api.nasa.gov/neo/"
        const val CLIENT_ID = BuildConfig.NASA_API_KEY
    }

    @GET("rest/v1/feed")
     suspend fun getNeoFeed(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Response<Feed>
}