import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// import androidx.lifecycle.ViewModel

/**
 * As mentioned in [this discussion](https://stackoverflow.com/questions/68702217/mutablestate-in-view-model/68702455#68702455),
 * ViewModel could use either LiveData or MutableState (mutableStateOf()).
 * Difference is that ViewModel works with Android, MutableState works only with Jetpack Compose.
 */

enum class TicMark(val value: Int) {
    EMPTY(0), O(1), X(2)
}

// Dummy ViewModel
class GameViewModel(val sizeX: Int = 7, val sizeY: Int = 8) /*: ViewModel()*/ {
    var nextTurn by mutableStateOf(TicMark.X)

    val ticArray by mutableStateOf(IntArray(sizeX * sizeY))

    fun initialize() {
        nextTurn = TicMark.EMPTY
        repeat(sizeX*sizeY) { index ->
            ticArray[index] = TicMark.EMPTY.value
        }
        nextTurn = TicMark.X
    }

    fun tapped(x: Int, y:Int) {
        val index = x + y * sizeX

        if (ticArray[index] == TicMark.EMPTY.value) {
            ticArray[index] = nextTurn.value

            nextTurn = if (nextTurn == TicMark.X) {
                TicMark.O
            } else {
                TicMark.X
            }
        }
    }
}

