import java.net.URL

fun checkPassPhrase(str : String) : Boolean {
    val set : MutableSet<String> = emptySet<String>().toMutableSet()
    val list : MutableList<String> = emptyList<String>().toMutableList()
    val words = str.split("\\s+".toRegex()) .forEach {
        set.add(it)
        list.add(it)
    }
    return set.size == list.size
}

fun checkPassPhrase2(str : String) : Boolean {
    val set : MutableSet<String> = emptySet<String>().toMutableSet()
    val list : MutableList<String> = emptyList<String>().toMutableList()
    val words = str.split("\\s+".toRegex()) .forEach {
        val sorted = it.toList().sorted()
        set.add(sorted.toString())
        list.add(sorted.toString())
    }
    return set.size == list.size
}

fun countCorrectPhrases(f : URL) : Int {
    var count = 0
    f.readText().lines().forEach {
        if (checkPassPhrase(it)) {
            count++
        }
    }
    return count
}

fun countCorrectPhrases2(f : URL) : Int {
    var count = 0
    f.readText().lines().forEach {
        if (checkPassPhrase2(it)) {
            count++
        }
    }
    return count
}