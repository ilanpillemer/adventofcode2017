import java.net.URL

var registers : MutableMap<String,Long> = emptyMap<String, Long>().toMutableMap()
var codeListing : MutableMap<Long,String> = emptyMap<Long,String>().toMutableMap()
var pc : Long = 0
var sound : Long = 0
var isEOF = false
var numOfMul = 0

fun initProgram () {
    registers.clear()
    codeListing.clear()
    pc = 0
    sound = 0
    isEOF = false
    numOfMul = 0
    registers.set("a",1)
}
fun executeProgramListing(f: URL) {
    initProgram()
    f.readText().lines().forEachIndexed { index, s -> codeListing.put(index.toLong(),s)  }
    val size : Long = f.readText().lines().size.toLong()
    codeListing.put(size,"EOF")
    while (!isEOF) {
        interpret(codeListing[pc]!!)
    }
    println("pc: $pc sound: $sound registers:$registers")
}

//snd X plays a sound with a frequency equal to the value of X.
//set X Y sets register X to the value of Y.
//add X Y increases register X by the value of Y.
//mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
//mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
//rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
//jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)
fun interpret(s: String) {
    val tokens = s.split("\\s+".toRegex())
    when (tokens[0]) {
        "snd" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            //println ("Tra La La... Frequency :$X")
            sound = X
            pc++
        }
        "set" -> {
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0L)
            }
            registers.set(tokens[1],Y)
            pc++
        }
        "add" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0L)
            }
            val newVal = X + Y
            registers.set(tokens[1],newVal)
            pc++
        }
        "sub" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0L)
            }
            val newVal = X - Y
            registers.set(tokens[1],newVal)
            pc++
        }
        "mul" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0L)
            }
            val newVal = X * Y
            registers.set(tokens[1],newVal)
            pc++
            numOfMul++
        }
        "mod" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0L)
            }
            val newVal = X % Y
            registers.set(tokens[1],newVal)
            pc++
        }
        "rcv" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            if (X != 0L) {
                println("Recovered Sound : $sound")
                isEOF = true
            }
            pc++
        }
        "jgz" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0)
            }
            if (X > 0L) {
                pc = pc + Y
            } else {
                pc++
            }
         }
        "jnz" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers.getOrDefault(tokens[1],0L)
            }
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers.getOrDefault(tokens[2],0)
            }
            if (X != 0L) {
                pc = pc + Y
            } else {
                pc++
            }
        }
        "EOF" -> {
            isEOF = true
        }
         else -> println("SYNTAX ERROR, CORRECT AND TRY AGAIN!")
    }
    println("$tokens --> pc: $pc sound: $sound registers:$registers")
}