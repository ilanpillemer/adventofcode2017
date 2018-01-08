import java.net.URL

var pipeMap : MutableMap<Int,MutableSet<Int>> = emptyMap<Int,MutableSet<Int>>().toMutableMap()

fun loadPipeMap(file: URL) {
    file.readText().lines().forEach {
        var vars = it.split("<->".toRegex())
        val left = vars[0].trim().toInt()
        var leftPipeSet = pipeMap.getOrDefault(left,emptySet<Int>().toMutableSet())
        leftPipeSet.add(left)
        val right = vars[1]
        right.split(",").forEach {
            val element = it.trim().toInt()
            leftPipeSet.add(element)
            var rightPipeSet = pipeMap.getOrDefault(element, emptySet<Int>().toMutableSet())
            rightPipeSet.add(left)
            pipeMap.put(element,rightPipeSet)
        }
        pipeMap.put(left,leftPipeSet)
    }
println(pipeMap)
}

//var followedPipes : MutableMap<Int,Boolean> = emptyMap<Int,Boolean>().toMutableMap()

fun pipesReach(dest: Int, pipe:Int) : Boolean {
    return pipesReachHelper(dest,pipe,emptyMap<Int,Boolean>().toMutableMap())
}

fun pipesReachZero(pipe: Int) : Boolean {
    return pipesReachZeroHelper(pipe, emptyMap<Int,Boolean>().toMutableMap())
}

fun pipesReachZeroHelper(pipe : Int, followedPipes : MutableMap<Int,Boolean>) : Boolean {
    return pipesReachHelper(0,pipe,followedPipes)
 }

fun pipesReachHelper(dest: Int, pipe : Int, followedPipes : MutableMap<Int,Boolean>) : Boolean {
    followedPipes.put(pipe,true)
    if (pipeMap.get(pipe)!!.contains(dest)) return true
    else {
        var huh = pipeMap.get(pipe)!!
        huh.forEach {
            if (!followedPipes.containsKey(it)) {
                val res = pipesReachHelper(dest,it,followedPipes)
                if (res) return true
            }
        }
    }
    return false
}