package com.green.robot.greenmessanger.presenter.presenter.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.green.robot.greenmessanger.presenter.presenter.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Primary20,
    primaryContainer = Primary30,
    onPrimaryContainer = Primary90,
    primaryFixed = Primary90,
    onPrimaryFixed = Primary10,
    primaryFixedDim = Primary80,
    onPrimaryFixedVariant = Primary30,
    inversePrimary = Primary40,
    secondary = Secondary80,
    onSecondary = Secondary20,
    secondaryContainer = Secondary30,
    onSecondaryContainer = Secondary90,
    secondaryFixed = Secondary90,
    onSecondaryFixed = Secondary10,
    secondaryFixedDim = Secondary80,
    onSecondaryFixedVariant = Secondary30,
    tertiary = Tertiary80,
    onTertiary = Tertiary20,
    tertiaryContainer = Tertiary30,
    onTertiaryContainer = Tertiary90,
    tertiaryFixed = Tertiary90,
    onTertiaryFixed = Tertiary10,
    tertiaryFixedDim = Tertiary80,
    onTertiaryFixedVariant = Tertiary30,
    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,
    surface = Neutral6,
    onSurface = Neutral90,
    surfaceVariant = NeutralVariant30,
    onSurfaceVariant = Neutral30,
    surfaceContainerHighest = Neutral80,
    surfaceContainerHigh = Neutral17,
    surfaceContainer = Neutral12,
    surfaceContainerLow = Neutral10,
    surfaceContainerLowest = Neutral4,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    surfaceTint = Primary80,
    outline = NeutralVariant60,
    outlineVariant = NeutralVariant30,
    background = Neutral6,
    onBackground = Neutral90,
    surfaceBright = Neutral98,
    surfaceDim = Neutral6,
    scrim = Black
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = White,
    primaryContainer = Primary90,
    onPrimaryContainer = Primary30,
    primaryFixed = Primary90,
    onPrimaryFixed = Primary10,
    primaryFixedDim = Primary80,
    onPrimaryFixedVariant = Primary30,
    secondary = Secondary40,
    onSecondary = White,
    secondaryContainer = Secondary90,
    onSecondaryContainer = Secondary30,
    secondaryFixed = Secondary90,
    onSecondaryFixed = Secondary10,
    secondaryFixedDim = Secondary80,
    onSecondaryFixedVariant = Secondary30,
    tertiary = Tertiary40,
    onTertiary = White,
    tertiaryContainer = Tertiary90,
    onTertiaryContainer = Tertiary30,
    tertiaryFixed = Tertiary90,
    onTertiaryFixed = Tertiary10,
    tertiaryFixedDim = Tertiary80,
    onTertiaryFixedVariant = Tertiary30,
    error = Error40,
    onError = White,
    errorContainer = Error90,
    onErrorContainer = Error30,
    surface = Neutral98,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    surfaceContainerHighest = Neutral22,
    surfaceContainerHigh = Neutral92,
    surfaceContainer = Neutral94,
    surfaceContainerLow = Neutral96,
    surfaceContainerLowest = White,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    surfaceTint = Primary40,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant80,
    background = Neutral98,
    onBackground = Neutral10,
    surfaceBright = Neutral98,
    surfaceDim = Neutral87,
    scrim = Black
)

@Composable
fun GreenMessangerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}