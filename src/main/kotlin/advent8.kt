import java.net.URL

var symbolTable : MutableMap<String,Int> = emptyMap<String,Int>().toMutableMap()

fun String.seenBefore() : Boolean = symbolTable.containsKey(this)

fun String.isDigits() : Boolean {
    return this.startsWith('-') ||
            this.startsWith('0') ||
            this.startsWith('1') ||
            this.startsWith('2') ||
            this.startsWith('3') ||
            this.startsWith('4') ||
            this.startsWith('5') ||
            this.startsWith('6') ||
            this.startsWith('7') ||
            this.startsWith('8') ||
            this.startsWith('9')
}

fun String.isKeyWord() : Boolean {
    return this == "inc" ||
            this == "dec" ||
            this == "if" ||
            this == ">" ||
            this == "<" ||
            this == ">=" ||
            this == "==" ||
            this == "<=" ||
            this == "!="
}

fun parseAndExecute(f: URL) {
    symbolTable.clear()
    println("import kotlin.math.max")
    println("var max = 0")
    // parse into symbol table
    f.readText().lines().forEach {
        it.split("\\s+".toRegex()).forEach {
            if(!it.isKeyWord() &&
                    !it.isDigits() &&
                    !it.seenBefore())  {
                symbolTable.put(it,0)
            }
        }
    }

    symbolTable.forEach() {
        println ("var ${it.key} : Int = 0")
    }

    //syntax directed translation into Kotlin statements
    f.readText().lines().forEach {
        val parsed = it.split("if")
        print("if (${parsed[1]}) ")
        println("{ ${parsed[0].replace("inc","+=").replace("dec","-=")} }")
    }

    symbolTable.forEach() {
        println ("max = max (max,${it.key})")
    }
    println ("println(max)")

}

fun parseAndExecute2(f: URL) {
    symbolTable.clear()
    println("import kotlin.math.max")
    println("var m = 0")
    // parse into symbol table
    f.readText().lines().forEach {
        it.split("\\s+".toRegex()).forEach {
            if(!it.isKeyWord() &&
                    !it.isDigits() &&
                    !it.seenBefore())  {
                symbolTable.put(it,0)
            }
        }
    }

    symbolTable.forEach() {
        println ("var ${it.key} : Int = 0")
    }

    //syntax directed translation into Kotlin statements
    var count : Int = 0
    f.readText().lines().forEach {
        val parsed = it.split("if")
        val variable = parsed[0].split("\\s+".toRegex()).first()
        print("if (${parsed[1]}) {")
        print("${parsed[0].replace("inc","+=").replace("dec","-=")}")
        print(";m = max(m,$variable)")
        println("}")
        count ++
    }
    println ("println(m)")

}