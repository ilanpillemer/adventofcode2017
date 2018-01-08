import java.net.URL

var dancers : CharArray
        = charArrayOf('a','b','c','d','e',
        'f','g','h','i','j','k','l','m','n',
        'o','p')
fun initDancersSimple() {
    dancers = charArrayOf('a','b','c','d','e',
            'f','g','h','i','j','k','l','m','n',
            'o','p')
    dancers = charArrayOf('a','b','c','d','e')
}

var danceHash : MutableMap<String,Boolean> = emptyMap<String,Boolean>().toMutableMap()

fun initDancers() {
    danceHash.clear()
    dancers = charArrayOf('a','b','c','d','e',
            'f','g','h','i','j','k','l','m','n',
            'o','p')
}

fun dance(f : URL) {
    f.readText().lines().forEach {
        val moves = it.split(",")
        moves.forEach {
            if (it[0].equals('s')) {
                val size = it.substring(1)
                spin(size.toInt())
            }
            if (it[0].equals('x')) {
                val pieces = it.substring(1).split('/')
                exchange(pieces[0].toInt(), pieces[1].toInt())
            }
            if (it[0].equals('p')) {
                it.substring(1).split('/')
                val pieces = it.substring(1).split('/')
                swap(pieces[0].toCharArray()[0], pieces[1].toCharArray()[0])
            }
        }
    }
}

fun danceLots(f : URL, count : Int) {
    for (i in 0.until(count)) {
            dance(f)
        }
}

fun findCycle(f : URL, count : Int) : Int{
    for (i in 0.until(count)) {
        if (!danceHash.containsKey(dancers.toList().toString())) {
            danceHash.put(dancers.toList().toString(),true)
            dance(f)
        } else {
           return i
        }
    }
    return count
}

fun spin(s : Int) {
    val clone = dancers.clone()
    for (i in 0.until(dancers.size)) {
        dancers[i] = clone[(i+(dancers.size-s)) % dancers.size]
    }
}

fun exchange(s: Int, t:Int) {
    val temp = dancers[s]
    dancers[s] = dancers[t]
    dancers[t] = temp
}

fun swap(s: Char, t: Char) {
    var temps = 0
    var tempt= 0
    dancers.forEachIndexed { index, c ->
        if (c.equals(s)) temps = index
        if (c.equals(t)) tempt = index
    }
    exchange(temps, tempt)
}