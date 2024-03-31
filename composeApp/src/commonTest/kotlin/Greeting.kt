import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingTest {
    @Test
    fun greetingReturnsValue() {
        val greeting = Greeting().greet()
        val greetingBegins = greeting.substring(0..4)

        assertEquals("Hello", greetingBegins)
    }
}