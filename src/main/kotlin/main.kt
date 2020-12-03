import year2020.Day3
import java.io.File

fun main() {
    println(Day3(InputReader(year = 2020, day = 3)).answerPart2())
}

class InputReader(year: Int, day: Int) {
    private val source = "src/main/resources/input/year$year/day${day}.txt"

    fun asIntSet() = File(source).useLines { lines -> lines.map { it.toInt() }.toSet() }
    fun asStringSet() = File(source).useLines { it.toSet() }
    fun asStringList() = File(source).useLines { it.toList() }
}

interface Puzzle {
    fun answerPart1(): Int
    fun answerPart2(): Int
}

class NoSolutionFoundException : RuntimeException()