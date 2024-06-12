package materialcomponent.containment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import materialcomponent.common.BorderBox

// TODO FullScreenDialog https://m3.material.io/components/dialogs/guidelines#007536b9-76b1-474a-a152-2f340caaff6f
fun LazyListScope.dialogs(
    modifier: Modifier = Modifier,
    onClickButton: (String) -> Unit = {},
) {
    item {
        val openDialog = remember { mutableStateListOf(false, false, false) }
        val close: () -> Unit = {
            openDialog.forEachIndexed { i, _ ->
                openDialog[i] = false
            }
        }
        val open: (index: Int) -> Unit = { index ->
            close()
            openDialog[index] = true
        }
        BorderBox(
            modifier = modifier,
            label = "Dialog",
        ) {
            FlowRow {
                Button(onClick = { open(0) }) {
                    Text("AlertDialog")
                }
                Button(onClick = { open(1) }) {
                    Text("AlertDialogWithIcon")
                }
                Button(onClick = { open(2) }) {
                    Text("BasicAlertDialog")
                }
            }
        }
        AlertDialogSample(openDialog[0], close)
        AlertDialogWithIconSample(openDialog[1], close)
        BasicAlertDialogSample(openDialog[2], close)
    }
}

@Composable
fun AlertDialogSample(
    open: Boolean,
    close: () -> Unit,
) {
    if (open) {
        AlertDialog(
            onDismissRequest = close,
            title = {
                Text(text = "Title")
            },
            text = {
                Text(text = "Turned on by default")
            },
            confirmButton = {
                TextButton(
                    onClick = close,
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = close,
                ) {
                    Text("Dismiss")
                }
            },
        )
    }
}

@Composable
fun AlertDialogWithIconSample(
    open: Boolean,
    close: () -> Unit,
) {
    if (open) {
        AlertDialog(
            onDismissRequest = close,
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            title = {
                Text(text = "Title")
            },
            text = {
                Text(
                    "This area typically contains the supportive text " +
                        "which presents the details regarding the Dialog's purpose.",
                )
            },
            confirmButton = {
                TextButton(
                    onClick = close,
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = close,
                ) {
                    Text("Dismiss")
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicAlertDialogSample(
    open: Boolean,
    close: () -> Unit,
) {
    if (open) {
        BasicAlertDialog(
            onDismissRequest = close,
        ) {
            Surface(
                modifier =
                    Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text =
                            "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose.",
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = close,
                        modifier = Modifier.align(Alignment.End),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}
