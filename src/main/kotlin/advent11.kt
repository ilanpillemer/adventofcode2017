import Directions.*
enum class Directions {
    N, NE, SE, S, SW, NW
}

fun hasReductions(path : List<Directions>) : Boolean {
    if (path.contains(N) && path.contains(SE)) return true
    if (path.contains(N) && path.contains(S)) return true
    if (path.contains(N) && path.contains(SW)) return true
    if (path.contains(NE) && path.contains(S)) return true
    if (path.contains(NE) && path.contains(SW)) return true
    if (path.contains(NE) && path.contains(NW)) return true
    if (path.contains(SE) && path.contains(SW)) return true
    if (path.contains(SE) && path.contains(NW)) return true
    if (path.contains(S) && path.contains(NW)) return true
    return false
}

tailrec fun findShortestPath(path : List<Directions>) : List<Directions> =
    when (hasReductions(path)) {
        false -> path
        true -> findShortestPath(directionReduce(path))
    }

fun directionReduce(path : List<Directions>) :  List<Directions>{
    var newpath = path.toMutableList()
    if (newpath.contains(N) && newpath.contains(SE)) {
        newpath.remove(N)
        newpath.remove(SE)
        newpath.add(NE)
    }
    if (newpath.contains(N) && newpath.contains(S)) {
        newpath.remove(N)
        newpath.remove(S)
    }
    if (newpath.contains(N) && newpath.contains(SW)) {
        newpath.remove(N)
        newpath.remove(SW)
        newpath.add(NW)
    }
    if (newpath.contains(NE) && newpath.contains(S)) {
        newpath.remove(NE)
        newpath.remove(S)
        newpath.add(SE)
    }
    if (newpath.contains(NE) && newpath.contains(SW)) {
        newpath.remove(NE)
        newpath.remove(SW)
    }
    if (newpath.contains(NE) && newpath.contains(NW)) {
        newpath.remove(NE)
        newpath.remove(NW)
        newpath.add(N)
    }
    if (newpath.contains(SE) && newpath.contains(SW)) {
        newpath.remove(SE)
        newpath.remove(SW)
        newpath.add(S)
    }
    if (newpath.contains(SE) && newpath.contains(NW)) {
        newpath.remove(SE)
        newpath.remove(NW)
    }
    if (newpath.contains(S) && newpath.contains(NW)) {
        newpath.remove(S)
        newpath.remove(NW)
        newpath.add(SW)
    }
    return newpath
}

