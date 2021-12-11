import java.io.File

data class Number(val value: Int, val x: Int, val y: Int, var drawn: Boolean = false)

data class Ticket(val numbers: MutableList<Number>){
    fun winner() = fullRow() || fullColumn()
    fun fullRow() = numbers.groupBy { it.x }.any { (_, value) -> value.all(Number::drawn) }
    fun fullColumn() = numbers.groupBy { it.y }.any { (_, value) -> value.all(Number::drawn) }
}

fun getInputData(filename: String) = File("$filename").useLines { it.toList() }

fun main() {
    val data = getInputData("input")
    fun part1(data: List<String>): Int {
        val drawnNumbers = data.first().split(",").map(String::toInt)
        val tickets = mutableListOf<Ticket>()
        val ticketNumbers = data.subList(2, data.size).flatMap {
            it.split(" ").filterNot(String::isBlank).map(String::toInt)
        }.toList()

        val numberOfTickets = ticketNumbers.size / 25
        repeat(numberOfTickets) { ticketIndex ->
            val numbers = mutableListOf<Number>()
            repeat(5) { rowIndex ->
                repeat(5) { columnIndex ->
                    val numberIndex = 25 * ticketIndex + 5 * rowIndex + columnIndex
                    numbers.add(Number(ticketNumbers[numberIndex], rowIndex, columnIndex))
                }
            }
            tickets.add(Ticket(numbers))
        }
        for (eachNumber in drawnNumbers) {
            for (eachTicket in tickets) {
                for (number in eachTicket.numbers) {
                    if (number.value == eachNumber) {
                        number.drawn = true
                        if (eachTicket.winner()) {
                            return eachTicket.numbers.filter { it.drawn.not() }
                                .sumOf(Number::value) * eachNumber
                        }
                    }
                }
            }
        }
        error("No Winner")
    }

    fun part2(data: List<String>): Int {
        val drawnNumbers = data.first().split(",").map(String::toInt)
        val tickets = mutableListOf<Ticket>()
        val ticketsNumbers = data.subList(2, data.size).flatMap {
            it.split(" ").filterNot(String::isBlank).map(String::toInt)
        }.toList()

        val numberOfTickets = ticketsNumbers.size / 25
        repeat(numberOfTickets) { ticketIndex ->
            val numbers = mutableListOf<Number>()
            repeat(5) { rowIndex ->
                repeat(5) { columnIndex ->
                    val numberIndex = 25 * ticketIndex + 5 * rowIndex + columnIndex
                    numbers.add(Number(ticketsNumbers[numberIndex], rowIndex, columnIndex))
                }
            }
            tickets.add(Ticket(numbers))
        }
        for (drawnNumber in drawnNumbers) {
            for (ticket in tickets) {
                for (number in ticket.numbers){
                    if (number.value == drawnNumber) {
                        number.drawn = true
                        if (tickets.all(Ticket::winner)) {
                            return ticket.numbers.filter {it.drawn.not()}.sumOf(Number::value) * drawnNumber
                        }
                    }
                }
            }
        }
        error("No winner")
    }

        println("Part 1: " + part1(data))
        println("Part 2: " + part2(data))
}