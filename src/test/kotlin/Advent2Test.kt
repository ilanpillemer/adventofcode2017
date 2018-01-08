import javafx.collections.transformation.SortedList
import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL

class Advent2Test {
    @Test fun testChecksum() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/test")
        val checksum = checksum(file)
        assertThat(checksum, `is` ( 18))
    }

    @Test fun testChecksum_input() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day2-input")
        val checksum = checksum(file)
        assertThat(checksum, `is` ( 51833))
    }

    @Test fun testChecksum2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day2-test2")
        val checksum = checksum2(file)
        assertThat(checksum, `is` ( 9))
    }

    @Test fun testChecksum2_input() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day2-input")
        val checksum = checksum2(file)
        assertThat(checksum, `is` ( 9))
    }
}