package verzich.composablenavigation.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import verzich.composablenavigation.timer.TimerService
import java.util.*

class CounterModel(
	private val timerService: TimerService = TimerService()
) : ViewModel() {
	var currentValue = MutableStateFlow(0)


	init {
		viewModelScope.launch {
			timerService.counterFlow.collect { currentValue.value = Date(it).seconds }
		}
	}

	fun increment() {
		Log.d("CounterModel", "increment: Incremented to ${currentValue.value}")
		currentValue.value += 1
	}
}