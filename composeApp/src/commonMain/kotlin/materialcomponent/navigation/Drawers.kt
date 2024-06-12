package materialcomponent.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.JoinFull
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material3.Button
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import materialcomponent.AppScaffold
import materialcomponent.common.BorderBox
import navigation.Navigation

fun LazyListScope.drawers(
    onClick: (String) -> Unit,
    navController: NavController,
) {
    item {
        BorderBox(
            label = "Drawers",
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button(onClick = { navController.navigate(Navigation.DrawerNestedScreen.Normal().root) }) {
                    Text("Normal Drawer")
                }
                Button(onClick = { navController.navigate(Navigation.DrawerNestedScreen.Permanent().root) }) {
                    Text("Permanent Drawer")
                }
                Button(onClick = { navController.navigate(Navigation.DrawerNestedScreen.Dismissible().root) }) {
                    Text("Dismissible Drawer")
                }
            }
        }
    }
}

@Composable
fun ModalNavigationDrawerSample(navController: NavController) {
    AppScaffold(
        title = "",
        onBack = { navController.popBackStack() },
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        // icons to mimic drawer destinations
        val items =
            listOf(
                Icons.Default.AccountCircle,
                Icons.Default.Bookmarks,
                Icons.Default.CalendarMonth,
                Icons.Default.Dashboard,
                Icons.Default.Email,
                Icons.Default.Favorite,
                Icons.Default.Group,
                Icons.Default.Headphones,
                Icons.Default.Image,
                Icons.Default.JoinFull,
                Icons.Default.Keyboard,
                Icons.Default.Laptop,
                Icons.Default.Map,
                Icons.Default.Navigation,
                Icons.Default.Outbox,
                Icons.Default.PushPin,
                Icons.Default.QrCode,
                Icons.Default.Radio,
            )
        val selectedItem = remember { mutableStateOf(items[0]) }
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Spacer(Modifier.height(12.dp))
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(item.name.substringAfterLast(".")) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }
                }
            },
        )
    }
}

@Composable
fun PermanentNavigationDrawerSample(navController: NavController) {
    AppScaffold(
        title = "",
        onBack = { navController.popBackStack() },
    ) {
        // icons to mimic drawer destinations
        val items =
            listOf(
                Icons.Default.AccountCircle,
                Icons.Default.Bookmarks,
                Icons.Default.CalendarMonth,
                Icons.Default.Dashboard,
                Icons.Default.Email,
                Icons.Default.Favorite,
                Icons.Default.Group,
                Icons.Default.Headphones,
                Icons.Default.Image,
                Icons.Default.JoinFull,
                Icons.Default.Keyboard,
                Icons.Default.Laptop,
                Icons.Default.Map,
                Icons.Default.Navigation,
                Icons.Default.Outbox,
                Icons.Default.PushPin,
                Icons.Default.QrCode,
                Icons.Default.Radio,
            )
        val selectedItem = remember { mutableStateOf(items[0]) }
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(240.dp)) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Spacer(Modifier.height(12.dp))
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(item.name.substringAfterLast(".")) },
                                selected = item == selectedItem.value,
                                onClick = { selectedItem.value = item },
                                modifier = Modifier.padding(horizontal = 12.dp),
                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Application content")
                }
            },
        )
    }
}

@Composable
fun DismissibleNavigationDrawerSample(navController: NavController) {
    AppScaffold(
        title = "",
        onBack = { navController.popBackStack() },
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        // icons to mimic drawer destinations
        val items =
            listOf(
                Icons.Default.AccountCircle,
                Icons.Default.Bookmarks,
                Icons.Default.CalendarMonth,
                Icons.Default.Dashboard,
                Icons.Default.Email,
                Icons.Default.Favorite,
                Icons.Default.Group,
                Icons.Default.Headphones,
                Icons.Default.Image,
                Icons.Default.JoinFull,
                Icons.Default.Keyboard,
                Icons.Default.Laptop,
                Icons.Default.Map,
                Icons.Default.Navigation,
                Icons.Default.Outbox,
                Icons.Default.PushPin,
                Icons.Default.QrCode,
                Icons.Default.Radio,
            )
        val selectedItem = remember { mutableStateOf(items[0]) }

        DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DismissibleDrawerSheet {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Spacer(Modifier.height(12.dp))
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(item.name.substringAfterLast(".")) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(horizontal = 12.dp),
                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }
                }
            },
        )
    }
}
