import java.io.File

fun getInputData(filename: String) = File("$filename").useLines { it.toList() }

fun main() {
    val data = getInputData("input")
    fun part1(data: List<String>): Int {
        val drawnNumbers = data.first().split("->").map(String::toInt)
        val tickets = mutableListOf<Ticket>()
        val ticketNumbers = data.subList(2, data.size).flatMap {
            it.split(" ").filterNot(String::isBlank).map(String::toInt)
        }.toList()

    }
}