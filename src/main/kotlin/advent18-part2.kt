import java.net.URL
import java.util.*

var registers0 : MutableMap<String,Long> = emptyMap<String, Long>().toMutableMap()
var registers1 : MutableMap<String,Long> = emptyMap<String, Long>().toMutableMap()

var codeListing0 : MutableMap<Long,String> = emptyMap<Long,String>().toMutableMap()
var codeListing1 : MutableMap<Long,String> = emptyMap<Long,String>().toMutableMap()

var queue0: Queue<Long> = ArrayDeque<Long>()
var queue1: Queue<Long> = ArrayDeque<Long>()

var pc0 : Long = 0
var pc1 : Long = 0

var sound0 : Long = 0
var sound1 : Long = 0

var isSuspended0 = false
var isSuspended1 = false

var isEOF0 = false
var isEOF1 = false

var numSent1 : Long = 0
var numSent0 : Long = 0

fun initPrograms () {
    registers0.clear()
    codeListing0.clear()
    queue0.clear()
    pc0 = 0
    sound0 = 0
    isSuspended0 = false
    isEOF0 = false

    registers1.clear()
    codeListing1.clear()
    queue1.clear()
    pc1 = 0
    sound1 = 0
    isSuspended1 = false
    isEOF1 = false

    numSent1 = 0
    numSent0 = 0

    registers0.set("p",0)
    registers1.set("p",1)

}
fun executeProgramListings(f: URL) {
    initPrograms()
    f.readText().lines().forEachIndexed { index, s -> codeListing0.put(index.toLong(),s)  }
    f.readText().lines().forEachIndexed { index, s -> codeListing1.put(index.toLong(),s)  }

    val size : Long = f.readText().lines().size.toLong()
    codeListing0.put(size,"EOF")
    codeListing1.put(size,"EOF")


    while ((!isSuspended0 || !isSuspended1)) {
        while (!isSuspended1) {
            interpret1(codeListing1[pc1]!!)
        }
        while (!isSuspended0) {
            interpret0(codeListing0[pc0]!!)
        }

        println("isSusp1: $isSuspended1 isSusp0: $isSuspended0")
        println("registers1: $registers1 registers0: $registers0")
        //println("queu0 size ${queue0.size} q1 size: ${queue1.size}")
        println("numSent1: $numSent1 numSent0: $numSent0")

    }
    println("numSent1: $numSent1 numSent0: $numSent0")
}

//snd X plays a sound with a frequency equal to the value of X.
//set X Y sets register X to the value of Y.
//add X Y increases register X by the value of Y.
//mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
//mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
//rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
//jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)
fun interpret0(s: String) {
    val tokens = s.split("\\s+".toRegex())
    when (tokens[0]) {
        "snd" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers0.getOrDefault(tokens[1],0L)
            }
            //println ("Prog 0 .. sending :$X")
            //
            queue1.add(X)
            //println("Queue 1 size: ${queue1.size}")
            isSuspended1=false
            //

            numSent0++
            pc0++
        }
        "set" -> {
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers0.getOrDefault(tokens[2],0L)
            }
            registers0.set(tokens[1],Y)
            pc0++
        }
        "add" -> {
            val X = registers0.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers0.getOrDefault(tokens[2],0L)
            }
            val newVal = X + Y
            registers0.set(tokens[1],newVal)
            pc0++
        }
        "mul" -> {
            val X = registers0.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers0.getOrDefault(tokens[2],0L)
            }
            val newVal = X * Y
            registers0.set(tokens[1],newVal)
            pc0++
        }
        "mod" -> {
            val X = registers0.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers0.getOrDefault(tokens[2],0L)
            }
            val newVal = X % Y
            registers0.set(tokens[1],newVal)
            pc0++
        }
        "rcv" -> {

            if (!queue0.isEmpty()) {
                val Y = queue0.remove()
                //println("Prog 0 Received Value : $Y.. Queue size now: ${queue0.size}" )
                registers0.set(tokens[1],Y)
                pc0++
            } else {
                //println("Prog 0 Suspended and waiting...")
                isSuspended0 = true
            }
        }
        "jgz" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers0.getOrDefault(tokens[1],0L)
            }
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers0.getOrDefault(tokens[2],0)
            }
            if (X > 0) {
                pc0 = pc0 + Y
            } else {
                pc0++
            }
         }
        "EOF" -> {
            isEOF0 = true
            isSuspended0 = true
        }
         else -> println("SYNTAX ERROR, CORRECT AND TRY AGAIN!")
    }
    //println("$tokens --> prog: 0 pc: $pc0 queue:$queue0 registers:$registers0")
}

fun interpret1(s: String) {
    val tokens = s.split("\\s+".toRegex())
    when (tokens[0]) {
        "snd" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers1.getOrDefault(tokens[1],0L)
            }
            //println ("Prog 1 .. sending :$X")
            numSent1++
            //
            queue0.add(X)
            //println("Queue 0 size: ${queue0.size}")
            isSuspended0 = false
            //
            pc1++
        }
        "set" -> {
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers1.getOrDefault(tokens[2],0L)
            }
            registers1.set(tokens[1],Y)
            pc1++
        }
        "add" -> {
            val X = registers1.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers1.getOrDefault(tokens[2],0L)
            }
            val newVal = X + Y
            registers1.set(tokens[1],newVal)
            pc1++
        }
        "mul" -> {
            val X = registers1.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers1.getOrDefault(tokens[2],0L)
            }
            val newVal = X * Y
            registers1.set(tokens[1],newVal)
            pc1++
        }
        "mod" -> {
            val X = registers1.getOrDefault(tokens[1],0L)
            val Y = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers1.getOrDefault(tokens[2],0L)
            }
            val newVal = X % Y
            registers1.set(tokens[1],newVal)
            pc1++
        }
        "rcv" -> {
            if (!queue1.isEmpty()) {
                val Y = queue1.remove()
                //println("Prog 1 Received Value : $Y.. Queue size now: ${queue1.size}")
                registers1.set(tokens[1],Y)
                pc1++
            } else {
                //println("Prog 0 Suspended and waiting...")
                isSuspended1 = true
            }
        }
        "jgz" -> {
            val X : Long = if (tokens[1].isDigits()) {
                tokens[1].toLong()
            } else {
                registers1.getOrDefault(tokens[1],0L)
            }
            val Y : Long = if (tokens[2].isDigits()) {
                tokens[2].toLong()
            } else {
                registers1.getOrDefault(tokens[2],0)
            }
            if (X > 0) {
                pc1 = pc1 + Y
            } else {
                pc1++
            }
        }
        "EOF" -> {
            isEOF1 = true
            isSuspended1 = true
        }
        else -> println("SYNTAX ERROR, CORRECT AND TRY AGAIN!")
    }
    //println("$tokens --> prog: 1 pc: $pc1 queue:$queue1 registers:$registers1")
}