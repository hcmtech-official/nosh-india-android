package com.noshindia.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NoshColors = lightColorScheme(
    primary = Maroon,
    onPrimary = Color.White,
    secondary = Saffron,
    onSecondary = Cocoa,
    background = Cream,
    onBackground = Cocoa,
    surface = CardSurface,
    onSurface = Cocoa,
    surfaceVariant = CardSurface,
    onSurfaceVariant = Cocoa,
    outline = Hairline,
    error = Maroon,
    onError = Color.White
)

@Composable
fun NoshIndiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NoshColors,
        typography = NoshTypography,
        content = content
    )
}
