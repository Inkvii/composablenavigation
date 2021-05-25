package verzich.composablenavigation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import verzich.composablenavigation.NavigationEnum


@Composable
fun MainMenu(navController: NavController) {
	Column(Modifier.padding(10.dp)) {
		Text(
			text = "Main menu",
			textAlign = TextAlign.Center,
			fontSize = 12.em,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = "This is a test application about composable with navigation",
			fontSize = 2.em,
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.padding(20.dp))

		Card(
			shape = RoundedCornerShape(5.dp)
		) {
			Column() {
				val modifier = Modifier.padding(10.dp)

				Row(modifier) {
					Button(
						onClick = { navController.navigate(NavigationEnum.MANAGEMENT.toString()) },
						modifier = Modifier.weight(1F)
					) {
						Text(text = "In development")
					}
				}

				Row(modifier) {

					Button(
						onClick = { navController.navigate(NavigationEnum.PLACEHOLDER.toString()) },
						modifier = Modifier.weight(1F)
					) {
						Text(text = "To placeholder")
					}
				}
				Row(modifier) {

					Button(
						onClick = { navController.navigate(NavigationEnum.COUNTER.toString()) },
						modifier = Modifier.weight(1F)
					) {
						Text(text = "To counter")
					}
				}
			}

		}
	}
}