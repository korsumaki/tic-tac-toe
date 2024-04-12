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
}