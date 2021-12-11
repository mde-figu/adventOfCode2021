import java.io.File

fun getInputData(filename: String) = File("$filename").useLines { it.toList() }

fun mostCommonToRates(input: List<String>): Int {
    var gamma = ""
    for (i in input[0].indices) {
        gamma += input.map { it[i] }.groupBy { it }.maxByOrNull { it.value.size }?.key
    }
    val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")
    //println(gamma.toInt(2), println(epsilon.toInt(2)))
    return gamma.toInt(2) * epsilon.toInt(2)
}

fun List<String>.filterBy(position: Int, defEqual: String, sort: (Map<Char, List<Char>>) -> Char): List<String> {
    if (this.size == 1) return this
    val jointInput = this.map { it[position] }.groupBy { it }
    val isEqual = jointInput.values.toMutableList()[0].size == jointInput.values.toMutableList()[1].size
    return this.filter {
        if (isEqual) {
            //println(jointInput)
            it[position].toString() == defEqual
        } else it[position] == sort(jointInput)
    }
}

fun lifeSupport(input: List<String>): Int {
    var OxigenRating = input
    var ScrubberRating = input
    for (i in input[0].indices) {
        OxigenRating = OxigenRating.filterBy(i, "1") { it.maxByOrNull { it.value.size }?.key!! }
        ScrubberRating = ScrubberRating.filterBy(i, "0") { it.minByOrNull { it.value.size }?.key!! }
    }
    return ScrubberRating[0].toInt(2) * OxigenRating[0].toInt(2)
}

fun main() {
    val input = getInputData("input")
    println("Power Consumption: ${mostCommonToRates(input)}")
    println("Life Support Rating: ${lifeSupport(input)}")
}