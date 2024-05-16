package materialcomponent.action

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import materialcomponent.common.BorderBox

fun LazyListScope.iconButtons(
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "IconButtons"
        ) {
            FlowRow(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { onClickButton("IconButton") }) {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
                IconButton(onClick = { onClickButton("TintedIconButton") }) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                        contentDescription = "Localized description",
                        tint = Color.Red
                    )
                }
                IconToggleButtonSample(onClickButton = { onClickButton("IconToggleButton") })
                FilledIconButton(onClick = { onClickButton("FilledIconButton") }) {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
                FilledIconToggleButtonSample(onClickButton = { onClickButton("FilledIconToggleButton") } )
                FilledTonalIconButton(onClick = { onClickButton("FilledTonalIconButton") }) {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
                FilledTonalIconToggleButtonSample(onClickButton = { onClickButton("FilledTonalIconToggleButton") } )
                OutlinedIconButton(onClick = { onClickButton("OutlinedIconButton") }) {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
                OutlinedIconToggleButtonSample(onClickButton = { onClickButton("OutlinedIconToggleButton") })
            }
        }
    }
}

@Composable
fun IconToggleButtonSample(onClickButton: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(checked = checked, onCheckedChange = {
        checked = it
        onClickButton()
    }) {
        if (checked) {
            Icon(Icons.Filled.Lock, contentDescription = "Localized description")
        } else {
            Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
        }
    }
}

@Composable
fun FilledIconToggleButtonSample(onClickButton: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    FilledIconToggleButton(checked = checked, onCheckedChange = {
        checked = it
        onClickButton()
    }) {
        if (checked) {
            Icon(Icons.Filled.Lock, contentDescription = "Localized description")
        } else {
            Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
        }
    }
}

@Composable
fun FilledTonalIconToggleButtonSample(onClickButton: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    FilledTonalIconToggleButton(checked = checked, onCheckedChange = {
        checked = it
        onClickButton()
    }) {
        if (checked) {
            Icon(Icons.Filled.Lock, contentDescription = "Localized description")
        } else {
            Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
        }
    }
}

@Composable
fun OutlinedIconToggleButtonSample(onClickButton: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    OutlinedIconToggleButton(checked = checked, onCheckedChange = {
        checked = it
        onClickButton()
    }) {
        if (checked) {
            Icon(Icons.Filled.Lock, contentDescription = "Localized description")
        } else {
            Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
        }
    }
}