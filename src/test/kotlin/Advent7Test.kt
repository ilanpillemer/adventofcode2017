import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

class Advent7Test {


    @Test fun testGetNameAndWeight() {
       val (name, weight) = nameAndWeight("pbga (66)")

        assertEquals("pbga", name)
        assertEquals( 66, weight)
    }

    @Test fun testCombineddWeight() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-test")
        loadTowersIntoMap(file)
        towers.forEach {
            areChildrenUnbalanced(it.value)
        }
    }

    @Test fun testCombineddWeightSolution() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-input")
        loadTowersIntoMap(file)
        towers.forEach {
            areChildrenUnbalanced(it.value)
        }
    }

    @Test fun testCombineddWeightSolutionFix() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-input-fix")
        loadTowersIntoMap(file)
        towers.forEach {
            areChildrenUnbalanced(it.value)
        }
    }

    @Test fun testFindWeight() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-test")
        val root = getTowerRoot(file)
        val rootTower = towers[root]!!
        println(getCombinedWeights(rootTower))

        rootTower.children.forEach {
            println(getCombinedWeights(towers[it]!!))
        }

    }

    @Test fun testGetChildren() {
       val list = children("ktlj, cntj, xhth")
       assertEquals(list, listOf("ktlj","cntj","xhth"))

        val empt = children("   ")
        assertEquals(empt, emptyList<String>())
    }

    @Test fun testGetTowerRoot() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-test")
        val root = getTowerRoot(file)
        assertEquals("tknk", root)
    }

    @Test fun testGetTowerRootSolution1() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day7-input")
        val root = getTowerRoot(file)
        assertEquals("qibuqqg", root)
    }


}