import java.net.URL

var diagram : Array<CharArray> = emptyArray()


fun loadDiagram(f: URL) {
    diagram = emptyArray()
    f.readText().lines().forEach {
       diagram = diagram.plus (it.toCharArray())
    }
}

fun getStart() : Pos {
    diagram[0].forEachIndexed {
        index, c ->
        if (c.equals('|')) return Pos(index,0)
    }
    fail("invalid diagram with no starting location")
}

tailrec fun move(incoming : DIRECTION, pos : Pos, letters : List<Char>, count: Int) : List<Char> {
    val nc = count + 1
    val (x, y) = pos
    val isCross = diagram[y][x].equals('+')
    var newLetters = letters.toMutableList()
    if (diagram[y][x] >= 'A' && diagram[y][x] <= 'Z') newLetters.add(diagram[y][x])
    if (endOfJourney(incoming, pos)) {
        println("count : $nc")
        return newLetters
    } else {
        if (isCross) {
            if (incoming == DIRECTION.DOWN || incoming == DIRECTION.UP) {
                if (diagram[y][x - 1] != ' ') return move(DIRECTION.RIGHT, Pos(x - 1, y), newLetters, nc)
                else return move(DIRECTION.LEFT, Pos(x + 1, y), newLetters,nc)
            } else {
                if (y >= 1 && x < diagram[y - 1].size && diagram[y - 1][x] != ' ') return move(DIRECTION.UP, Pos(x, y - 1), newLetters, nc)
                else return move(DIRECTION.DOWN, Pos(x, y + 1), newLetters, nc)
            }
        } else {
            if (incoming == DIRECTION.DOWN) return move(incoming, Pos(x, y + 1), newLetters,nc )
            else if (incoming == DIRECTION.UP) return move(incoming, Pos(x, y - 1), newLetters, nc)
            else if (incoming == DIRECTION.LEFT) return move(incoming, Pos(x + 1, y), newLetters, nc)
            else return move(DIRECTION.RIGHT, Pos(x - 1, y), newLetters,nc)
        }
    }
}


fun endOfJourney(incoming : DIRECTION, pos: Pos) : Boolean {
    val (x,y) = pos
    val letter = diagram[y][x]
    if (diagram[y][x].equals('+')) {
        if (incoming == DIRECTION.UP || incoming == DIRECTION.DOWN) {
            if (diagram[y][x-1] != ' ' || (diagram[y][x+1] != ' ')) return false
        } else if (incoming == DIRECTION.LEFT || incoming == DIRECTION.RIGHT) {
            if ( (y<diagram.size-1 && x < diagram[y+1].size &&diagram[y+1][x] != ' ') || (y>=1 && x < diagram[y-1].size &&diagram[y-1][x] != ' ')) return false
        }
    } else {
        if (incoming == DIRECTION.DOWN)  return (diagram[y+1][x] == ' ')
        if (incoming == DIRECTION.UP)    return (diagram[y-1][x] == ' ')
        if (incoming == DIRECTION.LEFT)  return (diagram[y][x+1] == ' ')
        if (incoming == DIRECTION.RIGHT) {
            return (diagram[y][x-1] == ' ')
        }
    }
    return true
}

fun fail(message : String) : Nothing {
    throw RuntimeException(message)
}