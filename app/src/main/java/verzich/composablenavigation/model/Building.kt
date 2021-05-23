package verzich.composablenavigation.model

import android.util.Log
import java.math.BigDecimal

data class Building(
	val name: String,
	var level: Int = 0,
	val initialCost: Int,
	val modifierPercentage: Double
) {

	fun costToUpgrade(): BigDecimal {
		Log.d(javaClass.name, "Calculating cost to upgrade of $name")
		return level.toBigDecimal() * modifierPercentage.toBigDecimal() * initialCost.toBigDecimal()
	}
}