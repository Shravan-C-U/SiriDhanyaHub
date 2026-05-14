package com.example.siri_dhanyahub_1.data

/**
 * Data model for millet market prices.
 */
data class MandiPrice(
    val id: Int,
    val city: String,
    val milletType: String,
    val currentPrice: Double,
    val prevPrice: Double,
    val sevenDayHigh: Double,
    val sevenDayLow: Double
)
