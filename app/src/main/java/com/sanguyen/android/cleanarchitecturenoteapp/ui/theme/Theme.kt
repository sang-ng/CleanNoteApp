package com.sanguyen.android.cleanarchitecturenoteapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)


@Composable
fun CleanArchitectureNoteAppTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = DarkGray
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }
}