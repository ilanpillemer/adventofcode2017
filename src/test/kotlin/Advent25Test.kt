import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent25Test {
    @Test fun pass () {}
    @Test fun testTuring() {
    val cs = runMachine(State.AT, 6)
        assertEquals(3,cs)
    }
    @Test fun testTuringSol1() {
        val cs = runMachine(State.A, 12386363)
        assertEquals(3,cs)
    }

}