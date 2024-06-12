package materialcomponent.communication

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import materialcomponent.common.BorderBox

fun LazyListScope.badges(modifier: Modifier = Modifier) {
    item {
        BorderBox(
            modifier = modifier,
            label = "badges",
        ) {
            NavigationBarItemWithBadge()
        }
    }
}

@Composable
fun NavigationBarItemWithBadge() {
    NavigationBar {
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge()
                    },
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                    )
                }
            },
            selected = false,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge {
                            val badgeNumber = "8"
                            Text(
                                badgeNumber,
                                modifier =
                                    Modifier.semantics {
                                        contentDescription = "$badgeNumber new notifications"
                                    },
                            )
                        }
                    },
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                    )
                }
            },
            selected = false,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge {
                            val badgeNumber = "88"
                            Text(
                                badgeNumber,
                                modifier =
                                    Modifier.semantics {
                                        contentDescription = "$badgeNumber new notifications"
                                    },
                            )
                        }
                    },
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                    )
                }
            },
            selected = false,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge {
                            val badgeNumber = "999+"
                            Text(
                                badgeNumber,
                                modifier =
                                    Modifier.semantics {
                                        contentDescription = "$badgeNumber new notifications"
                                    },
                            )
                        }
                    },
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                    )
                }
            },
            selected = false,
            onClick = {},
        )
    }
}
