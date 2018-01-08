tailrec fun generate_helper(prev_a: Long, prev_b:Long, count: Int, stop : Int, judge: Int) : Int{
    val a = (prev_a * 16807) % 2147483647
    val b = (prev_b * 48271) % 2147483647
    var judged = judge
    if (a.toString(2).padStart(32,'0').drop(16) == b.toString(2).padStart(32,'0').drop(16)) {
        judged++
    }
    if (count == stop) return judged
    else return generate_helper(a,b,count+1,stop, judged)
}

tailrec fun generate_helper_2(prev_a: Long, prev_b:Long, count: Int, stop : Int, judge: Int) : Int{
    var a = prev_a
    var b = prev_b
    do {
        a = (a * 16807) % 2147483647
    } while ((a % 4) != 0L)

    do {
        b = (b * 48271) % 2147483647
    } while ((b % 8) != 0L)
    var judged = judge
    if (a.toString(2).padStart(32,'0').drop(16) == b.toString(2).padStart(32,'0').drop(16)) {
        judged++
    }
    if (count == stop) return judged
    else return generate_helper_2(a,b,count+1,stop, judged)
}

fun generate() : Int {
    return generate_helper(634,301,1,40_000_000, 0)
}

fun generate2() : Int {
    return generate_helper_2(65, 8921,1,5_000_000, 0)
}

fun generate3() : Int {
    return generate_helper_2(634, 301,1,5_000_000, 0)
}