import java.net.URL

var nodes : MutableMap<Pos,Boolean> = emptyMap<Pos,Boolean>().toMutableMap()
var nodesState : MutableMap<Pos,VIRALSTATE> = emptyMap<Pos,VIRALSTATE>().toMutableMap()
var currentDirection : DIRECTION = DIRECTION.UP
var currentNode = Pos(0,0)
var infectionBursts = 0

enum class VIRALSTATE {
    CLEAN,
    WEAK,
    INFECTED,
    FLAGGED
}

fun loadNodes(f : URL) {

    var count = 0
    f.readText().lines().forEach {
        count++
    }

    val shift : Int =  count / 2
    f.readText().lines().forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            if (c=='#') {
                nodes.put(Pos(x-shift,shift-y),true)
            }
        }
    }
    nodes.forEach {
    }
}

fun loadNodesStates(f : URL) {

    var count = 0
    f.readText().lines().forEach {
        count++
    }

    val shift : Int =  count / 2
    f.readText().lines().forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            if (c=='#') {
                nodesState.put(Pos(x-shift,shift-y),VIRALSTATE.INFECTED)
            }
        }
    }

    nodesState.forEach {

    }
}


fun updateDirection() {
    if ((nodes.getOrDefault(currentNode,false)) == true) {
        when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.RIGHT
            DIRECTION.RIGHT -> currentDirection = DIRECTION.DOWN
            DIRECTION.DOWN -> currentDirection = DIRECTION.LEFT
            DIRECTION.LEFT -> currentDirection = DIRECTION.UP
        }
    } else {
        when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.LEFT
            DIRECTION.LEFT -> currentDirection = DIRECTION.DOWN
            DIRECTION.DOWN -> currentDirection = DIRECTION.RIGHT
            DIRECTION.RIGHT -> currentDirection = DIRECTION.UP
        }
    }
}


fun updateDirectionViralState() {
    val currentState = (nodesState.getOrDefault(currentNode,VIRALSTATE.CLEAN))
    when (currentState) {
        VIRALSTATE.INFECTED -> when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.RIGHT
            DIRECTION.RIGHT -> currentDirection = DIRECTION.DOWN
            DIRECTION.DOWN -> currentDirection = DIRECTION.LEFT
            DIRECTION.LEFT -> currentDirection = DIRECTION.UP
        }
        VIRALSTATE.CLEAN -> when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.LEFT
            DIRECTION.LEFT -> currentDirection = DIRECTION.DOWN
            DIRECTION.DOWN -> currentDirection = DIRECTION.RIGHT
            DIRECTION.RIGHT -> currentDirection = DIRECTION.UP
        }
        VIRALSTATE.WEAK -> when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.UP
            DIRECTION.LEFT -> currentDirection = DIRECTION.LEFT
            DIRECTION.DOWN -> currentDirection = DIRECTION.DOWN
            DIRECTION.RIGHT -> currentDirection = DIRECTION.RIGHT
        }
        VIRALSTATE.FLAGGED -> when(currentDirection) {
            DIRECTION.UP -> currentDirection = DIRECTION.DOWN
            DIRECTION.LEFT -> currentDirection = DIRECTION.RIGHT
            DIRECTION.DOWN -> currentDirection = DIRECTION.UP
            DIRECTION.RIGHT -> currentDirection = DIRECTION.LEFT
        }
    }
}

fun updateAndMoveVirusInfector() {
    if (nodes.getOrDefault(currentNode,false) == true) {
        nodes.remove(currentNode)
    } else {
        nodes.put(currentNode,true)
        infectionBursts++
    }
    val (x,y) = currentNode
    when (currentDirection) {
        DIRECTION.UP -> currentNode = Pos(x,y+1)
        DIRECTION.RIGHT -> currentNode = Pos(x+1,y)
        DIRECTION.DOWN -> currentNode = Pos(x,y-1)
        DIRECTION.LEFT -> currentNode = Pos(x-1,y)
    }
}

fun updateAndMoveVirusStateInfector() {
    val currentState = (nodesState.getOrDefault(currentNode,VIRALSTATE.CLEAN))
    when (currentState) {
        VIRALSTATE.FLAGGED -> nodesState.remove(currentNode)
        VIRALSTATE.CLEAN -> nodesState.put(currentNode,VIRALSTATE.WEAK)
        VIRALSTATE.WEAK -> {
            nodesState.put(currentNode,VIRALSTATE.INFECTED)
            infectionBursts++
        }
        VIRALSTATE.INFECTED -> nodesState.put(currentNode,VIRALSTATE.FLAGGED)
    }

    val (x,y) = currentNode
    when (currentDirection) {
        DIRECTION.UP -> currentNode = Pos(x,y+1)
        DIRECTION.RIGHT -> currentNode = Pos(x+1,y)
        DIRECTION.DOWN -> currentNode = Pos(x,y-1)
        DIRECTION.LEFT -> currentNode = Pos(x-1,y)
    }
}

fun initVirusInfector() {
    nodes.clear()
    nodesState.clear()
    currentDirection = DIRECTION.UP
    infectionBursts = 0
}

fun runInfector(bursts : Int, end : Int) : Int {
    if (bursts == end  ) return infectionBursts
    else {
        updateDirection()
        updateAndMoveVirusInfector()
        return runInfector(bursts + 1, end)
    }

}

tailrec fun runInfectorState(bursts : Int, end : Int) : Int {
    if (bursts == end  ) return infectionBursts
    else {
        updateDirectionViralState()
        updateAndMoveVirusStateInfector()
        return runInfectorState(bursts + 1, end)
    }

}
