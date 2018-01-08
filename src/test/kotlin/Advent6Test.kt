import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

class Advent6Test {


    @Test fun testLoadBanks() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day6-test")
        loadBanks(file)
    }

    @Test fun testFindMax() {
        val array : IntArray = intArrayOf(0 , 2, 7 , 0)
        var (pos,value) = findMaxPos(array)
        assertEquals(2, pos)
        assertEquals(7, value)
    }

    @Test fun testCount() {
        val file : URL = this.javaClass.getResource("/day6-test")
        val cycles = reallocate(file)
        assertEquals(5, cycles)
    }

    @Test fun testCount2() {
        val file : URL = this.javaClass.getResource("/day6-test")
        val cycles = bankCycle(file)
        assertEquals(4, cycles)
    }

    @Test fun testSolution1() {
        val file : URL = this.javaClass.getResource("/day6-input")
        val cycles = reallocate(file)
        assertEquals(11137, cycles)
    }

    @Test fun testSolution2() {
        val file : URL = this.javaClass.getResource("/day6-input")
        val cycles = bankCycle(file)
        assertEquals(1037, cycles)
    }


}