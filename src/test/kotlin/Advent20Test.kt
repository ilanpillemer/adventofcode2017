import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*
import Directions.*
import kotlin.math.max
import kotlin.math.min


class Advent20Test {
    @Test fun pass () {}
    @Test fun testTick() {
        val a = Particle(Position(3,0,0), Velocity(2, 0 , 0), Acceleration(-1,0 ,0 ))
        val b = Particle(Position(4,0,0), Velocity(0, 0 , 0), Acceleration(-2,0 ,0 ))
        println(".. A: ${a.manhattan()} B: ${b.manhattan()}")
        for (i in 0..3) {
            a.tick()
            b.tick()
            println(".. A: ${a.manhattan()} B: ${b.manhattan()}")
        }
    }

    @Test fun testLoad() {
        loadParticles(this.javaClass.getResource("/day20-input"))
    }

    @Test fun testNearest() {
        loadParticles(this.javaClass.getResource("/day20-input"))
        getNearest()
    }

    @Test fun testCollisions() {
        loadParticles(this.javaClass.getResource("/day20-input"))
        getSizeAfterCollisions()
    }

    @Test fun testCollisionsTest() {
        loadParticles(this.javaClass.getResource("/day20-test"))
        getSizeAfterCollisions()
    }
}