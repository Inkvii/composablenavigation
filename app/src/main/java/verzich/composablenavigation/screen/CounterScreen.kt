package verzich.composablenavigation.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import verzich.composablenavigation.NavigationEnum
import verzich.composablenavigation.model.CounterModel

@Composable
fun Counter(navController: NavController) {
	val counterModel: CounterModel = viewModel()

	val currentValue by counterModel.currentValue.collectAsState()

	LaunchedEffect("first") {
		launch {
			Log.d("Counter", "Starting this launched effect")

			counterModel.startCounter()
		}
	}


	Column {
		Row(
			Modifier
				.fillMaxWidth()
				.weight(2f),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			Text(currentValue.toString(), fontSize = 60.sp)
		}
		Row(
			Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			Text(text = "Current value is shown above this.", Modifier.align(Bottom))
		}
		Row(
			Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			Button(
				onClick = {
					navController.navigate(NavigationEnum.MAIN_MENU.toString())
					counterModel.stopCounter()
				},
				modifier = Modifier.fillMaxSize()
			) {
				Text(text = "Go back")
			}
		}
	}
}