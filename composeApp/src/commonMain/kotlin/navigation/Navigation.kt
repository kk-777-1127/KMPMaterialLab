package navigation

import ActionScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import materialcomponent.communication.CommunicationScreen
import materialcomponent.containment.BottomSheetScaffoldNestedScrollSample
import materialcomponent.containment.ContainmentScreen
import materialcomponent.containment.SimpleBottomSheetScaffoldSample
import materialcomponent.navigation.NavigationScreen
import materialcomponent.selection.SelectionScreen
import materialcomponent.textinputs.TextInputsScreen

/*
TODO: Migrate to type-safe navigation once it is supported.
 */
sealed interface NestedNavigation: NavigationRoots {
    val nestedRoot: String
    fun getRoots(): List<NestedNavigation>
}
sealed interface NavigationRoots {
    val root: String
    val isNested: Boolean
        get() = this is NestedNavigation

    data class Root(override val root: String = "/"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            Surface {
                Box(Modifier.fillMaxSize().padding(24.dp)) {
                    FlowRow(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        getRoots().filter { it !is Root }.forEach {
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

    data class Action(override val root: String = "/action"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            ActionScreen(navController)
        }
    }

    data class Communication(override val root: String = "/communication"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            CommunicationScreen(navController)
        }
    }

    sealed interface Containment: NestedNavigation {
        override val nestedRoot: String
            get() = "/containment"

        data class ContainmentRoot(override val root: String = "/containment/root"): Containment {
            @Composable
            override fun Content(navController: NavController) {
                ContainmentScreen(navController)
            }
        }

        data class BottomSheetScaffoldNav(override val root: String = "/communication/bottomsheetscaffold"): Containment {
            @Composable
            override fun Content(navController: NavController) {
                SimpleBottomSheetScaffoldSample(navController)
            }
        }

        data class NestedBottomSheetScaffold(override val root: String = "/communication/nestedbottomsheetscaffold"): Containment {
            @Composable
            override fun Content(navController: NavController) {
                BottomSheetScaffoldNestedScrollSample(navController)
            }
        }

        override fun getRoots(): List<Containment> {
            return listOf(
                ContainmentRoot(),
                BottomSheetScaffoldNav(),
                NestedBottomSheetScaffold()
            )
        }
    }
    data class Navigation(override val root: String = "/navigation"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            NavigationScreen()
        }
    }
    data class Selection(override val root: String = "/selection"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            SelectionScreen()
        }
    }
    data class TextInputs(override val root: String = "/textinputs"): NavigationRoots {
        @Composable
        override fun Content(navController: NavController) {
            TextInputsScreen()
        }
    }

    @Composable
    fun Content(navController: NavController)

    companion object {
        fun getRoots(): List<NavigationRoots> {
            return listOf(
                Root(),
                Action(),
                Communication(),
                Containment.ContainmentRoot(),
                Navigation(),
                Selection(),
                TextInputs()
            )
        }
    }
}