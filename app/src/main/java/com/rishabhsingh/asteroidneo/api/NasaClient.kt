package com.rishabhsingh.asteroidneo.api

import com.rishabhsingh.asteroidneo.api.NasaApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaClient {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(NasaApi::class.java)
}