package materialcomponent.containment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import materialcomponent.AppScaffold
import materialcomponent.SnackBarState

@Composable
fun ContainmentScreen(
    navController: NavController
) {
    val snackBarState = remember { mutableStateOf<SnackBarState?>(null) }
    AppScaffold(
        title = "Containment",
        snackBarState = snackBarState.value,
        onBack = { navController.popBackStack() }
    ) {
        LazyColumn {
            bottomSheets(navController)
            cards(
                onClickButton = { action ->
                    snackBarState.value = SnackBarState(
                        message = "Clicked $action"
                    )
                }
            )
            carousel()
            dialogs()
        }
    }
}
