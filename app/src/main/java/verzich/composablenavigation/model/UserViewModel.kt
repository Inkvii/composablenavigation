package verzich.composablenavigation.model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.math.BigDecimal

data class BuildingWithTimer(var remainingTime: Int, var building: Building)

class UserViewModel : ViewModel() {
	private val _money = MutableStateFlow(BigDecimal(1000))
	val money = _money


	private var _buildings = MutableStateFlow(
		mutableListOf(
			BuildingWithTimer(
				5,
				Building(
					"Gold mine",
					initialCost = 100,
					modifierPercentage = 1.2,
					level = 1,
					timeNeededInSeconds = 5,
					initialReward = 10.toBigDecimal()
				)
			),
			BuildingWithTimer(
				7,
				Building(
					"Diamond mine",
					initialCost = 540,
					modifierPercentage = 1.4,
					level = 1,
					timeNeededInSeconds = 7,
					initialReward = 12.toBigDecimal()
				)
			)
		)
	)
	val buildings = _buildings

	fun canUpgradeBuilding(building: Building): Boolean {
		val value = money.value >= building.costToUpgrade()
		Log.d(javaClass.name, "Can upgrade? $value")
		return value
	}

	fun upgradeBuilding(buildingWithTimer: BuildingWithTimer): Boolean {
		if (canUpgradeBuilding(buildingWithTimer.building)) {
			Log.i(
				javaClass.name,
				"Proceeding with upgrade of building ${buildingWithTimer.building.name} to level ${buildingWithTimer.building.level + 1}"
			)
			_money.value -= buildingWithTimer.building.costToUpgrade()
			_buildings.value = _buildings.value.toMutableList().also {

				val bt = it[it.indexOf(buildingWithTimer)]
				val b = bt.building.copy(level = bt.building.level + 1)

				it[it.indexOf(buildingWithTimer)] = bt.copy(building = b)
			}

			return true
		}
		return false
	}

	fun addBuilding() {
//		_buildings.value = _buildings.value.toMutableList().also {
//			it.add(Building("TET", 2, 2, 10.0, 5, 10.toBigDecimal()))
//		}
//		Log.i(javaClass.name, "Building added size ${buildings.value.size}")
	}

	fun removeBuilding(building: Building) {
//		Log.i(javaClass.name, "Proceeding with removal of ${building.name}")
//
//		try {
//			val list = _buildings.value.filter { p -> p.name != building.name }.toMutableList()
//			_buildings.value = list
//		} catch (e: Exception) {
//
//		}
	}
}

