package com.demo.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = CustomGreen,
    background = CustomBlack,
    surface = CustomDarkGray,
    secondary = CustomWhite,
    tertiary = CustomOrange,
    primaryContainer = CustomGreen30,
    onPrimary = CustomBlack,
    onBackground = CustomWhite,
    onSurface = CustomWhite,
    onSurfaceVariant = CustomGray,
    error = CustomDarkRed,
    errorContainer = CustomDarkRed5,
)

@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}