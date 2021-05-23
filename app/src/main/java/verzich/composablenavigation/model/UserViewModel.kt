package verzich.composablenavigation.model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

class UserViewModel : ViewModel() {
	private val _money = MutableStateFlow(BigDecimal(1000))
	val money = _money

	private var _buildings = MutableStateFlow(
		mutableListOf(
			Building("Gold mine", initialCost = 100, modifierPercentage = 1.2, level = 1),
			Building("Diamond mine", initialCost = 540, modifierPercentage = 1.4, level = 1),
			Building("Bitcoin rig", initialCost = 440, modifierPercentage = 2.52, level = 1),
			Building("Rice field", initialCost = 64, modifierPercentage = 0.87, level = 1)
		)
	)
	val buildings: StateFlow<List<Building>> = _buildings

	fun canUpgradeBuilding(building: Building): Boolean {
		val value = money.value >= building.costToUpgrade()
		Log.d(javaClass.name, "Can upgrade? $value")
		return value
	}

	fun upgradeBuilding(building: Building): Boolean {
		if (canUpgradeBuilding(building)) {
			Log.i(
				javaClass.name,
				"Proceeding with upgrade of building ${building.name} to level ${building.level + 1}"
			)
			_money.value -= building.costToUpgrade()
			_buildings.value = _buildings.value.toMutableList().also {
				it[it.indexOf(building)] = it[it.indexOf(building)].copy(level = building.level + 1)
			}


			return true
		}
		return false
	}

	fun addBuilding() {
		_buildings.value = _buildings.value.toMutableList().also {
			it.add(Building("TET", 2, 2, 10.0))
		}
		Log.i(javaClass.name, "Building added size ${buildings.value.size}")

	}

	fun removeBuilding(building: Building) {
		Log.i(javaClass.name, "Proceeding with removal of ${building.name}")

		try {
			val list = _buildings.value.filter { p -> p.name != building.name }.toMutableList()
			_buildings.value = list
		} catch (e: Exception) {

		}


	}
}

