var circularBuffer : MutableList<Int> = listOf<Int>(0).toMutableList()
var pos = 0
var value = 0
var size = 1
fun initBuffer() {
    circularBuffer = arrayOf<Int>(0).toMutableList()
    size = 1
}
fun spinStepOld(steps : Int, element : Int) : Int {
    val size = circularBuffer.size
    pos = (pos + steps) % size
    circularBuffer.add(pos + 1, element)
    pos++
   return pos
}

fun spinStep(steps : Int, element : Int) : Int {
    pos = (pos + steps) % size
    if (pos==0) value = element
    //circularBuffer.add(pos + 1, element)
    size++
    pos++
    return pos
}