import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KMPMaterialLab",
        ) {
            // TODO caluculate window size
            App(WindowSize.EXPANDED)
        }
    }
