import java.util.*

fun f(hash:Array<Int>, pos: Int, len: Int, skip: Int) : Array<Int> {
    var ptr = pos
    var list : MutableList<Int> = emptyList<Int>().toMutableList()
    var stack : Stack<Int> = Stack()
    repeat(len) {
        var i = (ptr % hash.size)
        stack.push(hash[i])
        ptr++
    }
    ptr = pos
    while (stack.isNotEmpty()) {
        var i = (ptr % hash.size)
        hash[i] = stack.pop()
        ptr++
    }

    return hash
}

fun buildHash(hash:Array<Int>, lengths:Array<Int>) {
    var pos = 0
    var skip = 0
    var newHash : Array<Int> = hash
    lengths.forEach {
        newHash = f(newHash,pos,it,skip)
        pos = (pos + it + skip++) % newHash.size
    }
    println(hash[0] * hash[1])
}

fun buildHash64(hash:Array<Int>, lengths:Array<Int>) : Array<Int> {
    var pos = 0
    var skip = 0
    var newHash : Array<Int> = hash
    repeat(64) {
        lengths.forEach {
            newHash = f(newHash, pos, it, skip)
            pos = (pos + it + skip++) % newHash.size
        }
    }
    return hash
}

fun densify(sparse:Array<Int>) : Array<Int>{
    var a = sparse.toList().foldRight (10) {x,y -> x xor y}
    var dens = Array<Int>(16, {0})
    var i = 0
    for (j in 0 .. 15) {
        dens[j] = sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor
            sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor
            sparse[i++] xor sparse[i++]

    }
    return dens
}

fun densify2(sparse:Array<Int>) : Array<Int>{

    var a = sparse.groupingBy { 10 }

    var dens = Array<Int>(16, {0})
    var i = 0
    for (j in 0 .. 15) {
        dens[j] = sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor
                sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor sparse[i++] xor
                sparse[i++] xor sparse[i++]

    }
    return dens
}


fun Int.toHexString() = Integer.toHexString(this).padStart(2,'0')
fun toAsciiAndSalt(input:CharArray) : Array<Int> {
    var output = toAscii(input).toList()
    output = output.plus(listOf(17,31,73,47,23))
    return output.toTypedArray()
}


fun toAscii(input:CharArray) : Array<Int> {
    var ptr = 0
    var out = Array<Int>(input.size,{0})
    while (ptr < input.size) {
        out[ptr] = input[ptr].toInt()
        ptr++
    }
    return out
}

fun knotHash(input : String) : String {
    var lengths = toAsciiAndSalt(input.toCharArray())
    var sparsehash = buildHash64(Array<Int>(256,{it}),lengths)
    var densehash = densify(sparsehash)
    var sb = StringBuilder()
    var hexed = densehash.forEach {
        sb.append(it.toHexString())
    }
    return sb.toString()
}