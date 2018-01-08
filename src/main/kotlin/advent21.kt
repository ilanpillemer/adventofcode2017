import java.net.URL

var fractal : Array<String> = emptyArray()
fun initFractal() {
    val line1 = ".#."
    val line2 = "..#"
    val line3 = "###"
    fractal = fractal.plus(line1)
    fractal = fractal.plus(line2)
    fractal = fractal.plus(line3)
}

var rules : MutableMap<String,String> = emptyMap<String,String>().toMutableMap()
fun loadRules(f : URL) {
    f.readText().lines().forEach {
        val part = it.split(("=>"))
        val input = part[0].trim()
        val output = part[1].trim()
        rules.put(input,output)
    }
}

fun applyRules(state:Array<String>, count : Int, end: Int) : Array<String>{
    if (count > end - 1) return state
    else {
        if (state.size % 2 == 0) {
            var newState : Array<String> = emptyArray()
            for (i in 0.until(state.size) step 2) {
                var acc : MutableList<String> = listOf<String>("","","").toMutableList()
                for (j in 0.until(state.size) step 2) {
                    var a: Array<String> = emptyArray()
                    a = a.plus(state[i].substring(j, j + 2))
                    a = a.plus(state[i + 1].substring(j, j + 2))
                    val m = findMatch(a).split('/')
                    acc[0] += m[0]
                    acc[1] += m[1]
                    acc[2] += m[2]
                }
                newState = newState + acc
            }

            return applyRules(newState, count + 1, end)

        }
        if (state.size % 3 == 0) {
            var newState : Array<String> = emptyArray()
            for (i in 0.until(state.size) step 3) {
                var acc: MutableList<String> = listOf<String>("", "", "","").toMutableList()
                for (j in 0.until(state.size) step 3) {
                    var a: Array<String> = emptyArray()
                    a = a.plus(state[i].substring(j, j + 3))
                    a = a.plus(state[i + 1].substring(j, j + 3))
                    a = a.plus(state[i + 2].substring(j, j + 3))
                    val m = findMatch(a).split("/")
                    acc[0] += m[0]
                    acc[1] += m[1]
                    acc[2] += m[2]
                    acc[3] += m[3]
                }
                newState = newState + acc
            }
            return applyRules(newState, count + 1, end)
        }

    }
    return state
}

fun findMatch(state: Array<String>) : String {
    var string1 = ""
    var string2 = ""
    var string3 = ""
    var string4 = ""
    var string5 = ""
    var string6 = ""
    var string7 = ""
    var string8 = ""

        state.forEach {
            string1 += it + "/"
            string2 += it.reversed() + "/"
        }

    string1 = string1.substring(0,string1.length-1)
    string2 = string2.substring(0,string2.length-1)

       for (i in 0.until(state.size)) {
           if (state.size == 3) string3 += state[2][i]
           string3 += state[1][i]
           string3 += state[0][i]
           string3 += "/"

           string4 += state[0][i]
           string4 += state[1][i]
           if (state.size == 3) string4 += state[2][i]
           string4 += "/"
       }

    for (i in (state.size-1).downTo(0)) {
        string5 += state[i][0]
        string5 += state[i][1]
        if (state.size == 3) string5 += state[i][2]
        string5 += "/"

        if (state.size == 3) string6 += state[i][2]
        string6 += state[i][1]
        string6 += state[i][0]
        string6 += "/"
    }

    for (i in (state.size-1).downTo(0)) {
        if (state.size == 3) string7 += state[i][2]
    }
    if (state.size == 3) string7 += "/"
    for (i in (state.size-1).downTo(0)) {
        string7 += state[i][1]
    }
    string7 += "/"
    for (i in (state.size-1).downTo(0)) {
        string7 += state[i][0]
    }

    for (i in 0.until(state.size)) {
        if (state.size == 3) string8 += state[i][2]
    }
    if (state.size == 3) string8 += "/"
    for (i in 0.until(state.size)) {
        string8 += state[i][1]
    }
    string8 += "/"
    for (i in 0.until(state.size)) {
        string8 += state[i][0]
    }

    string3 = string3.substring(0,string3.length-1)
    string4 = string4.substring(0,string4.length-1)
    string5 = string5.substring(0,string5.length-1)
    string6 = string6.substring(0,string6.length-1)

    if (rules.containsKey(string1)) return rules[string1]!!
    if (rules.containsKey(string2)) return rules[string2]!!
    if (rules.containsKey(string3)) return rules[string3]!!
    if (rules.containsKey(string4)) return rules[string4]!!
    if (rules.containsKey(string5)) return rules[string5]!!
    if (rules.containsKey(string6)) return rules[string6]!!
    if (rules.containsKey(string7)) return rules[string7]!!
    if (rules.containsKey(string8)) return rules[string8]!!

    fail("BURP!")
}