package com.example.siri_dhanyahub_1.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary          = ForestGreen,
    onPrimary        = CardWhite,
    primaryContainer = LightMint,
    onPrimaryContainer = ForestGreen,
    secondary        = EarthBrown,
    onSecondary      = CardWhite,
    secondaryContainer = LightAmber,
    onSecondaryContainer = EarthBrown,
    tertiary         = GoldenAmber,
    onTertiary       = CardWhite,
    background       = CreamWhite,
    onBackground     = DarkText,
    surface          = CardWhite,
    onSurface        = DarkText,
    surfaceVariant   = PaleMint,
    onSurfaceVariant = MediumText,
    outline          = DividerColor,
)

@Composable
fun SiriDhanyaHub1Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = ForestGreen.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}