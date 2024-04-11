import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GameViewModelTest {
    @Test
    fun testGameViewModelGetSet() {
        val xMax = 2
        val yMax = 4
        val model = GameViewModel(xMax+1,yMax+1)

        assertEquals(TicMark.EMPTY, model.get(0,0))
        assertEquals(TicMark.EMPTY, model.get(xMax,0))
        assertEquals(TicMark.EMPTY, model.get(0,yMax))
        assertEquals(TicMark.EMPTY, model.get(xMax,yMax))

        assertEquals(TicMark.EDGE, model.get(-1,0))
        assertEquals(TicMark.EDGE, model.get(xMax+1,0))
        assertEquals(TicMark.EDGE, model.get(0,-1))
        assertEquals(TicMark.EDGE, model.get(xMax,yMax+1))

        model.set(0,0, TicMark.X)
        model.set(xMax,yMax, TicMark.O)
        assertEquals(TicMark.X, model.get(0,0))
        assertEquals(TicMark.O, model.get(xMax,yMax))
    }

    @Test
    fun testGameViewModelSetExceptions() {
        val xMax = 2
        val yMax = 4
        val model = GameViewModel(xMax+1,yMax+1)

        assertFailsWith<IllegalStateException> {
            model.set(-1,0, TicMark.X)
        }
        assertFailsWith<IllegalStateException> {
            model.set(0,-1, TicMark.X)
        }
        assertFailsWith<IllegalStateException> {
            model.set(xMax+1,0, TicMark.X)
        }
        assertFailsWith<IllegalStateException> {
            model.set(0,yMax+1, TicMark.X)
        }
    }

    @Test
    fun testGameViewModelInitialize() {
        val model = GameViewModel()

        assertEquals(TicMark.EMPTY, model.get(1,1))
        model.set(1,1,TicMark.O)
        assertEquals(TicMark.O, model.get(1,1))
        model.initialize()
        assertEquals(TicMark.EMPTY, model.get(1,1))
    }
}