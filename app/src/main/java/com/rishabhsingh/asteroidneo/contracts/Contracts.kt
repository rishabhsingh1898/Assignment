package com.rishabhsingh.asteroidneo.contracts

import com.rishabhsingh.asteroidneo.api.data.Feed

interface Contracts {
    interface View {
        fun initView()
    }

    interface Presenter {
        fun getFastestAsteroid(): String
        fun getClosestAsteroid(): String
        fun getAverageSizeAsteroid(): String
    }

    interface Model {
        fun getFeed(startDate:String, endDate:String): Feed
    }
}