package materialcomponent.navigation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import materialcomponent.AppScaffold
import materialcomponent.SnackBarState

@Composable
fun NavigationScreen(
    navController: NavController
) {
    val snackBarState = remember { mutableStateOf<SnackBarState?>(null) }
    val onClick: (String) -> Unit = { message ->
        snackBarState.value = SnackBarState(message = message )
    }
    AppScaffold(
        title = "Navigation",
        snackBarState = snackBarState.value,
        onBack = { navController.popBackStack() }
    ) {
        LazyColumn {
            bottomAppBar(onClick, navController)
            navigationBarOrRail(onClick, navController)
        }
    }
}