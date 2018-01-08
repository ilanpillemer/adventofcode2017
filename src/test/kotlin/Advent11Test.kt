import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max

class Advent11Test {
    @Test fun pass () {}

    @Test fun testShortestPath() {
        assertEquals(listOf(NE,NE,NE),
        findShortestPath(listOf(NE,NE,NE)))

        assertEquals(emptyList<Directions>(),
                findShortestPath(listOf(NE,NE,SW,SW)))

        assertEquals(listOf(SE,SE),
                findShortestPath(listOf(NE,NE,S,S)))

        assertEquals(listOf(SW,S,S),
                findShortestPath(listOf(SE,SW,SE,SW,SW)))
    }

    @Test fun testShortestPathCount() {
        assertEquals(3,
                findShortestPath(listOf(NE,NE,NE)).size)

        assertEquals(0,
                findShortestPath(listOf(NE,NE,SW,SW)).size)

        assertEquals(2,
                findShortestPath(listOf(NE,NE,S,S)).size)

        assertEquals(3,
                findShortestPath(listOf(SE,SW,SE,SW,SW)).size)
    }

    @Test fun testDay11Sol1() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day11-input")
        var soldirections : MutableList<Directions> = emptyList<Directions>().toMutableList()
        file.readText().split(",").forEach {
            when(it.toUpperCase()) {
                "N" -> soldirections.add(N)
                "NE" -> soldirections.add(NE)
                "SE" -> soldirections.add(SE)
                "S" -> soldirections.add(S)
                "SW" -> soldirections.add(SW)
                "NW" -> soldirections.add(NW)
                else -> fail("Invalid Direction $it")
            }
        }

        val reducedirections = findShortestPath(soldirections)
      //  assertEquals(listOf(SE,SE), reducedirections)
        assertEquals(650, reducedirections.size)
    }

    @Test fun testDay11Sol2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day11-input")
        var soldirections : MutableList<Directions> = emptyList<Directions>().toMutableList()
        file.readText().split(",").forEach {
            when(it.toUpperCase()) {
                "N" -> soldirections.add(N)
                "NE" -> soldirections.add(NE)
                "SE" -> soldirections.add(SE)
                "S" -> soldirections.add(S)
                "SW" -> soldirections.add(SW)
                "NW" -> soldirections.add(NW)
                else -> fail("Invalid Direction $it")
            }
        }

        var max = 0
        while (soldirections.isNotEmpty()) {
            max = max (max, findShortestPath(soldirections).size)
            soldirections.removeAt(soldirections.size-1)
            println(".")
        }
        println(max)
        //  assertEquals(listOf(SE,SE), reducedirections)
        //assertEquals(650, reducedirections.size)
    }
}