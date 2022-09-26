package com.rishabhsingh.asteroidneo.contracts

import com.rishabhsingh.asteroidneo.api.data.Feed
import retrofit2.Response

interface Contracts {
    interface View {
        fun initView()
        fun updateData(fastest :String, average : String, minimum : String)
    }

    interface Presenter {
        fun getFastestAsteroid(): String
        fun getClosestAsteroid(): String
        fun getAverageSizeAsteroid(): String
        fun requestFeed(startDate:String, endDate:String)
    }

    interface Model {
        suspend fun getFeed(startDate:String, endDate:String): Response<Feed>
    }
}