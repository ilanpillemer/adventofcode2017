import java.io.File
import java.net.URL

fun checksum(f: URL) : Int {
    var checksum = 0
    f.readText().lines().forEach {
        val line : MutableSet<Int> = emptySet<Int>().toMutableSet()
        val sum = it.split("\\s+".toRegex()) .forEach { line.add(it.toInt()) }
        val sorted = line.toSortedSet()
        val max = sorted.max()!!
        val min = sorted.min()!!
        val diff = max - min
        checksum += diff
    }
    return checksum
}


fun checksum2(f: URL) : Int {
    var checksum = 0
    f.readText().lines().forEach {
        val line : MutableSet<Int> = emptySet<Int>().toMutableSet()
        val sum = it.split("\\s+".toRegex()) .forEach { line.add(it.toInt()) }
        line.forEach {
            val me = it
            line.forEach {
                if (me % it == 0 && me != it) {
                    val div = if (me > it) {me/it} else {it/me}
                    checksum = checksum + div
                }
            }
        }

    }
    return checksum
}

