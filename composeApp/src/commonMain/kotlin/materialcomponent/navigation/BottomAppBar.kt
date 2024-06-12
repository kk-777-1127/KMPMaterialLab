package materialcomponent.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import materialcomponent.AppScaffold
import materialcomponent.SnackBarState
import materialcomponent.common.BorderBox
import navigation.Navigation

fun LazyListScope.bottomAppBar(
    onClick: (String) -> Unit,
    navController: NavController,
) {
    item {
        BorderBox(
            label = "BottomAppBar",
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                SimpleBottomAppBar(onClick)
                BottomAppBarWithFAB(onClick)
                Button(onClick = { navController.navigate(Navigation.BottomAppBarScreens.ExitBottomBarSample().root) }) {
                    Text("ExitAlwaysBottomAppBar")
                }
            }
        }
    }
}

@Composable
fun SimpleBottomAppBar(onClick: (String) -> Unit = {}) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onClick("menu") }) {
                Icon(Icons.Filled.Menu, contentDescription = "Localized description")
            }
        },
    )
}

@Composable
fun BottomAppBarWithFAB(onClick: (String) -> Unit = {}) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onClick("check") }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { onClick("edit") }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClick("add") },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
    )
}

@Composable
fun ExitAlwaysBottomAppBarScreen(navController: NavController) {
    val snackBarState = remember { mutableStateOf<SnackBarState?>(null) }
    val onClick: (String) -> Unit = { message ->
        snackBarState.value = SnackBarState(message = message)
    }
    AppScaffold(
        title = "Navigation",
        snackBarState = snackBarState.value,
        onBack = { navController.popBackStack() },
    ) {
        ExitAlwaysBottomAppBar(onClick)
    }
}

@Composable
private fun ExitAlwaysBottomAppBar(onClick: (String) -> Unit = {}) {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { onClick("check") }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { onClick("edit") }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Localized description")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(y = 4.dp),
                onClick = { onClick("add") },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    )
                }
            }
        },
    )
}
