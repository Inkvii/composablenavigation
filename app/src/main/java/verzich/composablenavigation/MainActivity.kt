package verzich.composablenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import verzich.composablenavigation.screen.MainMenu
import verzich.composablenavigation.screen.Management
import verzich.composablenavigation.ui.theme.ComposableNavigationTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val navController = rememberNavController()
			ComposableNavigationTheme {
				// This is where all the routes are defined in this particular app.
				// To navigate between components, navigation controller must be passed to the component somehow
				NavHost(
					navController = navController,
					startDestination = NavigationEnum.MAIN_MENU.toString()
				) {
					composable(NavigationEnum.MAIN_MENU.toString()) { MainMenu(navController = navController) }
					composable(NavigationEnum.PLACEHOLDER.toString()) {
						PlaceholderComposable(
							navController = navController
						)
					}
					composable(NavigationEnum.MANAGEMENT.toString()) { Management() }
				}
			}
		}
	}

	@Composable
	fun PlaceholderComposable(navController: NavController) {
		Column {
			Text(text = "Hello there")

			Button(onClick = { navController.navigate("main menu") }) {
				Text("Navigate to main menu")
			}
		}
	}
}

