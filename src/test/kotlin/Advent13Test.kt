import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min

class Advent13Test {
    @Test fun pass () {}


    @Test fun testCatchSeverity() {
        assertTrue(caught(Layer(0,3),0))
        assertFalse(caught(Layer(1,2),1))
        assertFalse(caught(Layer(2,0),2))
        assertFalse(caught(Layer(3,0),3))
        assertFalse(caught(Layer(4,4),4))
        assertFalse(caught(Layer(5,0),5))
        assertTrue(caught(Layer(6,4),6))
    }

    @Test fun testSeverity() {
        assertEquals(0,Layer(0,3).severity)
        assertEquals(24,Layer(6,4).severity)
    }

    @Test fun testDay13Test() {
        val file : URL = this.javaClass.getResource("/day13-test")
        loadFirewall(file)
        var score = 0
        for (i in 0..max_depth) {
            if (firewall.containsKey(i)) {
                if (caught(firewall[i]!!,i)) {
                   score += firewall[i]!!.severity
                }
            }
        }
        assertEquals(24,score)
    }

    @Test fun testDay13Sol1() {
        val file : URL = this.javaClass.getResource("/day13-input")
        loadFirewall(file)
        var score = 0
        for (i in 0..max_depth) {
            if (firewall.containsKey(i)) {
                if (caught(firewall[i]!!,i)) {
                    score += firewall[i]!!.severity
                }
            }
        }
        assertEquals(3184,score)
    }

    @Test fun testDay13Test_for_delay_10_doesnt_get_caught() {
        val file : URL = this.javaClass.getResource("/day13-test")
        loadFirewall(file)
        var score = 0
        var delay = 10
        for (i in 0..max_depth) {
            if (firewall.containsKey(i)) {
                if (caught(firewall[i]!!,i+delay)) {
                    score += firewall[i]!!.severity
                }
            }
        }
        assertEquals(0,score)
    }

    @Test fun testDay13Test_finds_smallest_delay() {
        val file : URL = this.javaClass.getResource("/day13-test")
        loadFirewall(file)
        var score = 0
        var delay = -1
        var lowest_score = 999
        while (lowest_score!=0) {
            delay++
            println("delay $delay")
            score = 0
            loop@ for (i in 0..max_depth) {
                if (firewall.containsKey(i)) {
                    if (caught(firewall[i]!!, i + delay)) {
                        score += 1
                        break@loop
                    }
                }
            }
            println("score $score")
            lowest_score=min(lowest_score,score)
        }
        assertEquals(10,delay)
    }

    @Test fun testDay13Sol2_finds_smallest_delay() {
        val file : URL = this.javaClass.getResource("/day13-input")
        loadFirewall(file)
        var score = 0
        var delay = -1
        var lowest_score = 999
        while (lowest_score!=0) {
            delay++
            //println("delay $delay")
            score = 0
            loop@ for (i in 0..max_depth) {
                if (firewall.containsKey(i)) {
                    if (caught(firewall[i]!!, i + delay)) {
                        score += 1
                        break@loop
                    }
                }
            }
            //println("score $score")
            lowest_score=min(lowest_score,score)
        }
        println(delay)
        assertEquals(3878062,delay)
    }
}