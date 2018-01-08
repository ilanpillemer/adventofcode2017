import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent23Test {
    @Test fun pass () {}

    @Test fun testInterpret() {

        initProgram()
        interpret("set a 1")
        interpret("add a 2")
        interpret("mul a a")
        interpret("mod a 5")
        println("pc: $pc sound: $sound registers:$registers")

        interpret("snd a")
        interpret("set a 0")
        interpret("rcv a")
        interpret("jgz a -1")
        interpret("set a 1")
        interpret("jgz a -2")
        println("pc: $pc sound: $sound registers:$registers")

        interpret("jgz a -1")
        println("pc: $pc sound: $sound registers:$registers")

        interpret("rcv a")
        interpret("jgz a -1")
        interpret("set a 1")
        interpret("jgz a -2")
    }

    @Test fun executeTest() {
        println(numOfMul)
        executeProgramListing(this.javaClass.getResource("/day23-input"))
        println(codeListing)
        println(numOfMul)
    }

    @Test fun executeTest2() {
        println(numOfMul)
        executeProgramListing(this.javaClass.getResource("/day23-input2"))
        println(codeListing)
        println(numOfMul)
    }

    @Test fun executeTestSol2() {
        executeProgramListings(this.javaClass.getResource("/day18-test-2"))
    }

    @Test fun executeSol2() {
        executeProgramListings(this.javaClass.getResource("/day18-input"))
    }

    @Test fun executeSol1() {
        executeProgramListing(this.javaClass.getResource("/day18-input"))
    }
}