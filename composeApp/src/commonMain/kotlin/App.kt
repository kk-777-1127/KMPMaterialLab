import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import navigation.createGraph
import navigation.getAppRoot
import navigation.getRoots
import theme.AppTheme

@Composable
fun App(
    windowSize: WindowSize
) {
    /*
    TODO
        設定画面から seed colorを変える術を考える
        ktlintとcomposelintが流れるようにする
     */
    AppTheme {
        CompositionLocalProvider(LocalWindowSize provides windowSize) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = getAppRoot().root
            ) {
                getRoots().forEach { navigation ->
                    createGraph(navController, navigation)
                }
            }
        }
    }
}