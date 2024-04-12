import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// import androidx.lifecycle.ViewModel

/**
 * As mentioned in [this discussion](https://stackoverflow.com/questions/68702217/mutablestate-in-view-model/68702455#68702455),
 * ViewModel could use either LiveData or MutableState (mutableStateOf()).
 * Difference is that ViewModel works with Android, MutableState works only with Jetpack Compose.
 */

/**
 * Enum for Marks
 */
enum class TicMark(val value: Int) {
    EMPTY(0), O(1), X(2), EDGE(3)
}

// Dummy ViewModel
class GameViewModel(val sizeX: Int = 7, val sizeY: Int = 8) /*: ViewModel()*/ {
    var nextTurn by mutableStateOf(TicMark.X)

    private val ticArray by mutableStateOf(IntArray(sizeX * sizeY))

    /**
     * Initialize play field
     */
    fun initialize() {
        nextTurn = TicMark.EMPTY
        repeat(sizeX*sizeY) { index ->
            ticArray[index] = TicMark.EMPTY.value
        }
        nextTurn = TicMark.X
    }

    /**
     * Get mark from play field
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @return      TicMark
     */
    fun get(x: Int, y: Int): TicMark {
        if (x < 0 || y < 0 || x >= sizeX || y >= sizeY) {
            return TicMark.EDGE
        }

        return when(ticArray[x + y * sizeX]) {
            TicMark.EMPTY.value -> TicMark.EMPTY
            TicMark.O.value -> TicMark.O
            TicMark.X.value -> TicMark.X
            else -> TicMark.EDGE
        }
    }

    /**
     * Set mark
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param mark  mark to set
     * @exception Throws IllegalStateException if coordinates are illegal
     */
    fun set(x: Int, y: Int, mark: TicMark) {
        check(x >= 0)
        check(y >= 0)
        check(x < sizeX)
        check(y < sizeY)
        ticArray[x + y * sizeX] = mark.value
    }

    fun tapped(x: Int, y:Int) {
        if (get(x,y) == TicMark.EMPTY) {
            set(x, y, nextTurn)

            nextTurn = if (nextTurn == TicMark.X) {
                TicMark.O
            } else {
                TicMark.X
            }
        }
    }
}

