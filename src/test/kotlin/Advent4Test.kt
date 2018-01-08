import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

class Advent4Test {
    @Test fun testCheckPassPhrase() {
        assertTrue(checkPassPhrase("aa bb cc dd ee"))
        assertFalse(checkPassPhrase("aa bb cc dd aa"))
        assertTrue(checkPassPhrase("aa bb cc dd aaa"))
    }

    @Test fun testCheckPassPhrase2() {
        assertTrue(checkPassPhrase2("abcde fghij"))
        assertFalse(checkPassPhrase2("abcde xyz ecdab"))
        assertTrue(checkPassPhrase2("a ab abc abd abf abj"))
        assertTrue(checkPassPhrase2("iiii oiii ooii oooi oooo"))
        assertFalse(checkPassPhrase2("oiii ioii iioi iiio"))
    }

    @Test fun testCount() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day4-test")

        assertEquals(2,countCorrectPhrases(file))

    }

    @Test fun testSolution() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day4-input")

        assertEquals(451,countCorrectPhrases(file))

    }

    @Test fun testSolution2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day4-input")

        assertEquals(223,countCorrectPhrases2(file))

    }
}