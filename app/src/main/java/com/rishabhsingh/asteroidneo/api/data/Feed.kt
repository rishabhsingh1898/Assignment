package com.rishabhsingh.asteroidneo.api.data

data class Feed(
    val near_earth_objects: Map<String, List<Asteroids>>
)

data class Asteroids(
    val id: String,
    val estimated_diameter: Diameter,
    val close_approach_data: List<Distance>
)

data class Diameter(
    val kilometers: Kilometer
)

data class Kilometer(
    val estimated_diameter_max: String,
    val estimated_diameter_min: String
)


data class Distance(
    val miss_distance: MissDistance
)

data class MissDistance(
    val kilometers: String
)







