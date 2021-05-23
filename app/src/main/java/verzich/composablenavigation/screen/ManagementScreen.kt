package verzich.composablenavigation.screen

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import verzich.composablenavigation.model.Building
import verzich.composablenavigation.model.UserViewModel

@Composable
fun Management() {
	val userViewModel: UserViewModel = viewModel()

	val buildings by userViewModel.buildings.collectAsState()

	Column(Modifier.padding(5.dp)) {
		UserInfo(userViewModel)

		buildings.forEach {
			SingleBuilding(it, userViewModel)
		}

		Button(onClick = { userViewModel.addBuilding() }) {
			Text(text = "Add me")
		}
		Button(onClick = { userViewModel.removeBuilding(userViewModel.buildings.value[0]) }) {
			Text(text = "Remove me")
		}

	}
}

@Preview(showBackground = true)
@Composable
fun SingleBuildingPreview() {
	val userViewModel: UserViewModel = viewModel()
	val building = Building("name", 1, 123, 120.0)

	Column() {
		SingleBuilding(building = building, userViewModel = userViewModel)
	}
}

@Composable
fun SingleBuilding(building: Building, userViewModel: UserViewModel) {
	Card(Modifier.padding(10.dp)) {

		Row(
			Modifier
				.padding(5.dp)
				.height(60.dp)
		) {
			Column(
				Modifier
					.weight(3f)
					.padding(2.dp)
			) {

				Row() {
					Text(building.name)
					Text(
						text = "(upgrade: ${building.costToUpgrade()} €)",
						fontSize = 10.sp,
						modifier = Modifier
							.padding(horizontal = 1.dp)
							.align(CenterVertically)
					)
				}
				ProgressBar(progress = 0.2f)
			}

			Button(
				onClick = { userViewModel.upgradeBuilding(building) }, modifier = Modifier
					.weight(1f)
					.padding(2.dp)
					.fillMaxHeight(), enabled = userViewModel.canUpgradeBuilding(building)
			) {
				Text(text = "${building.name} to lvl ${building.level + 1}", fontSize = 10.sp)

			}
		}
	}
}


@Composable
fun UserInfo(userViewModel: UserViewModel) {
	val money = userViewModel.money.collectAsState()
	Text(text = "User has ${money.value} €")
}

@Composable
fun ProgressBar(
	@FloatRange(from = 0.0, to = 1.0) progress: Float,
	height: Dp = 20.dp,
	color: Color = Color.Green,
	backgroundColor: Color = Color.Red,
	modifier: Modifier = Modifier.fillMaxWidth()
) {
	// Check that value is allowed, otherwise it will be drawn out of boundaries/constraints
	var checkedProgress = progress
	when {
		progress > 1f -> checkedProgress = 1f
		progress < 0f -> checkedProgress = 0f
	}

	var previousProgress by remember { mutableStateOf(0f) }
	Log.d(
		"Progressbar",
		"Progressbar value has been changed from previous value $previousProgress to ${checkedProgress}"
	)

	Box(
		modifier = modifier.then(
			Modifier
				.background(backgroundColor)
				.height(height)
				.fillMaxWidth()
		)
	) {
		Box(
			modifier = Modifier
				.height(height)
				.then(
					Modifier
						.background(color)
						.layout { measurable, constraints ->

							val width = (constraints.maxWidth * checkedProgress).toInt()
							Log.d("ProgressBar", "Max width: $width")
							Log.w("ProgressBar", "Changing progress ${checkedProgress}")
							layout(width, constraints.maxHeight) {
								val placeable = measurable.measure(constraints)
								placeable.place(0, 0)
							}
						})
		)
	}
	previousProgress = checkedProgress

}
