import java.net.URL
import kotlin.math.abs

data class Position(val x: Long, val y: Long, val z: Long)
data class Velocity(val x: Long, val y: Long, val z:Long)
data class Acceleration(val x: Long, val y: Long, val z:Long)

var particles : Array<Particle> = emptyArray()

fun loadParticles(f: URL) {
    particles = emptyArray()
    var index = 0
    f.readText().lines().forEach {
        val r = Regex(pattern = """p=<(.*?),(.*?),(.*?)>, v=<(.*?),(.*?),(.*?)>, a=<(.*?),(.*?),(.*?)>""")
        val (px,py,pz,vx,vy,vz,ax,ay,az) = r.matchEntire(it)!!.destructured

        val pos = Position(px.toLong(),py.toLong(),pz.toLong())
        val vel = Velocity(vx.toLong(),vy.toLong(),vz.toLong())
        val acc = Acceleration(ax.toLong(),ay.toLong(),az.toLong())
        val par = Particle(pos, vel, acc)
        par.index = index
        index++
        particles = particles.plus(par)
    }
}

fun getNearest() {
    var minIndex = 0
    var minValue = 1_000_000L

    for (i in 0..1_000_000) {
        particles.forEach {
            it.tick()
        }
        minIndex = -1
        minValue = 1_000_000_000
        particles.forEachIndexed { index, particle ->
            if (particle.manhattan() < minValue) {
                minIndex = index
                minValue = particle.manhattan()
            }
        }
        println("minIndex: $minIndex minValue: $minValue")
    }
}

fun getSizeAfterCollisions() {
    for (i in 0..1_000_000) {
        particles.forEach {
            it.tick()
        }
        removeCollisions()
    }
}

fun removeCollisions() {

    var found : Boolean = false
    particles.forEachIndexed { index, particle ->
        val i = index
        val p = particle
        particles.forEachIndexed { index, particle ->
            if ((i!=index) && p.position==particle.position) {
                particle.isAlive = false
                p.isAlive = false
                found = true
                println("BOOM! between ${p.index} and ${particle.index} -> $p <--> $particle")
            }
        }
    }
    particles = particles.filter { it.isAlive }.toTypedArray()
    if (found) println(particles.size)
}

data class Particle(var position: Position, var velocity: Velocity, var acceleration: Acceleration, var isAlive :Boolean = true, var index: Int = 0) {
    fun manhattan() : Long = abs(position.x) + abs(position.y) + abs(position.z)
    fun tick() {
        velocity = Velocity(velocity.x + acceleration.x, velocity.y +  acceleration.y, velocity.z + acceleration.z)
        position = Position( position.x + velocity.x, position.y + velocity.y, position.z + velocity.z)
    }
}

