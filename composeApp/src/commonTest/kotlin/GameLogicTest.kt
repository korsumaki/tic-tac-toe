import kotlin.test.Test
import kotlin.test.assertEquals

class GameLogicTest {
    /**
     * Helper function for filling play field with some marks
     */
    private fun createGameField(model: GameViewModel, data: Array<String>) {
        for((y, row) in data.withIndex()) {
            //println("$y #$row#")
            for ((x, char) in row.withIndex()) {
                when (char) {
                    'o' -> model.set(x, y, TicMark.O)
                    'x' -> model.set(x, y, TicMark.X)
                    else -> model.set(x, y, TicMark.EMPTY)
                }
            }
        }
    }

    @Test
    fun testCreateGameField() {
        val model = GameViewModel(10,10)
        //val game = GameLogic(model,3)

        val data = arrayOf(
            //123456789
            "x ox      ", //0
            "   o      ", //1
            "    o     ", //2
            "     o    ", //3
            "      o   ", //4
            "       o  ", //5
            "        o ", //6
            " x       o", //7
        )
        createGameField(model, data)

        assertEquals(TicMark.X, model.get(0,0))
        assertEquals(TicMark.EMPTY, model.get(1,0))
        assertEquals(TicMark.O, model.get(2,0))
        assertEquals(TicMark.X, model.get(3,0))
        assertEquals(TicMark.O, model.get(9,7))
        assertEquals(TicMark.X, model.get(1,7))
    }

    @Test
    fun testGameLogicCountForDirection() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,3)
        val data = arrayOf(
            //123456789
            "          ", //0
            "          ", //1
            " x        ", //2
            " ooo   x  ", //3
            "      x   ", //4
            "     x    ", //5
            "          ", //6
            "          ", //7
        )
        createGameField(model, data)

        assertEquals(0, game.countForDirection(0,2,1,0,TicMark.X))

        assertEquals(1, game.countForDirection(1,2,1,0,TicMark.X))
        assertEquals(0, game.countForDirection(1,2,1,0,TicMark.O))

        assertEquals(3, game.countForDirection(1,3,1,0,TicMark.O))
        assertEquals(2, game.countForDirection(2,3,1,0,TicMark.O))
        assertEquals(3, game.countForDirection(3,3,-1,0,TicMark.O))

        assertEquals(3, game.countForDirection(5,5,1,-1,TicMark.X))
    }

    @Test
    fun testGameLogicCheckWinner() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,3)
        val data = arrayOf(
            //123456789
            "     ooo  ", //0
            "   o      ", //1
            "   xox    ", //2
            "  x  o    ", //3
            "          ", //4
            "    x    x", //5
            "   x     x", //6
            "  x      x", //7
        )
        createGameField(model, data)

        assertEquals(false, game.checkWinner(1, 1, TicMark.X))
        assertEquals(true, game.checkWinner(4, 5, TicMark.X))
        assertEquals(true, game.checkWinner(3, 6, TicMark.X))
        assertEquals(true, game.checkWinner(2, 7, TicMark.X))

        assertEquals(false, game.checkWinner(4, 2, TicMark.X))

        assertEquals(false, game.checkWinner(3, 2, TicMark.X))

        assertEquals(true, game.checkWinner(5, 0, TicMark.O))
        assertEquals(true, game.checkWinner(6, 0, TicMark.O))
        assertEquals(true, game.checkWinner(7, 0, TicMark.O))

        assertEquals(true, game.checkWinner(4, 2, TicMark.O))

        assertEquals(true, game.checkWinner(9, 7, TicMark.X))
    }

    @Test
    fun testGameLogicRateSquare_justNeighbours() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,5)
        val data = arrayOf(
            //123456789
            " oxxxo    ", //0
            "   o o    ", //1
            "          ", //2
            "          ", //3
            "    x     ", //4
            "   x   ooo", //5
            "       o o", //6
            "o      ooo", //7
        )
        createGameField(model, data)

        assertEquals(-1, game.rateSquare(4,4)) // Already filled -> -1
        assertEquals(-1, game.rateSquare(0,7)) // Already filled -> -1
        assertEquals(-1, game.rateSquare(-1,7)) // Edge -> -1
        assertEquals(0, game.rateSquare(3,7)) // Empty, nothing near -> 0
        assertEquals(1, game.rateSquare(3,3)) // 1 neighbour -> 1
        assertEquals(1, game.rateSquare(4,3)) // 1 neighbour -> 1
        assertEquals(1, game.rateSquare(5,5)) // 1 neighbour -> 1
        assertEquals(2, game.rateSquare(4,5)) // 2 neighbour -> 2
        assertEquals(2, game.rateSquare(2,1)) // 2/2 neighbours -> 2
        assertEquals(5, game.rateSquare(4,1)) // 2/3 neighbours (2 at the same line) -> 5
        assertEquals(16, game.rateSquare(8,6)) // 8 neighbours (at the same line) -> 16
    }

    @Test
    fun testGameLogicRateSquare_moreMarks() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,5)
        val data = arrayOf(
            //123456789
            "   xx    o", //0
            "        o ", //1
            "  o    o  ", //2
            "  o       ", //3
            "oo        ", //4
            "   x o    ", //5
            "   xo     ", //6
            "xxx oo    ", //7
        )
        createGameField(model, data)

        assertEquals(4, game.rateSquare(2,0)) // 2 in row -> 2*2 -> 4
        assertEquals(9, game.rateSquare(6,3)) // 3 in row -> 3*3 -> 9
        assertEquals(8, game.rateSquare(2,4)) // 2+2 in row -> 2*2 + 2*2 -> 8
        assertEquals(13, game.rateSquare(3,7)) // 3+2 in row -> 3*3 + 2*2 -> 13
    }

    @Test
    fun testGameLogicRateSquare_oppositeDirections() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,5)
        val data = arrayOf(
            //123456789
            "          ", //0
            "xxx x     ", //1
            "          ", //2
            "     o    ", //3
            "   oo     ", //4
            "          ", //5
            "   xx x   ", //6
            "          ", //7
        )
        createGameField(model, data)

        assertEquals(4, game.rateSquare(2,4)) // 2 -> 2*2 -> 4
        assertEquals(5, game.rateSquare(5,4)) // 2+1-> 4+1 -> 5
        assertEquals(9, game.rateSquare(5,6)) // 3 -> 3*3 -> 9
        assertEquals(16, game.rateSquare(3,1)) // 4 -> 4*4 -> 16
    }

    @Test
    fun testGameLogicCalculateNextMove() {
        val model = GameViewModel(10,10)
        val game = GameLogic(model,3)
        val data = arrayOf(
            //123456789
            "          ", //0
            "          ", //1
            "   o      ", //2
            "    o x   ", //3
            "    x     ", //4
            "   x  o   ", //5
            "       o  ", //6
            "          ", //7
        )
        createGameField(model, data)

        assertEquals(GameLogic.Coordinate(5, 4), game.calculateNextMove())
    }
}