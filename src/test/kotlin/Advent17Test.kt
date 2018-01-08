import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent17Test {
    @Test fun pass () {}

    @Test fun testAddStep() {
        initBuffer()
        for (i in 1..2017) {
            spinStep(3, i)
            //if (i % 10 == 0) println(circularBuffer)
            println("$i: $circularBuffer")
        }
        for (i in 0..2017) {
            if (circularBuffer[i] == 2017) {
                println(circularBuffer[i+1])
                return
            }

        }

    }

    @Test fun testAddStepSol1() {
        initBuffer()
        for (i in 1..2017) {
            spinStep(377, i)
            //if (i % 10 == 0) println(circularBuffer)
            if (i % 10 == 0) println("$i: $circularBuffer")
        }
//        for (i in 0..2017) {
//            if (circularBuffer[i] == 2017) {
//                println(circularBuffer[i+1])
//                return
//            }
//
//        }
        println(value)

    }

    @Test fun testAddStepSol1_2() {
        initBuffer()
        for (i in 1..2017) {
            spinStep(377, i)
            //spinStep(1, i)
            //if (i % 10 == 0) println(circularBuffer)
            if (i % 377 == 0) println("$i: $pos: $circularBuffer")
        }
//        for (i in 0..2017) {
//            if (circularBuffer[i] == 2017) {
//                println(circularBuffer[i+1])
//                return
//            }
//
//        }
        println("$pos $value")

    }

    @Test fun testAddStepSol2() {
        initBuffer()
        for (i in 1..50_000_000) {
            spinStep(377, i)
            if ((i % 1_000_000) == 0) {
               // for (i in 0..50_000_000) {
                    //if (circularBuffer[i] == 0) {
                        //println(circularBuffer[i + 1])
                        //break
                    //}
               // }
                println("$pos $value")
            }
        }
//        for (i in 0..50_000_000) {
//            if (circularBuffer[i] == 0) {
//                println(circularBuffer[i + 1])
//                break
//            }
//        }


    }
}