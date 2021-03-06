import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val inputStream: InputStream = File("input.txt").inputStream()
    val lineList = mutableListOf<String>()

    var depth: Int = 0
    var range: Int = 0
    var aim: Int = 0
    var horizontalMove: Int = 0
    var aimedDepth: Int = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{
        val str: String = it
        val movement = str.split(" ")
        when(movement[0]){
            "forward" -> range = range + movement[1].toInt()
            "down" -> depth = depth + movement[1].toInt()
            "up" -> depth = depth - movement[1].toInt()
        }
        when(movement[0]){
            "forward" -> {horizontalMove = horizontalMove + movement[1].toInt(); aimedDepth = aimedDepth + (aim * movement[1].toInt())}
            "down" -> aim = aim + movement[1].toInt()
            "up" -> aim = aim - movement[1].toInt()
        }
        println(movement[0] + "> " + movement[1]+ "range: " + range + "depth: " + depth)
        println("final" + aimedDepth * horizontalMove)
        //println(range * depth)
    }
}