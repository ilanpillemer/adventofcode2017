import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*


class Advent8Test {



    @Test fun testParseAndExecute() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day8-test")
        val root = parseAndExecute(file)
    }

    @Test fun testParseAndExecuteSolution1() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day8-input")
        val root = parseAndExecute(file)
    }

    @Test fun testParseAndExecuteSolution2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day8-input")
        val root = parseAndExecute2(file)
    }

}