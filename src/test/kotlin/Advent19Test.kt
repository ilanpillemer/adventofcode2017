import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent19Test {
    @Test fun pass () {}
    @Test fun testLoad() {
        loadDiagram(this.javaClass.getResource("/day19-test"))
        diagram.forEach {
            println(it.toList())
        }
    }

    @Test fun testGetStart() {
        loadDiagram(this.javaClass.getResource("/day19-test"))
        val (_, start) = getStart()
        assertEquals(5,start)
    }

    @Test fun testMove() {
        loadDiagram(this.javaClass.getResource("/day19-test"))
        val discovery = move(DIRECTION.DOWN,getStart(), emptyList(), 0)
        println("discovered: $discovery")
    }

    @Test fun testMoveS1() {
        loadDiagram(this.javaClass.getResource("/day19-input"))
        val discovery = move(DIRECTION.DOWN,getStart(), emptyList(), 0)
        println()
        discovery.forEach { print(it) }
        println()
        println("discovered: $discovery")
    }

}