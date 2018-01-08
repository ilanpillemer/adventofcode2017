import java.net.URL

var banksList : MutableList<Int> = emptyList<Int>().toMutableList()
var bankMemo : MutableMap<String, Int> = emptyMap<String, Int>().toMutableMap()
var rank : Int = 0

fun loadBanks(f : URL) {
    rank = 0
    banksList.clear()
    bankMemo.clear()
    f.readText().lines().forEach {
        it.split("\\s+".toRegex()).forEach {
            banksList.add(it.toInt())
        }
    }
    println(banksList)
}

fun reallocate(f: URL) : Int {
    loadBanks(f)
    var banks : IntArray = banksList.toIntArray()
    return helper(0, banks)
}

fun bankCycle(f: URL) : Int {
    loadBanks(f)
    var banks : IntArray = banksList.toIntArray()
    return helper2(0, banks)
}

fun IntArray.bankKey() : String  {
    var sb  = StringBuilder()
    forEach {sb.append(it)}
    return sb.toString()
}



 tailrec fun helper (count : Int, banks: IntArray) : Int {

    if (bankMemo.containsKey(banks.bankKey())) {
        return count
    } else {
        bankMemo.put(banks.bankKey(),rank++)
        println("enter")
        var (pos, redis) = findMaxPos(banks)
        banks[pos] = 0
        while (redis > 0) {
            pos = (pos + 1) % banks.size
            banks[pos] += 1
            redis--
        }

        return helper(count + 1, banks)
    }
}

 tailrec fun helper2 (count : Int, banks: IntArray) : Int {

    println(banks.toList())
    if (bankMemo.containsKey(banks.bankKey())) {
        return count - bankMemo[banks.bankKey()]!!
    } else {
        bankMemo.put(banks.bankKey(),rank++)
        var (pos, redis) = findMaxPos(banks)
        banks[pos] = 0
        while (redis > 0) {
            pos = (pos + 1) % banks.size
            banks[pos] += 1
            redis--
        }

        return helper2(count + 1, banks)
    }
}

data class MaxPos (val pos : Int, val max: Int)
fun findMaxPos(array : IntArray) : MaxPos {
    var max_pos = 0
    var max_value = 0
    array.forEachIndexed { index, i ->
        if ( i > max_value) {
            max_pos = index
            max_value = i
        }
    }
    return MaxPos(max_pos, max_value)
}