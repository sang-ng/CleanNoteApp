package com.sanguyen.android.cleanarchitecturenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Orange,
    onPrimary = White,
    primaryVariant = White,
    background = Black,
    onBackground = Color.White,
    secondary = Orange,
    surface = Color.White,
)

private val LightColorPalette = darkColors(
    primary = Orange,
    onPrimary = White,
    primaryVariant = Color.White,
    background = LightBlue,
    onBackground = DarkGray,
    secondary = Orange,
    surface = Color.White,
)

@Composable
fun CleanArchitectureNoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Black
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = LightBlue
        )
    }

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}