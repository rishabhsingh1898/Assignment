package com.rishabhsingh.asteroidneo.api

import com.rishabhsingh.asteroidneo.api.NasaApi.Companion.CLIENT_ID
import org.junit.Assert
import org.junit.Test


class NasaClientTest {
    private val nasaClient = NasaClient()

    @Test
    fun `getFeeds`() {
        val startDate = "2020-01-01"
        val endDate = "2020-01-02"

        val feed = nasaClient.api.getNeoFeed(startDate, endDate, CLIENT_ID).execute()
        Assert.assertNotNull(feed.body()?.near_earth_objects)
    }
}