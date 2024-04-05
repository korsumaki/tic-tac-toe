import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class GreetingTest {
    @Test
    fun greetingPrintPlatformString() {
        val platformName = getPlatform().name

        assertNotEquals("", platformName)
        print("Platform: $platformName")
    }

    @Test
    fun greetingReturnsValue() {
        val greeting = Greeting().greet()
        val greetingBegins = greeting.substring(0..4)

        assertEquals("Hello", greetingBegins)
    }
}