import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

class Advent3Test {
    @Test fun testGetPos() {
        assertEquals(Pos (0,0), getPos(1) )
        assertEquals(Pos (1,0), getPos(2) )
        assertEquals(Pos (1,1), getPos(3) )
        assertEquals(Pos (0,1), getPos(4) )
        assertEquals(Pos (-1,1), getPos(5) )
        assertEquals(Pos (-1,0), getPos(6) )
        assertEquals(Pos (-1,-1), getPos(7) )
        assertEquals(Pos (0,-1), getPos(8) )
        assertEquals(Pos (2,1), getPos(12))
        assertEquals(Pos (0,2), getPos(15))
    }

    @Test fun testGetDistance() {
        assertEquals( 0  , getDistance(getPos(1)) )
        assertEquals( 3  , getDistance(getPos(12)) )
        assertEquals( 2  , getDistance(getPos(23)) )
        assertEquals( 31  , getDistance(getPos(1024)) )
        //assertEquals( 480  , getDistance(getPos(347991)) )
    }

    @Test fun testGetStorage() {
 //       assertEquals( 0  , getDistance(getPos(1)) )
 //       assertEquals( 3  , getDistance(getPos(12)) )
 //       assertEquals( 2  , getDistance(getPos(23)) )
        assertEquals( 31  , getDistance(getPos(1024)) )
        //assertEquals( 480  , getDistance(getPos(347991)) )
    }
}