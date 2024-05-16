package materialcomponent.action

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import materialcomponent.common.BorderBox

fun LazyListScope.buttons(
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "Buttons"
        ) {
            FlowRow(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { onClickButton("FilledButton") }) {
                    Text("FilledButton")
                }
                ElevatedButton(onClick = { onClickButton("ElevatedButton") }) {
                    Text("ElevatedButton")
                }
                FilledTonalButton(onClick = { onClickButton("FilledTonalButton") }) {
                    Text("FilledTonalButton")
                }
                OutlinedButton(onClick = { onClickButton("OutlinedButton") }) {
                    Text("OutlinedButton")
                }
                TextButton(onClick = { onClickButton("TextButton") }) {
                    Text("TextButton")
                }
                // TODO Button with Icon
            }
        }
    }
}

fun LazyListScope.fabs(
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "FloatingActionButton"
        ) {
            FlowRow(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FloatingActionButton(
                    onClick = { onClickButton("FloatingActionButton") }
                ) { Icon(Icons.Filled.Add, "Localized description") }

                SmallFloatingActionButton(
                    onClick = { onClickButton("SmallFloatingActionButton") }
                ) { Icon(Icons.Filled.Add, "Localized description") }

                LargeFloatingActionButton(
                    onClick = { onClickButton("LargeFloatingActionButton") }
                ) { Icon(Icons.Filled.Add, "Localized description") }

                ExtendedFloatingActionButton(
                    onClick = { onClickButton("ExtendedFloatingActionButton") }
                ) { Text(text = "Extended FAB") }

                ExtendedFloatingActionButton(
                    onClick = { onClickButton("ExtendedFloatingActionButton with Icon") },
                    icon = { Icon(Icons.Filled.Add, "Localized description") },
                    text = { Text(text = "Extended FAB") }
                )

                AnimatedExtendedFloatingActionButton(onClickButton)
            }
        }
    }
}

@Composable
fun AnimatedExtendedFloatingActionButton(
    onClickButton: (String) -> Unit,
) {
    var expandedFab by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        onClick = {
            expandedFab = !expandedFab
            onClickButton("AnimatedExtendedFloatingActionButton")
        },
        expanded = expandedFab,
        icon = { Icon(Icons.Filled.Add, "Localized Description") },
        text = { Text(text = "Extended FAB") },
    )
}