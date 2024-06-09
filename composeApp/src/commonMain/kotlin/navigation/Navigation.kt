package navigation

import ActionScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import materialcomponent.communication.CommunicationScreen
import materialcomponent.containment.BottomSheetAsScreenComposable
import materialcomponent.containment.BottomSheetScaffoldNestedScrollSample
import materialcomponent.containment.ContainmentScreen
import materialcomponent.containment.SimpleBottomSheetScaffoldSample
import materialcomponent.navigation.ExitAlwaysBottomAppBarScreen
import materialcomponent.navigation.NavigationRailScreen
import materialcomponent.navigation.NavigationScreen
import materialcomponent.selection.SelectionScreen
import materialcomponent.textinputs.TextInputsScreen

sealed interface AppNavigation {
    val root: String
    val isBottomSheet: Boolean
        get() = false
}

sealed interface RootNavigation: AppNavigation {
    @Composable
    fun Content(navController: NavController)
}

sealed interface NestedNavigation: AppNavigation {
    val children: List<AppNavigation>
}


fun NavGraphBuilder.createGraph(navController: NavController, navigation: AppNavigation) {
    when (navigation) {
        is RootNavigation -> when (navigation.isBottomSheet) {
            true -> createBottomSheet(navController, navigation)
            else -> createRouteGraph(navController, navigation)
        }
        is NestedNavigation -> createNestedGraph(navController, navigation)
    }
}

private fun NavGraphBuilder.createRouteGraph(navController: NavController, rootNavigation: RootNavigation) {
    composable(route = rootNavigation.root) { rootNavigation.Content(navController) }
}

private fun NavGraphBuilder.createBottomSheet(navController: NavController, rootNavigation: RootNavigation) {
    dialog(
        route = rootNavigation.root,
        dialogProperties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) { rootNavigation.Content(navController) }
}

private fun NavGraphBuilder.createNestedGraph(navController: NavController, nestedNavigation: NestedNavigation) {
    navigation(
        startDestination = nestedNavigation.children.first().root, // TODO ここは指定できるようにする？
        route = nestedNavigation.root
    ) {
        nestedNavigation.children.forEach { navigation ->
            createGraph(navController, navigation)
        }
    }
}


data class AppRoot(override val root: String = "/"): RootNavigation {
    @Composable
    override fun Content(navController: NavController) {
        Surface {
            Box(Modifier.fillMaxSize().padding(24.dp)) {
                FlowRow(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    getRoots().filter { it !is AppRoot }.forEach {
                        Button(
                            onClick = { navController.navigate(it.root) },
                        ) {
                            Text(it.root.removePrefix("/").replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } )
                        }
                    }
                }
            }
        }
    }
}

data class Action(override val root: String = "/action"): RootNavigation {
    @Composable
    override fun Content(navController: NavController) {
        ActionScreen(navController)
    }
}

data class Communication(override val root: String = "/communication"): RootNavigation {
    @Composable
    override fun Content(navController: NavController) {
        CommunicationScreen(navController)
    }
}

data class Navigation(
    override val root: String = "/navigation",
    override val children: List<AppNavigation> = listOf(
        Root(),
        BottomAppBarScreens(),
        NavigationBarScreens()
    )
): NestedNavigation {

    data class Root(
        override val root: String = "/navigation/root"
    ): RootNavigation {
        @Composable
        override fun Content(navController: NavController) {
            NavigationScreen(navController)
        }
    }

    data class BottomAppBarScreens(
        override val root: String = "/navigation/bottomAppBar/",
        override val children: List<AppNavigation> = listOf(ExitBottomBarSample())
    ): NestedNavigation {

        data class ExitBottomBarSample(
            override val root: String = "/navigation/bottomAppBar/exitBottomBar"
        ): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                ExitAlwaysBottomAppBarScreen(navController)
            }
        }
    }

    data class NavigationBarScreens(
        override val root: String = "/navigation/navigationBar/",
        override val children: List<AppNavigation> = listOf(
            NavigationRails()
        )
    ): NestedNavigation {

        data class NavigationRails(
            override val root: String = "/navigation/navigationBar/rails"
        ): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                NavigationRailScreen(navController)
            }
        }
    }

}
data class Selection(override val root: String = "/selection"): RootNavigation {
    @Composable
    override fun Content(navController: NavController) {
        SelectionScreen()
    }
}
data class TextInputs(override val root: String = "/textinputs"): RootNavigation {
    @Composable
    override fun Content(navController: NavController) {
        TextInputsScreen()
    }
}

data class Containment(
    override val root: String = "/containment",
    override val children: List<AppNavigation> = listOf(
        ContainmentRoot(),
        BottomSheetScaffoldNav(),
        NestedBottomSheetScaffold(),
        BottomSheetAsNestedScreen(),
    )
): NestedNavigation {
    data class ContainmentRoot(override val root: String = "/containment/root"): RootNavigation {
        @Composable
        override fun Content(navController: NavController) {
            ContainmentScreen(navController)
        }
    }

    data class BottomSheetScaffoldNav(override val root: String = "/communication/bottomsheetscaffold"): RootNavigation {
        @Composable
        override fun Content(navController: NavController) {
            SimpleBottomSheetScaffoldSample(navController)
        }
    }

    data class NestedBottomSheetScaffold(override val root: String = "/communication/nestedbottomsheetscaffold"): RootNavigation {
        @Composable
        override fun Content(navController: NavController) {
            BottomSheetScaffoldNestedScrollSample(navController)
        }
    }

    data class BottomSheetAsNestedScreen(
        override val root: String = "/communication/bottomsheetasscreen",
        override val children: List<AppNavigation> = listOf(
            BottomSheetAsScreen(),
            BottomSheetAsScreen2()
        )
    ): NestedNavigation {

        data class BottomSheetAsScreen(
            override val root: String = "/communication/bottomsheetasscreen/1",
            override val isBottomSheet: Boolean = true
        ): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                BottomSheetAsScreenComposable(navController)
            }
        }

        data class BottomSheetAsScreen2(
            override val root: String = "/communication/bottomsheetasscreen/2",
        ): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                BottomSheetAsScreenComposable(navController)
            }
        }
    }

}

fun getRoots(): List<AppNavigation> = listOf(
    AppRoot(), Action(), Communication(), Containment(), Navigation(), Selection(), TextInputs()
)

fun getAppRoot(): AppNavigation = AppRoot()