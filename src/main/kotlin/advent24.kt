import java.net.URL
import kotlin.math.max

var bridges : MutableList<Bridge> = emptyList<Bridge>().toMutableList()
var builtbridges : MutableList<List<Bridge>> = emptyList<List<Bridge>>().toMutableList()
data class Bridge(val x : Int, val y: Int) {
    fun weight() = x + y
}

fun loadBridges(f : URL) {
    bridges.clear()
    builtbridges.clear()
    f.readText().lines().forEach {
        val parts = it.split("/")
        val b = Bridge(parts[0].trim().toInt(),parts[1].trim().toInt())
        bridges.add(b)
    }
//    println(bridges)
}

fun runBridgeBuilder(f: URL) {
    var maxWeight = 0
    var maxLength = 0
    var maxw2 = 0
    loadBridges(f)
    buildBridges(0,bridges, emptyList<Bridge>())
    builtbridges.forEach {
       // println(it)
        val weight = it.map{ it.weight() }. fold(0,{total,next -> total + next } )
        val length = it.size
        maxWeight = max(maxWeight,weight)
        maxLength = max(maxLength,length)
        if (length == 30) {
            println("lenght $length weight $weight")
            maxw2 = max(maxw2,weight)
        }
       //println(weight)
    }
    println("max weight: $maxWeight")
    println("max length: $maxLength")
    println("max weight of max length: $maxw2")
}

fun buildBridges(port : Int, available : List<Bridge>, current : List<Bridge>) {
    val options = getPossibleBridges(port,available)
    if (options.isEmpty()) {
        builtbridges.add(current)
    } else {
        options.forEach {
            val (x,y) = it
            val nextPort = if (port == x) y else x
            buildBridges(nextPort, available.minus(it), current.plus(it) )
        }
    }
}

fun getPossibleBridges(port: Int, available: List<Bridge>) = available.filter { it.x == port || it.y == port }
