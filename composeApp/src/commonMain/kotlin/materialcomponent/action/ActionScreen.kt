import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import materialcomponent.AppScaffold
import materialcomponent.SnackBarState
import materialcomponent.action.buttons
import materialcomponent.action.fabs
import materialcomponent.action.iconButtons
import materialcomponent.action.segmentedButtons

@Composable
fun ActionScreen(
    navController: NavController
) {
    val snackBarState = remember { mutableStateOf<SnackBarState?>(null) }
    AppScaffold(
        title = "Action",
        snackBarState = snackBarState.value,
        onBack = { navController.popBackStack() }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            buttons(
                onClickButton = {
                    snackBarState.value = SnackBarState("$it clicked")
                }
            )

            fabs(
                onClickButton = {
                    snackBarState.value = SnackBarState("$it clicked")
                }
            )

            iconButtons(
                onClickButton = {
                    snackBarState.value = SnackBarState("$it clicked")
                }
            )

            segmentedButtons()
        }
    }
}

@Composable
fun Dummy(name: String) {
    Box(Modifier.fillMaxSize()) {
        Text(name, Modifier.align(Alignment.Center))
    }
}