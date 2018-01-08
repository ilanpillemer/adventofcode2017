import DIRECTION.*
import kotlin.math.abs

data class Pos( val x: Int , val y : Int = 0)
enum class DIRECTION {
    RIGHT,
    UP,
    LEFT,
    DOWN
}

var memo : MutableSet<Pos> = emptySet<Pos>().toMutableSet()
var storage : MutableMap<Pos,Int> = emptyMap<Pos,Int>().toMutableMap()

fun getPos( p: Int) : Pos {
    memo.clear()
    return helper(p, RIGHT, Pos(0,0))
}

fun getDistance (pos : Pos) : Int {
    val (x,y) = pos
    return (abs(x) + abs(y))
}

fun getSumAdjacentWeights(pos : Pos) : Int {
    val (x,y) = pos

    if (x==0 && y==0) return 1

    var ret = 0

    if (storage.containsKey(Pos(x+1, y ))) {
        ret += storage.get(Pos(x+1, y ))!!
    }

    if (storage.containsKey(Pos(x+1, y+1 ))) {
        ret += storage.get(Pos(x+1, y+1 ))!!
    }

    if (storage.containsKey(Pos(x, y+1 ))) {
        ret += storage.get(Pos(x, y+1 ))!!
    }

    if (storage.containsKey(Pos(x-1, y+1 ))) {
        ret += storage.get(Pos(x-1, y+1 ))!!
    }

    if (storage.containsKey(Pos(x-1, y ))) {
        ret += storage.get(Pos(x-1, y ))!!
    }

    if (storage.containsKey(Pos(x-1, y-1 ))) {
        ret += storage.get(Pos(x-1, y-1 ))!!
    }

    if (storage.containsKey(Pos(x, y-1 ))) {
        ret += storage.get(Pos(x, y-1 ))!!
    }

    if (storage.containsKey(Pos(x+1, y-1 ))) {
        ret += storage.get(Pos(x+1, y-1 ))!!
    }
    return ret
}

tailrec fun helper( p: Int, dir:DIRECTION, pos: Pos) : Pos {
    //println("P: $p")
    if (p == 1) return pos
    else {
        var (x ,y) = pos
        val stor = getSumAdjacentWeights(pos)
        memo.add(Pos(x,y))
        storage.put(pos,stor)
        println("$p .. $stor")
        // check direction is correct, and if not correct
        val new_dir = when (dir) {
            RIGHT -> {
                if (memo.size == 1) {
                   RIGHT
                } else {
                    if (memo.any({pos -> pos.y == y+1 && pos.x == x}) ) {
                        RIGHT
                    } else {
                        UP
                    }
                }
            }
            UP -> {
                if (memo.any({pos -> pos.y == y && pos.x == x-1}) ) {
                    UP
                } else {
                    LEFT
                }
            }
            LEFT -> {
                if (memo.any({pos -> pos.y == y-1 && pos.x == x}) ) {
                    LEFT
                } else {
                    DOWN
                }
            }
            DOWN -> {
                if (memo.any({pos -> pos.y == y && pos.x == x+1}) ) {
                    DOWN
                } else {
                    RIGHT
                }
            }

        }

        // we know direction...
        when (new_dir) {
            RIGHT -> x += 1
            UP -> y += 1
            LEFT -> x -= 1
            DOWN -> y -=1
        }
        return helper(p-1, new_dir, Pos(x,y))
    }
}