package materialcomponent.communication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import materialcomponent.common.BorderBox

fun LazyListScope.progressIndicators(
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "progressIndicator"
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                var progress by remember { mutableStateOf(0.1f) }
                val animatedProgress by animateFloatAsState(
                    targetValue = progress,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                )
                Row {
                    Text("Set progress: ")
                    Slider(
                        modifier = Modifier.width(300.dp),
                        value = progress,
                        valueRange = 0f..1f,
                        onValueChange = { progress = it },
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CircularProgressIndicatorSample(animatedProgress)
                    LinearProgressIndicatorSample(animatedProgress)
                }

                Text("Indeterminate")
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    IndeterminateCircularProgressIndicatorSample()
                    IndeterminateLinearProgressIndicatorSample()
                }
            }
        }
    }
}

@Composable
fun LinearProgressIndicatorSample(
    animatedProgress: Float,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(
            progress = { animatedProgress },
        )
        Spacer(Modifier.requiredHeight(30.dp))

    }
}

@Composable
fun IndeterminateLinearProgressIndicatorSample() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator()
    }
}

@Composable
fun CircularProgressIndicatorSample(
    animatedProgress: Float,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(progress = { animatedProgress })
        Spacer(Modifier.requiredHeight(30.dp))
    }
}

@Composable
fun IndeterminateCircularProgressIndicatorSample() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator()
    }
}
