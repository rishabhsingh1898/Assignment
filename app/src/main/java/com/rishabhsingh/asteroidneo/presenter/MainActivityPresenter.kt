package com.rishabhsingh.asteroidneo.presenter

import com.rishabhsingh.asteroidneo.api.data.Feed
import com.rishabhsingh.asteroidneo.contracts.Contracts
import com.rishabhsingh.asteroidneo.model.MainActivityModel
import com.rishabhsingh.asteroidneo.ui.MainActivity
import kotlinx.coroutines.*

class MainActivityPresenter(v: MainActivity): Contracts.Presenter {

    private var view = v
    lateinit var feed:Feed
    private var model: Contracts.Model = MainActivityModel()


    init {
        view.initView()
    }

    override fun getFastestAsteroid(): String {
        lateinit var fastest:String
        lateinit var id:String
            for (item in feed.near_earth_objects) {

                var temp = item.value[0].close_approach_data[0].relative_velocity.kilometers_per_hour
                id = item.value[0].id

                for (obj in item.value) {
                    if (obj.close_approach_data[0]
                            .relative_velocity.kilometers_per_hour > temp
                    ) {
                        temp = obj.close_approach_data[0].relative_velocity.kilometers_per_hour
                        id = obj.id
                    }
                }
                fastest = temp
            }
        return "Asteroid ID:${id} with speed ${fastest}Km/h"
    }

    override fun getClosestAsteroid(): String {
        lateinit var closest:String
        lateinit var id:String
        for (item in feed.near_earth_objects) {

            var temp = item.value[0].close_approach_data[0].miss_distance.kilometers
            id = item.value[0].id

            for (obj in item.value) {
                if (obj.close_approach_data[0]
                        .miss_distance.kilometers < temp
                ) {
                    temp = obj.close_approach_data[0].miss_distance.kilometers
                    id = obj.id
                }
            }
            closest = temp
        }
        return "Asteroid ID:${id} with speed ${closest}Km/h"
    }

    override fun getAverageSizeAsteroid(): String {
        val size = feed.near_earth_objects.size
        var avg: Double = 0.0
        for (item in feed.near_earth_objects) {

            for (obj in item.value) {
                avg += obj.estimated_diameter.kilometers.estimated_diameter_max.toDouble()
            }
        }
        return "${avg/size}Km/h"
    }

    override  fun requestFeed(startDate: String, endDate: String) {

        CoroutineScope(Dispatchers.IO).launch {
            model.getFeed(startDate, endDate).body()?.let {
                feed  = it
                CoroutineScope(Dispatchers.Main).launch() {
                    view.updateData(
                        getFastestAsteroid(),
                        getClosestAsteroid(),
                        getAverageSizeAsteroid()
                    )
                }
            }
        }
    }

}