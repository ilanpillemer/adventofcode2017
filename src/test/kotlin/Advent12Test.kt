import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max

class Advent12Test {
    @Test fun pass () {}


    @Test fun testLoadPipeMap() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-test")
        loadPipeMap(file)
    }

    @Test fun testReachZero() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-test")
        loadPipeMap(file)
        assertTrue(pipesReachZero(0))
        assertFalse(pipesReachZero(1))
        assertTrue(pipesReachZero(2))
        assertTrue(pipesReachZero(3))
        assertTrue(pipesReachZero(4))
        assertTrue(pipesReachZero(5))
        assertTrue(pipesReachZero(6))
    }

    @Test fun testDay12Test() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-test")
        loadPipeMap(file)
        var count = 0
        pipeMap.keys.forEach {
            if (pipesReachZero(it)) {
                count++
            }
        }
        assertEquals(6,count)

    }

    @Test fun testDay12Sol1() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-input")
        loadPipeMap(file)
        var count = 0
        pipeMap.keys.forEach {
            if (pipesReachZero(it)) {
                println("$it did reach zero")
                count++
            }

        }
        assertEquals(145,count)

    }

    @Test fun testDay12Sol2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-input")
        loadPipeMap(file)
        var count = 0
        var dest = 0
        var removeable : MutableSet<Int> = emptySet<Int>().toMutableSet()
        while(pipeMap.isNotEmpty()) {
            println("trying $dest")
            removeable.clear()
            pipeMap.keys.forEach {
                if (pipesReach(dest,it)) {
                    removeable.add(it)
                }
            }
            if (removeable.size > 0 ) count++

            removeable.forEach {
                pipeMap.remove(it)
            }
            println("${pipeMap.size}.. left")
            dest++
        }
        assertEquals(207,count)

    }

    @Test fun testDay12Sol1470() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day12-input")
        loadPipeMap(file)
        assertTrue(pipesReachZero(470))

    }


}