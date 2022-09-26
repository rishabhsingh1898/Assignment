package com.rishabhsingh.asteroidneo.api

import com.rishabhsingh.asteroidneo.api.NasaApi.Companion.CLIENT_ID
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class NasaClientTest {
    private val nasaClient = NasaClient()

    @Test
    fun `getFeeds`() {
        val startDate = "2020-01-01"
        val endDate = "2020-01-02"

        runBlocking {
            val response = nasaClient.api.getNeoFeed(startDate, endDate, CLIENT_ID)
            Assert.assertNotNull(response.body()?.near_earth_objects)
        }

    }
}