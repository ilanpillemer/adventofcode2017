import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent21Test {
    @Test fun pass () {
        initFractal()
        fractal.forEach {
            println(it.toList())
        }

    }
    @Test fun testLoad() {
        var file = this.javaClass.getResource("/day21-test")
        loadRules(file)
        initFractal()
        findMatch(fractal)
    }

    @Test fun testRecurse() {
        var file = this.javaClass.getResource("/day21-test")
        loadRules(file)
        initFractal()
        var state = applyRules(fractal,0, 2)
        //println("final state: ${state.toList()}")
        var hmm = ""
        state.forEach { hmm += it }
        println("number on : ${hmm.replace(".","").length}")
    }

    @Test fun testRecursePrayer() {
        var file = this.javaClass.getResource("/day21-input")
        loadRules(file)
        //println("rules: $rules")
        initFractal()
        var state = applyRules(fractal,0, 5)
        //println("final state: ${state.toList()}")
        var hmm = ""
        state.forEach { hmm += it }
        println("number on : ${hmm.replace(".","").length}")
        state.forEach { println(it) }
    }

    @Test fun testRecursePrayer2() {
        var file = this.javaClass.getResource("/day21-input")
        loadRules(file)
        //println("rules: $rules")
        initFractal()
        var state = applyRules(fractal,0, 18)
        //println("final state: ${state.toList()}")
        var hmm = ""
        state.forEach { hmm += it }
        println("number on : ${hmm.replace(".","").length}")
        //state.forEach { println(it) }
    }
}