
//var dirtyInput : Array<Char> = emptyArray<Char>()




var garbageCount = -1
fun cleanOutGarbage(dirtyInput : CharArray) : CharArray {
    garbageCount = 0
    var cleanInput : MutableList<Char> = emptyList<Char>().toMutableList()
    var bkptr = -1
    var fwdptr = -1
    var inGarbage : Boolean = false
    while (fwdptr < (dirtyInput.size-1)) {
        if (inGarbage && dirtyInput[fwdptr].equals('!')) {
            fwdptr++
            fwdptr++
        }
        if (bkptr == fwdptr) {
            bkptr++
            fwdptr++
            if (inGarbage) garbageCount++
        }
        if (!inGarbage && dirtyInput[fwdptr].equals('<')) {
            inGarbage = true
            fwdptr++
        }
        if (!inGarbage) {
            cleanInput.add(dirtyInput[fwdptr])
        }
        if (inGarbage && dirtyInput[fwdptr].equals('>')) {
            inGarbage = false
        }
        bkptr = fwdptr
    }
    return cleanInput.toCharArray()
}

// global cheat
var score = 0
fun countGroups(cleanInput: CharArray) : Int {
    score = 0
    var ptr = 0
    var groups = 0
    var current_inc = 0

    while (ptr < (cleanInput.size-1)) {
        if(cleanInput[ptr].equals('{'))  {
            groups++
            current_inc++
            score+=current_inc
        }
        if(cleanInput[ptr].equals('}'))  {
            current_inc--
        }
        ptr ++
    }
    return groups
}