import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent16Test {
    @Test fun pass () {}
    @Test fun testspin() {
        initDancers()
        spin(1)
        println(dancers)

        initDancers()
        spin(3)
        println(dancers)
    }

    @Test fun testex() {
        initDancers()
        exchange(3,4)
        println(dancers)

        initDancers()
        spin(1)
        exchange(4, 3)
        println(dancers)
    }

    @Test fun testp() {
        initDancers()
        spin(1)
        exchange(4, 3)
        swap('e','b')
        println(dancers)
    }

    @Test fun simpletestp() {
        initDancersSimple()
        spin(1)
        exchange(4, 3)
        swap('e','b')
        println(dancers)
    }

    @Test fun simpletestpfile() {
        val file : URL = this.javaClass.getResource("/day16-test")
        initDancersSimple()
        dance(file)
        println(dancers)
    }


    @Test fun simpletestpfiledance() {
        val file : URL = this.javaClass.getResource("/day16-test")
        initDancersSimple()
        danceLots(file,2)
        println(dancers)
    }

    @Test fun day16Sol1() {
        val file : URL = this.javaClass.getResource("/day16-input")
        initDancers()
        dance(file)
        println(dancers)
        initDancers()
        danceLots(file,1)
        println(dancers)
    }

    @Test fun day16Sol2() {
        val file : URL = this.javaClass.getResource("/day16-input")
        initDancers()
        danceLots(file,1_000_000_000 % 30) // use find cycle to get the constant
        println(dancers)
    }

    @Test fun findCycle() {
        val file : URL = this.javaClass.getResource("/day16-input")
        initDancers()
        val c = findCycle(file, 1_000_000_000)
        println(c)
    }
}