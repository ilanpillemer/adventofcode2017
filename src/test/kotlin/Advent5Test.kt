import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

class Advent5Test {


    @Test fun test() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day5-test")
        val jumps = countJumps(file)
        assertEquals(5,jumps)

    }

    @Test fun test2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day5-test")
        val jumps = countJumps2(file)
        assertEquals(10,jumps)

    }

    @Test fun testSolution1() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day5-input")
        val jumps = countJumps(file)
        assertEquals(355965,jumps)

    }

    @Test fun testSolution2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day5-input")
        val jumps = countJumps2(file)
        assertEquals(26948068,jumps)

    }


}