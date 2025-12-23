package com.example.worldtatu.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    primary = NeonRose,
    onPrimary = TextPrimary,
    primaryContainer = Smoke,
    secondary = CyanGlow,
    onSecondary = InkBlack,
    background = InkBlack,
    onBackground = TextPrimary,
    surface = Graphite,
    onSurface = TextPrimary,
    surfaceVariant = Carbon,
    onSurfaceVariant = TextSecondary,
)

private val LightColors = lightColorScheme(
    primary = NeonRose,
    onPrimary = Color.White,
    primaryContainer = Smoke,
    secondary = CyanGlow,
    onSecondary = InkBlack,
    background = Color.White,
    onBackground = InkBlack,
    surface = Color.White,
    onSurface = InkBlack,
)

@Composable
fun WorldTatuTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

