package com.example.siri_dhanyahub_1.data

data class Recipe(
    val id: Int,
    val name: String,
    val milletType: String,
    val imageUrl: String,
    val healthBenefit: String,
    val description: String,
    val steps: List<String>,
    val isFavourite: Boolean = false
)