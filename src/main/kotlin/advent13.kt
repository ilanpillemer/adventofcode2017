import java.net.URL
import kotlin.math.max

var firewall : MutableMap<Int,Layer> = emptyMap<Int,Layer>().toMutableMap()
var max_depth = 0
data class Layer(val depth: Int, val range: Int) {
    val severity = depth * range
}

fun caught(layer : Layer, picos : Int) : Boolean {
    if (layer.range == 0) return false
    else if (picos==0) return true
    else {
        //println("$picos -> $picos % ${((layer.range - 1) * 2)} == ${((picos % ((layer.range - 1) * 2) == 0))}")
        return ((picos % ((layer.range - 1) * 2) == 0))
    }
}



fun loadFirewall(file: URL) {
    max_depth = 0
    firewall.clear()
    file.readText().lines().forEach {
        val splits = it.split((":"))
        val depth = splits[0].trim().toInt()
        val range = splits[1].trim().toInt()
        firewall.put(depth, Layer(depth, range))
        max_depth = max(max_depth, depth)
    }
    println(firewall)
    println(max_depth)
}


