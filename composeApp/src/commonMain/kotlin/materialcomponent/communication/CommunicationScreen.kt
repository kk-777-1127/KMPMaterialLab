package materialcomponent.communication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import materialcomponent.AppScaffold
import materialcomponent.SnackBarState

@Composable
fun CommunicationScreen(navController: NavController) {
    val snackBarState = remember { mutableStateOf<SnackBarState?>(null) }
    AppScaffold(
        title = "Communication",
        snackBarState = snackBarState.value,
        onBack = { navController.popBackStack() },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            badges()
            progressIndicators()
            snackBar(
                onClickButton = {
                    snackBarState.value = SnackBarState("SnackBar ${(1..10).random()}", "action")
                },
            )
            toolTips()
        }
    }
}
