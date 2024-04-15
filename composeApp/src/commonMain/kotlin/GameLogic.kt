import kotlin.math.max

/**
 * Game Logic class
 *
 * @param model             GameViewModel
 * @param winningLength     How many marks are needed to win
 */
class GameLogic(private val model: GameViewModel, private val winningLength: Int = 5) {

    data class Coordinate(val x: Int, val y: Int)

    private val oppositeDirectionIndex = 4 // Index to add to get opposite direction
    private val directions = arrayOf(
        Coordinate(1,0),
        Coordinate(1,1),
        Coordinate(0,1),
        Coordinate(-1,1),

        Coordinate(-1,0),
        Coordinate(-1,-1),
        Coordinate(0,-1),
        Coordinate(1,-1),
    )

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
     * Idea is to check only from location of last added mark, because winner was checked already
     * after every previous mark. Thus it is not needed to check whole table every time.
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param mark  mark to check
     */
    fun checkWinner(x: Int, y: Int, mark: TicMark): Boolean {
        // For each direction, need to count also opposite direction
        for (i in 0..< directions.size/2) {
            val dirA = directions[i]
            val dirB = directions[i + oppositeDirectionIndex]

            // NOTE: this will compare "greater than" (>) not "greater or equal" (>=), because starting
            // point (x,y) is counted twice (for both countForDirection() calls).
            if ((countForDirection(x, y, dirA.x, dirA.y, mark) +
                        countForDirection(x, y, dirB.x, dirB.y, mark)) > winningLength) {
                return true
            }
        }

        return false
    }

    fun rateSquare(x: Int, y: Int): Int {
        // Square must be empty to be meaningful to rate
        if (model.get(x,y) != TicMark.EMPTY) {
            return -1
        }

        var rateX = 0
        var rateO = 0
        for (i in 0..< directions.size/2) {
            // Sum opposite direction ratings together to give those higher rating
            val dirA = directions[i]
            val dirB = directions[i+oppositeDirectionIndex]

            // TicMark.X
            val countX = countForDirection(x+dirA.x, y+dirA.y, dirA.x, dirA.y, TicMark.X) +
                    countForDirection(x+dirB.x, y+dirB.y, dirB.x, dirB.y, TicMark.X)
            // TicMark.O
            val countO = countForDirection(x+dirA.x, y+dirA.y, dirA.x, dirA.y, TicMark.O) +
                    countForDirection(x+dirB.x, y+dirB.y, dirB.x, dirB.y, TicMark.O)

            //  Pow(2) to boost rating when there are more marks at the same line
            rateX += countX * countX
            rateO += countO * countO
        }
        // TODO if there is no room for winning, it should get lower rating

        return max(rateX, rateO)
    }

    fun calculateNextMove(): Coordinate {
        // Loop play field
        var bestRate = 0
        var bestPlace = Coordinate(0,0)
        repeat(model.sizeY) { y ->
            repeat(model.sizeX) { x ->
                // Rate every square
                val rate = rateSquare(x, y)
                // Find biggest rating
                if (rate > bestRate) {
                    // TODO this could randomize which place to select, if there are several bestRates
                    bestRate = rate
                    bestPlace = Coordinate(x, y)
                    println("New bestRate found: $rate at $bestPlace")
                }
            }
        }

        return bestPlace
    }
}