package io.kk__777.kmpmateriallab

import App
import WindowSize
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass.Companion.COMPACT
import androidx.window.core.layout.WindowWidthSizeClass.Companion.EXPANDED
import androidx.window.core.layout.WindowWidthSizeClass.Companion.MEDIUM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSize =
                when (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
                    MEDIUM -> WindowSize.MEDIUM
                    EXPANDED -> WindowSize.EXPANDED
                    COMPACT -> WindowSize.COMPACT
                    else -> WindowSize.COMPACT
                }
            App(windowSize)
        }
    }
}
