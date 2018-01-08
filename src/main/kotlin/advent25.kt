import State.*

var tape : MutableMap<Int,Int> = emptyMap<Int,Int>().toMutableMap()
var cursor = 0
var state = OFF
enum class State {
    AT,BT,A,B,C,D,E,F,
    OFF
}

fun initTuring() {
    tape =  emptyMap<Int,Int>().toMutableMap()
    cursor = 0
}

fun act() {
    val current = tape.getOrDefault(cursor,0)
    when(state) {
        AT -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor++
                state = BT
            } else {
                tape[cursor] = 0
                cursor--
                state = BT
            }
        }
        BT -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = AT
            } else {
                tape[cursor] = 1
                cursor++
                state = AT
            }
        }
        A -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor++
                state = B
            } else {
                tape[cursor] = 0
                cursor--
                state = E
            }
        }
        B -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = C
            } else {
                tape[cursor] = 0
                cursor++
                state = A
            }
        }
        C -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = D
            } else {
                tape[cursor] = 0
                cursor++
                state = C
            }
        }
        D -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = E
            } else {
                tape[cursor] = 0
                cursor--
                state = F
            }
        }
        E -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = A
            } else {
                tape[cursor] = 1
                cursor--
                state = C
            }
        }
        F -> {
            if (current == 0) {
                tape[cursor] = 1
                cursor--
                state = E
            } else {
                tape[cursor] = 1
                cursor++
                state = A
            }
        }
        else -> fail("KERWHANG")
    }
}

fun runMachine(s : State, steps : Int) : Int {
    initTuring()
    state = s
    return runMachineHelper(0, steps)
}

tailrec fun runMachineHelper(step: Int, end: Int) : Int {
    if (step == end) return checksum()
    else {
        act()
        return runMachineHelper(step + 1, end)
    }
}

fun checksum() : Int  {

    var count = 0
    tape.forEach {
        if (it.value == 1) {
            count++
        }
    }

    return count
}
