package materialcomponent.containment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import materialcomponent.common.BorderBox
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

fun LazyListScope.listItem(
    modifier: Modifier = Modifier,
    onClickButton: (String) -> Unit = {},
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "ListItem"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Oneline")
                OneLineListItem()
                Text("TwoLine")
                TwoLineListItem()
                Text("ThreeLine")
                ThreeLineListItemWithOverlineAndSupporting()
                Text("ThreeLineListItemWithExtendedSupporting")
                ThreeLineListItemWithExtendedSupporting()
            }
        }
    }
}

@Composable
fun OneLineListItem() {
    Column {
        ListItem(
            headlineContent = { Text("One line list item with 24x24 icon") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            }
        )
        HorizontalDivider()
    }
}

@Composable
fun TwoLineListItem() {
    Column {
        ListItem(
            headlineContent = { Text("Two line list item with trailing") },
            supportingContent = { Text("Secondary text") },
            trailingContent = { Text("meta") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            }
        )
        HorizontalDivider()
    }
}

@Composable
fun ThreeLineListItemWithOverlineAndSupporting() {
    Column {
        ListItem(
            headlineContent = { Text("Three line list item") },
            overlineContent = { Text("OVERLINE") },
            supportingContent = { Text("Secondary text") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = { Text("meta") }
        )
        HorizontalDivider()
    }
}

@Composable
fun ThreeLineListItemWithExtendedSupporting() {
    Column {
        ListItem(
            headlineContent = { Text("Three line list item") },
            supportingContent = {
                Text("Secondary text that is long and perhaps goes onto another line")
            },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = { Text("meta") }
        )
        HorizontalDivider()
    }
}