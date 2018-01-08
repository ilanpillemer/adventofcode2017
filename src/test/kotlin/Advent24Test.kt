import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent24Test {
    @Test fun pass () {}
    @Test fun testBridgeLoad() {
        loadBridges(this.javaClass.getResource("/day24-test"))
    }
    @Test fun testBridgeBuild() {
        runBridgeBuilder(this.javaClass.getResource("/day24-test"))
    }

    @Test fun testBridgeBuildSol1() {
        runBridgeBuilder(this.javaClass.getResource("/day24-input"))
    }
}