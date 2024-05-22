import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import navigation.AppNavigation.Companion.getAppNavigation
import navigation.AppNavigation.Companion.getAppRoot
import navigation.NavigationRoots
import navigation.NestedNavigation
import navigation.createGraph
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
                getAppNavigation().forEach { navigation ->
                    createGraph(navController, navigation)
                }
//                NavigationRoots.getRoots().forEach { root ->
//                    when (root.isNested) {
//                        true -> {
//                            (root as? NestedNavigation)?.let { nestedRoot ->
//                                navigation(
//                                    route = nestedRoot.nestedRoot,
//                                    startDestination = nestedRoot.root
//                                ) {
//                                    nestedRoot.getChildren().forEach { nestedNavRoot ->
//                                        composable(nestedNavRoot.root) {
//                                            nestedNavRoot.Content(navController)
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        else -> {
//                            composable(root.root) {
//                                root.Content(navController)
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}