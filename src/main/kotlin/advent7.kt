import java.net.URL

data class Tower(val name : String, val weight : Int, val children: List<String>)
var towers : MutableMap<String, Tower> = emptyMap<String,Tower>().toMutableMap()

fun nameAndWeight(str : String) : Pair<String,Int> {
    val x = str.trim().replace("(","").replace(")","").split("\\s+".toRegex())
    return Pair(x.first(),x.last().toInt())
}

fun areChildrenUnbalanced(tower: Tower)  {
    var weights : MutableSet<Int> = emptySet<Int>().toMutableSet()
    //if (tower.children.isEmpty()) println("balanced")
    tower.children.forEach {
        weights.add(getCombinedWeights(towers[it]!!))
    }
    if (weights.size>1) {
        println("${tower.name} + ${tower.weight}")
        println("children ${tower.children}")
        tower.children.forEach {
            println("$it ${getCombinedWeights(towers[it]!!)}")
        }

        println(weights.toIntArray()[0] - weights.toIntArray()[1])
    }
}


fun children(str : String) : List<String> {

    if (str.trim() == "") return emptyList()
    else {
        var list : MutableList<String> = emptyList<String>().toMutableList()
        str.trim().split(",").forEach {
            list.add(it.trim())
        }
        return list
    }

}

fun getCombinedWeights(tower:Tower) : Int{
    var total : Int
    if (tower.children.isEmpty()) return tower.weight
    else {
        return tower.children.fold(tower.weight) { total, s -> total +  getCombinedWeights(towers[s]!!) }
    }
}

fun checkWeights(f : URL)  {
    loadTowersIntoMap(f)
    towers.forEach {
        println()
    }

}


fun getTowerRoot(f : URL) : String {
    loadTowersIntoMap(f)
    var all : MutableList<String> = emptyList<String>().toMutableList()
    var child : MutableList<String> = emptyList<String>().toMutableList()
    towers.forEach {
        all.add(it.key)
        it.value.children.forEach {
            child.add(it)
        }
    }
    val root = all.filterNot { child.contains(it)  }
    return root[0]
}

fun loadTowersIntoMap(f: URL) {
    f.readText().lines().forEach {
        val split = it.split("->")
        val (name, weight) = nameAndWeight(split.first())
        var children = emptyList<String>()
        if (split.size > 1) {
            children = children(split[1])
        }
        towers.put(name, Tower(name, weight, children))
    }
}