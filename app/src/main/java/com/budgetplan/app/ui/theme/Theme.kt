package com.budgetplan.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GreenPrimary = Color(0xFF1B8A5A)
val GreenContainer = Color(0xFFB7F0D4)
val RedAccent = Color(0xFFE53935)
val RedContainer = Color(0xFFFFDAD6)
val BluePrimary = Color(0xFF1565C0)
val SurfaceColor = Color(0xFFF8FAF9)
val CardColor = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    primaryContainer = GreenContainer,
    onPrimaryContainer = Color(0xFF002114),
    secondary = BluePrimary,
    onSecondary = Color.White,
    surface = SurfaceColor,
    onSurface = Color(0xFF191C1B),
    background = SurfaceColor,
    error = RedAccent,
    errorContainer = RedContainer,
)

@Composable
fun BudgetPlanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}
