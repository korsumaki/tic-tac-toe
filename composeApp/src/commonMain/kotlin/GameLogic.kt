import kotlin.math.max
import kotlin.math.pow

/**
 * Game Logic class
 *
 * @param model             GameViewModel
 * @param winningLength     How many marks are needed to win
 */
class GameLogic(private val model: GameViewModel, private val winningLength: Int = 5) {

    /**
     * Count given marks from location (x,y) to direction (dx,dy)
     *
     * Count marks recursively to given direction.
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param dx    direction in x (-1, 0, 1)
     * @param dy    direction in y (-1, 0, 1)
     * @param mark  mark to count
     */
    fun countForDirection(x: Int, y: Int, dx: Int, dy: Int, mark: TicMark): Int {
        if (model.get(x,y) == mark) {
            return 1 + countForDirection(x+dx, y+dy, dx, dy, mark)
        }
        return 0
    }

    /**
     * Check Winner from one point.
     *
     * Idea is to check only  from location of last added mark, because winner was checked already
     * after every previous mark. Thus it is not needed to check whole table every time.
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param mark  mark to check
     */
    fun checkWinner(x: Int, y: Int, mark: TicMark): Boolean {
        // For each direction, need to count also opposite direction

        // NOTE: this will compare "greater than" (>) not "greater or equal" (>=), because starting
        // point (x,y) is counted twice (for both countForDirection() calls).
        if ((countForDirection(x, y, 1, 0, mark)
                    + countForDirection(x, y, -1, 0, mark)) > winningLength) {
            return true
        }
        if ((countForDirection(x, y, 1, 1, mark)
                    + countForDirection(x, y, -1, -1, mark)) > winningLength) {
            return true
        }
        if ((countForDirection(x, y, 0, 1, mark)
                    + countForDirection(x, y, 0, -1, mark)) > winningLength) {
            return true
        }
        if ((countForDirection(x, y, -1, 1, mark)
                    + countForDirection(x, y, 1, -1, mark)) > winningLength) {
            return true
        }

        return false
    }

    data class Coordinate(val x: Int, val y: Int)

    fun rateSquare(x: Int, y: Int): Int {
        // Square must be empty to be meaningful to rate
        if (model.get(x,y) != TicMark.EMPTY) {
            return -1
        }

        //val opposite = 4 // Index to add to get opposite direction
        val dirs = arrayOf(
            Coordinate(1,0),
            Coordinate(1,1),
            Coordinate(0,1),
            Coordinate(-1,1),

            Coordinate(-1,0),
            Coordinate(-1,-1),
            Coordinate(0,-1),
            Coordinate(1,-1),
        )

        var rateX = 0
        var rateO = 0
        for (direction in dirs) {
            val rX = countForDirection(x+direction.x, y+direction.y, direction.x, direction.y, TicMark.X)
            val rO = countForDirection(x+direction.x, y+direction.y, direction.x, direction.y, TicMark.O)

            rateX += rX.toFloat().pow(2.0f).toInt()
            rateO += rO.toFloat().pow(2.0f).toInt()
        }

        return max(rateX, rateO)
    }

    fun calculateNextMove(): Coordinate {
        // Loop play field
        /*repeat(model.sizeX) { x ->
            repeat(model.sizeY) { y ->
                val r = rateSquare(x, y)
            }
        }*/

        // Rate every square
        // Find biggest rating
        // Return it's coordinate

        return Coordinate(1,1) // TODO Temporary return value
    }
}