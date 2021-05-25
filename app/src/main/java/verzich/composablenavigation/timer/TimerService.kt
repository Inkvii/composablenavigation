package verzich.composablenavigation.timer

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimerService {


	val counterFlow: Flow<Long> = flow {
		while (true) {
			Log.d("CounterFlow", "Counter flow accessed")
			emit(System.currentTimeMillis())
			delay(2000)
		}
	}
}