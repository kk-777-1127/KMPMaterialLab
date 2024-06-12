package materialcomponent.communication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import materialcomponent.common.BorderBox

fun LazyListScope.toolTips(modifier: Modifier = Modifier) {
    item {
        BorderBox(
            modifier = modifier,
            label = "toolTips",
        ) {
            FlowRow(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PlainTooltipSample()
                PlainTooltipWithManualInvocationSample()
                RichTooltipSample()
                RichTooltipWithManualInvocationSample()
            }
        }
    }
}

@Composable
fun PlainTooltipSample() {
    val state = rememberTooltipState()
    val scope = rememberCoroutineScope()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text("Add to favorites")
            }
        },
        state = state,
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    state.show()
                }
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Localized Description",
            )
        }
    }
}

@Composable
fun PlainTooltipWithManualInvocationSample() {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip {
                    Text("Add to list")
                }
            },
            state = tooltipState,
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Localized Description",
            )
        }
        Spacer(Modifier.requiredHeight(30.dp))
        OutlinedButton(
            onClick = { scope.launch { tooltipState.show() } },
        ) {
            Text("Display tooltip")
        }
    }
}

@Composable
fun RichTooltipSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    TextButton(
                        onClick = { scope.launch { tooltipState.dismiss() } },
                    ) { Text(richTooltipActionText) }
                },
            ) {
                Text(richTooltipText)
            }
        },
        state = tooltipState,
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    tooltipState.show()
                }
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Localized Description",
            )
        }
    }
}

@Composable
fun RichTooltipWithManualInvocationSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
            tooltip = {
                RichTooltip(
                    title = { Text(richTooltipSubheadText) },
                    action = {
                        TextButton(
                            onClick = {
                                scope.launch {
                                    tooltipState.dismiss()
                                }
                            },
                        ) { Text(richTooltipActionText) }
                    },
                ) { Text(richTooltipText) }
            },
            state = tooltipState,
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Localized Description",
            )
        }
        Spacer(Modifier.requiredHeight(30.dp))
        OutlinedButton(
            onClick = { scope.launch { tooltipState.show() } },
        ) {
            Text("Display tooltip")
        }
    }
}

const val richTooltipSubheadText = "Permissions"
const val richTooltipText =
    "Configure permissions for selected service accounts. " +
        "You can add and remove service account members and assign roles to them. " +
        "Visit go/permissions for details"
const val richTooltipActionText = "Request Access"
