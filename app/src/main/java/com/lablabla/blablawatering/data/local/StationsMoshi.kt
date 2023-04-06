package com.lablabla.blablawatering.data.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationsMoshi(
    val id: Int,
    val name: String = "",
    val gpio: Int = -1,
    @Json(name="is_on") val state: Boolean = false
)
