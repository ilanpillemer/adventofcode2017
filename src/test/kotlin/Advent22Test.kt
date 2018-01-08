import org.junit.Test
import org.junit.Assert.*


class Advent22Test {
    @Test fun pass () {

    }
    @Test fun test() {
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodes(file)
        assertEquals(2,nodes.size)
        assertTrue(nodes.containsKey(Pos(2,0)))
        assertTrue(nodes.containsKey(Pos(0,1)))
    }

    @Test fun test7bursts(){
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodes(file)
        println(nodes)
        val count = runInfector(0,7)
        assertEquals(5,count)
        println(nodes)
    }

    @Test fun test70bursts(){
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodes(file)
        println(nodes)
        val count = runInfector(0,70)
        assertEquals(41,count)
    }

    @Test fun test10000bursts(){
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodes(file)
        println(nodes)
        val count = runInfector(0,10000)
        assertEquals(5587,count)
    }

    @Test fun test10000burstsSol1(){
        var file = this.javaClass.getResource("/day22-input")
        initVirusInfector()
        loadNodes(file)
        println(nodes)
        val count = runInfector(0,10000)
        assertEquals(5240,count)
    }

    @Test fun test100burstsTest2(){
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodesStates(file)
        println(nodes)
        val count = runInfectorState(0,100)
        assertEquals(26,count)
    }

    @Test fun test10000000burstsTest2(){
        var file = this.javaClass.getResource("/day22-test")
        initVirusInfector()
        loadNodesStates(file)
        println(nodes)
        val count = runInfectorState(0,10000000)
        assertEquals(2511944,count)
    }

    @Test fun test10000000burstsSol2(){
        var file = this.javaClass.getResource("/day22-input")
        initVirusInfector()
        loadNodesStates(file)
        println(nodes)
        val count = runInfectorState(0,10000000)
        assertEquals(2512144,count)
    }


}