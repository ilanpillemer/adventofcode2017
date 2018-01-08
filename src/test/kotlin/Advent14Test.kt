import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min

class Advent14Test {
    @Test fun pass () {}
    @Test fun testSolDay14() {
        assertEquals( "d4f76bdcbf838f8416ccfa8bc6d1f9e6",
                knotHash("flqrgnkx-0"))
        var a = knotHash("flqrgnkx-0")
        println(a)
    }

    @Test fun testOneCharHexToByteString() {
        assertEquals("0000","0".hexToByteString())
        assertEquals("0001","1".hexToByteString())
        assertEquals("1110","e".hexToByteString())
        assertEquals("1111","f".hexToByteString())
    }

    @Test fun testFullStringToByteString() {
        assertEquals("11010100111101110110101111011100101111111000001110001111100001000001011011001100111110101000101111000110110100011111100111100110","d4f76bdcbf838f8416ccfa8bc6d1f9e6".hexToByteString())
        assertEquals("11010100111101110110101111011100101111111000001110001111100001000001011011001100111110101000101111000110110100011111100111100110",
                knotHash("flqrgnkx-0").hexToByteString())
        assertEquals("01010101111010101011001111000100111110111111111011011110000101101101110011101100001011000110011011011101101000100110010001100100",
                knotHash("flqrgnkx-1").hexToByteString())
    }

    @Test fun testLoadKnotHashGrid() {
        val count = countUsedSquares(loadKnotHashGrid("flqrgnkx"))
        assertEquals(8108,count)
    }

    @Test fun testLoadKnotHashGridSol1() {
        val count = countUsedSquares(loadKnotHashGrid("hfdlxzhv"))
        assertEquals(8230,count)
    }

    @Test fun testLoadKnotHashGridTest2() {
        val count = countingUsedSquares(loadKnotHashGrid("flqrgnkx"))
        assertEquals(1242,count)
    }

    @Test fun testLoadKnotHashGridInput2() {
        val count = countingUsedSquares(loadKnotHashGrid("hfdlxzhv"))
        assertEquals(1103,count)
    }


}