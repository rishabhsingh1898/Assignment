package com.rishabhsingh.asteroidneo.model

import com.rishabhsingh.asteroidneo.api.NasaApi
import com.rishabhsingh.asteroidneo.api.NasaClient
import com.rishabhsingh.asteroidneo.api.data.Feed
import com.rishabhsingh.asteroidneo.contracts.Contracts
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivityModel : Contracts.Model {
    private val nasaClient = NasaClient()

    override suspend fun getFeed(startDate: String, endDate: String) =
        nasaClient.api.getNeoFeed(startDate, endDate, NasaApi.CLIENT_ID)

}