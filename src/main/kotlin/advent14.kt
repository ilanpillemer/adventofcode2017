fun String.hexToByteString() : String {
    var sb = StringBuilder()
    this.forEach {
        sb.append(it.toString().toInt(16).toString(2).padStart(4,'0'))
    }
    return sb.toString()
}

fun loadKnotHashGrid(input : String) : List<String> {
    var list : MutableList<String> = emptyList<String>().toMutableList()
    for (i in 0..127) {
        list.add(knotHash("$input-$i").hexToByteString())
    }
    return list
}

fun countUsedSquares(list: List<String>) : Int {
    var sum = 0
    list.forEach {
        it.forEach {
            if (it.equals('1')) sum++
        }
    }
    return sum
}

// global array for the win!
var array : Array<Array<Char>> = emptyArray()
fun countingUsedSquares(list: List<String>) : Int {
    array = Array(128,{Array(128, { ' '})})
    list.forEachIndexed { index, s ->
        array.set(index,s.replace('1','#').replace('0','.').toCharArray().toTypedArray())
    }
    return countingUsedHelper()
}

fun countingUsedHelper() : Int {
    var sum = 0
    for (i in 0..array.size-1) {
        for (j in 0..array[i].size-1) {
            if (array[i][j].equals('#')) {
                updateAdjacent('1' + sum, i, j)
                sum++
            }
        }
    }
    array.forEach { println(it.toList()) }
    return sum
}

fun updateAdjacent(rep: Char, i: Int, j: Int)  {
    array[i][j] = rep
    if (j>0 && array[i][j-1].equals('#')) updateAdjacent(rep, i, j-1)
    if (j<127 && array[i][j+1].equals('#')) updateAdjacent(rep, i, j+1)
    if (i>0 && array[i-1][j].equals('#')) updateAdjacent(rep, i-1, j)
    if (i<127 && array[i+1][j].equals('#')) updateAdjacent(rep, i+1, j)
}