import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import java.net.URL
import org.hamcrest.*

//<>, empty garbage.
//<random characters>, garbage containing random characters.
//<<<<>, because the extra < are ignored.
//<{!>}>, because the first > is canceled.
//<!!>, because the second ! is canceled, allowing the > to terminate the garbage.
//<!!!>>, because the second ! and the first > are canceled.
//<{o"i!a,<{i<a>, which ends at the first >.
class Advent9Test {
    @Test
    fun testIsGarbage() {
        println(cleanOutGarbage("<!!!>>".toCharArray()))
        assertEquals("not garbage",cleanOutGarbage("not garbage".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<random characters>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<<<<>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<{!>}>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<!!>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("<!!!>>".toCharArray()).joinToString ("" ))
        assertEquals("",cleanOutGarbage("""<{o\"i!a,<{i<a>""".toCharArray()).joinToString ("" ))
        assertEquals("Hello Eli!",cleanOutGarbage("Hello <garbage>Eli!".toCharArray()).joinToString ("" ))
    }


//{}, 1 group.
//{{{}}}, 3 groups.
//{{},{}}, also 3 groups.
//{{{},{},{{}}}}, 6 groups.
//{<{},{},{{}}>}, 1 group (which itself contains garbage).
//{<a>,<a>,<a>,<a>}, 1 group.
//{{<a>},{<a>},{<a>},{<a>}}, 5 groups.
@Test
fun testGrouping() {
    assertEquals(1, countGroups(cleanOutGarbage("{}".toCharArray())) )
    assertEquals(3, countGroups(cleanOutGarbage("{{{}}}".toCharArray())) )
    assertEquals(6, countGroups(cleanOutGarbage("{{{},{},{{}}}}".toCharArray())) )
    assertEquals(1, countGroups(cleanOutGarbage("{<{},{},{{}}>}".toCharArray())) )
    assertEquals(1, countGroups(cleanOutGarbage("{<a>,<a>,<a>,<a>}".toCharArray())) )
    assertEquals(5, countGroups(cleanOutGarbage("{{<a>},{<a>},{<a>},{<a>}}".toCharArray())) )
}

//    {}, score of 1.
//    {{{}}}, score of 1 + 2 + 3 = 6.
//    {{},{}}, score of 1 + 2 + 2 = 5.
//    {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
//    {<a>,<a>,<a>,<a>}, score of 1.
//    {{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3.
@Test
fun testScoring() {
    countGroups(cleanOutGarbage("{}".toCharArray()))
    assertEquals(1,score)
    countGroups(cleanOutGarbage("{{{}}}".toCharArray()))
    assertEquals(6,score)
    countGroups(cleanOutGarbage("{{},{}}".toCharArray()))
    assertEquals(5,score)
    countGroups(cleanOutGarbage("{{{},{},{{}}}}".toCharArray()))
    assertEquals(16,score)
    countGroups(cleanOutGarbage("{<a>,<a>,<a>,<a>}".toCharArray()))
    assertEquals(1,score)
    countGroups(cleanOutGarbage("{{<ab>},{<ab>},{<ab>},{<ab>}}".toCharArray()))
    assertEquals(9,score)
    countGroups(cleanOutGarbage("{{<!!>},{<!!>},{<!!>},{<!!>}}".toCharArray()))
    assertEquals(9,score)
    countGroups(cleanOutGarbage("{{<a!>},{<a!>},{<a!>},{<ab>}}".toCharArray()))
    assertEquals(3,score)
}

@Test fun testSolu1() {
    val classLoader = javaClass.classLoader
    val file : URL = this.javaClass.getResource("/day9-input")

//    println(cleanOutGarbage(file.readText().toCharArray()))
    countGroups(cleanOutGarbage(file.readText().toCharArray()))
    println(score)
    println(garbageCount)
}

    @Test fun testSolu2() {
        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day9-input2")

//    println(cleanOutGarbage(file.readText().toCharArray()))
        cleanOutGarbage(file.readText().toCharArray())
        println()
        println(garbageCount)
    }

    @Test fun wtf() {

        val classLoader = javaClass.classLoader
        val file : URL = this.javaClass.getResource("/day9-input2")
        val cleaned = cleanOutGarbage(file.readText().toCharArray())
        val dirty = file.readText().toCharArray()
        println(cleaned.size)
        println(dirty.size)


    }

    @Test fun testCount() {

        var cleaned = cleanOutGarbage("<>".toCharArray())
        assertEquals(0,garbageCount)
        assertEquals(0,garbageCount) // 0
        cleanOutGarbage("<random characters>".toCharArray())
        assertEquals(17,garbageCount) // 17
        cleanOutGarbage("<<<<>".toCharArray())
        assertEquals(3,garbageCount) // 3
        cleanOutGarbage("<{!>}>".toCharArray())
        assertEquals(2,garbageCount) //2
        cleanOutGarbage("<!!>".toCharArray())
        assertEquals(0,garbageCount)
        cleanOutGarbage("<!!!>>".toCharArray())
        assertEquals(0,garbageCount)
        cleanOutGarbage("""<{o"i!a,<{i<a>""".toCharArray())
        assertEquals(10,garbageCount)

//        <>, 0 characters.
//        <random characters>, 17 characters.
//        <<<<>, 3 characters.
//        <{!>}>, 2 characters.
//        <!!>, 0 characters.
//        <!!!>>, 0 characters.
//        <{o"i!a,<{i<a>, 10 characters.



    }


}