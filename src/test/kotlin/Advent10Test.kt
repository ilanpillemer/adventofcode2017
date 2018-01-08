import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*


class Advent10Test {
    @Test fun passs () {}

    @Test fun testf() {
        assertEquals(
                arrayOf(2,1,0,3,4),
                f(arrayOf(0,1,2,3,4),0,3,0))

        assertEquals(
                arrayOf(4,3,0,1,2),
                f(arrayOf(2,1,0,3,4),3,4,1))

        assertEquals(
                arrayOf(4,3,0,1,2),
                f(arrayOf(4,3,0,1,2),3,1,2))

        assertEquals(
                arrayOf(3,4,2,1,0),
                f(arrayOf(4,3,0,1,2),1,5,3))
    }

    @Test fun testBuildHash() {
        buildHash(arrayOf(0,1,2,3,4), arrayOf(3, 4, 1, 5))
    }

    @Test fun testDense() {
        var a = buildHash64(Array<Int>(256,{it}), arrayOf(3, 4, 1, 5))
        var b = densify(a)
        var c = b.map { it.toHexString() }
        println(c)
    }


    @Test fun testXor() {
        var res : Int = 0
        res = 65 xor 27 xor 9 xor 1 xor 4 xor 3 xor 40 xor 50 xor 91 xor 7 xor 6 xor 0 xor 2 xor 5 xor 68 xor 22
        println(res)
    }

    @Test fun testGetAscii() {
        assertEquals(
                listOf(49,44,50,44,51),
                toAscii("1,2,3".toCharArray()).toList())
    }

    @Test fun testGetAsciiAndSalt() {
        assertEquals(
                listOf(49,44,50,44,51,17, 31, 73, 47, 23),
                toAsciiAndSalt("1,2,3".toCharArray()).toList())
    }

    @Test fun day10Sol1() {
//        val classLoader = javaClass.classLoader
//        val file : URL = this.javaClass.getResource("/day10-input")

        buildHash(Array<Int>(256,{it}), arrayOf(197,97,204,108,1,29,5,71,0,50,2,255,248,78,254,63))
    }

    @Test fun testKnotHash() {
        assertEquals( "a2582a3a0e66e6e86e3812dcb672a272",
                knotHash(""))
        var a = knotHash("")
        println(a)
        assertEquals( "33efeb34ea91902bb2f59c9920caa6cd",
                knotHash("AoC 2017"))
        a = knotHash("AoC 2017")
        println(a)
        assertEquals( "3efbe78a8d82f29979031a4aa0b16a9d",
                knotHash("1,2,3"))
        a = knotHash("1,2,3")
        println(a)
        assertEquals( "63960835bcdc130f0b66d7ff4f6a5a8e",
                knotHash("1,2,4"))
        a = knotHash("1,2,4")
        println(a)
    }

    @Test fun testSol2() {
        assertEquals( "35b028fe2c958793f7d5a61d07a008c8",
                knotHash("197,97,204,108,1,29,5,71,0,50,2,255,248,78,254,63"))
        var a = knotHash("197,97,204,108,1,29,5,71,0,50,2,255,248,78,254,63")
        println(a)
    }



}