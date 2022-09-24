package com.rishabhsingh.asteroidneo.model

import com.rishabhsingh.asteroidneo.api.NasaApi
import com.rishabhsingh.asteroidneo.api.NasaClient
import com.rishabhsingh.asteroidneo.api.data.Feed
import com.rishabhsingh.asteroidneo.contracts.Contracts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityModel : Contracts.Model {
    private val nasaClient = NasaClient()
    lateinit var feed:Feed

    override fun getFeed(startDate:String, endDate:String): Feed {

        val call = nasaClient.api.getNeoFeed(startDate, endDate, NasaApi.CLIENT_ID)
        call.enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                response.body()?.near_earth_objects!!.let {
                    feed.near_earth_objects = it
                }
            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return feed
    }
}