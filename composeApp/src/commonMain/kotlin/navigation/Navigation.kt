package navigation

import ActionScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import materialcomponent.communication.CommunicationScreen
import materialcomponent.containment.BottomSheetScaffoldNestedScrollSample
import materialcomponent.containment.ContainmentScreen
import materialcomponent.containment.SimpleBottomSheetScaffoldSample
import materialcomponent.navigation.NavigationScreen
import materialcomponent.selection.SelectionScreen
import materialcomponent.textinputs.TextInputsScreen

/*
構造的に FileとFolderに近い、GofだとCompositeパターン
普通のRouteは File で Nested はFolderと解釈できる

以下3つの文脈で考えると..
Composite
- root名をプロパティに持つ

Route
- Content() を持っている

Folder
- compositeを持っている
 */
sealed interface AppNavigation {
    val root: String
    // TODO argsもここのプロパティで良いはず

    sealed interface RootNavigation: AppNavigation {
        @Composable
        fun Content(navController: NavController)
    }

    sealed interface NestedNavigation: AppNavigation {
        val children: List<AppNavigation>
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
                        Button(onClick = {navController.navigate(Home().root)}) {
                            Text("home ->")
                        }
                        Button(onClick = { navController.navigate(FeatureA().root)} ) {
                            Text("nested ->")
                        }
                    }
                }
            }
        }
    }

    data class Home(override val root: String = "/home"): RootNavigation {
        @Composable
        override fun Content(navController: NavController) {
            Text("Home")
        }
    }

    data class FeatureA(
        override val children: List<AppNavigation> = listOf(FeatureAHome(),FeatureADetail(), FeatureANested()),
        override val root: String = "/featureA"
    ): NestedNavigation {
        data class FeatureAHome(override val root: String = "/featureA/home"): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                Column {
                    Text("FeatureAHome")
                    Button(onClick = { navController.navigate(FeatureADetail().root)}) {
                        Text("FeatureADetail ->")
                    }
                }
            }
        }

        data class FeatureADetail(override val root: String = "/featureA/detail"): RootNavigation {
            @Composable
            override fun Content(navController: NavController) {
                Column {
                    Text("FeatureADetail")
                    Button( onClick =  { navController.navigate(FeatureANested().root )}) {
                        Text("FeatureANested ->")
                    }
                }
            }
        }

        data class FeatureANested(
            override val children: List<AppNavigation> = listOf(NestedNestedA(), NestedNestedB()),
            override val root: String = "/featureA/nested"
        ): NestedNavigation {
            data class NestedNestedA(override val root: String = "/featureA/nested/a"): RootNavigation {
                @Composable
                override fun Content(navController: NavController) {
                    Column {
                        Text("FeatureANestedA")
                        Button(onClick = { navController.navigate(NestedNestedB().root)}) {
                            Text("FeatureANestedB->")
                        }
                    }
                }
            }

            data class NestedNestedB(override val root: String = "/featureA/nested/b"): RootNavigation {
                @Composable
                override fun Content(navController: NavController) {
                    Text("FeatureANestedB")
                }
            }
        }
    }

    companion object {
        fun getAppNavigation(): List<AppNavigation> = listOf(AppRoot(), Home(), FeatureA())
        fun getAppRoot(): AppNavigation = AppRoot()
    }
}

// アプリからのエントリー
fun NavGraphBuilder.createGraph(navController: NavController, navigation: AppNavigation) {
    when (navigation) {
        is AppNavigation.RootNavigation -> createRouteGraph(navController, navigation)
        is AppNavigation.NestedNavigation -> createNestedGraph(navController, navigation)
    }
}

private fun NavGraphBuilder.createRouteGraph(navController: NavController, rootNavigation: AppNavigation.RootNavigation) {
    composable(route = rootNavigation.root) { rootNavigation.Content(navController) }
}

private fun NavGraphBuilder.createNestedGraph(navController: NavController, nestedNavigation: AppNavigation.NestedNavigation) {
    navigation(
        startDestination = nestedNavigation.children.first().root, // TODO ここは指定できるようにする？
        route = nestedNavigation.root
    ) {
        nestedNavigation.children.forEach { navigation ->
            createGraph(navController, navigation)
        }
    }
}

// TODO
sealed interface Args {
    val key: String
    data class StringArg(
        override val key: String,
        val value: String
    ): Args
}



/*
TODO: Migrate to type-safe navigation once it is supported.
 */
sealed interface NestedNavigation: NavigationRoots {
    val nestedRoot: String
    fun getChildren(): List<NestedNavigation>
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

        override fun getChildren(): List<Containment> {
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