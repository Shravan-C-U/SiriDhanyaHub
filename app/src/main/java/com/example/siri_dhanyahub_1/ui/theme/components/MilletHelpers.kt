package com.example.siri_dhanyahub_1.ui.theme.components

import androidx.compose.ui.graphics.Color

/**
 * Returns a theme color based on the millet type.
 */
fun milletColor(type: String): Color {
    return when (type.lowercase()) {
        "navane", "foxtail" -> Color(0xFFF4D03F) // Golden Yellow
        "sajje", "pearl" -> Color(0xFF85929E)   // Greyish
        "ragi", "finger" -> Color(0xFF922B21)   // Deep Red/Maroon
        "baragu", "proso" -> Color(0xFFEBEDEF)  // Off-white
        "oodalu", "barnyard" -> Color(0xFFD5DBDB)
        "araka", "kodo" -> Color(0xFF7D6608)
        else -> Color(0xFF2E7D32) // Default Green
    }
}

/**
 * Returns an emoji representation of the millet type.
 */
fun milletEmoji(type: String): String {
    return when (type.lowercase()) {
        "navane", "foxtail" -> "🌾"
        "sajje", "pearl" -> "🥖"
        "ragi", "finger" -> "🍪"
        "baragu", "proso" -> "🍚"
        "oodalu", "barnyard" -> "🌱"
        "araka", "kodo" -> "🥣"
        else -> "🌿"
    }
}
