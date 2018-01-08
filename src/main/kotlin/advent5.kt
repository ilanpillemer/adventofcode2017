import java.net.URL

fun countJumps (f: URL) : Int{
    var list : MutableList<Int> = emptyList<Int>().toMutableList()
    var instr : IntArray
    f.readText().lines().forEach {
        list.add(it.toInt())
    }
    instr = list.toIntArray()
    return helper(0,instr,0)
}

fun countJumps2 (f: URL) : Int{
    var list : MutableList<Int> = emptyList<Int>().toMutableList()
    var instr : IntArray
    f.readText().lines().forEach {
        list.add(it.toInt())
    }
    instr = list.toIntArray()
    return helper2(0,instr,0)
}


tailrec fun helper(pos: Int, instr: IntArray, acc: Int) : Int{
    if ((pos + instr[pos]) >= instr.size) return acc + 1
    else {
        val new_pos = pos + instr[pos]
        instr[pos] = instr[pos] + 1
        return helper(new_pos, instr, acc + 1)
    }
}

tailrec fun helper2(pos: Int, instr: IntArray, acc: Int) : Int{
    if ((pos + instr[pos]) >= instr.size) return acc + 1
    else {
        val new_pos = pos + instr[pos]
        if (instr[pos] >= 3) {
            instr[pos] = instr[pos] - 1
        } else {
            instr[pos] = instr[pos] + 1
        }
        return helper2(new_pos, instr, acc + 1)
    }
}