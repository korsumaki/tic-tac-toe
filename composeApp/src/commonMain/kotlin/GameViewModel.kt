import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// import androidx.lifecycle.ViewModel

/**
 * As mentioned in [this discussion](https://stackoverflow.com/questions/68702217/mutablestate-in-view-model/68702455#68702455),
 * ViewModel could use either LiveData or MutableState (mutableStateOf()).
 * Difference is that ViewModel works with Android, MutableState works only with Jetpack Compose.
 */

enum class TicMark {
    EMPTY, O, X
}

// Dummy ViewModel
class GameViewModel(val sizeX: Int = 7, val sizeY: Int = 8) /*: ViewModel()*/ {
    var tapCount by mutableIntStateOf(0)
    var latestTappedMark by mutableStateOf(0)

    val ticArray by mutableStateOf(IntArray(sizeX * sizeY))

    fun tapped(x: Int, y:Int, mark: Int) {
        val index = x + y * sizeX
        if (ticArray[index] == 0) {
            ticArray[index] = mark
            tapCount++
            latestTappedMark = mark
        }
    }
}

