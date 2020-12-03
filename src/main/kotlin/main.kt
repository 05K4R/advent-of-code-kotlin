import year2020.Day1
import year2020.Day2
import java.io.File

fun main() {
    println(Day1(InputReader(year = 2020, day = 1)).answerPart1())
    println(Day1(InputReader(year = 2020, day = 1)).answerPart2())

    println(Day2(InputReader(year = 2020, day = 2)).answerPart1())
    println(Day2(InputReader(year = 2020, day = 2)).answerPart2())
}

class InputReader(year: Int, day: Int) {
    private val source = "src/main/resources/input/year$year/day${day}.txt"

    fun asIntSet() = File(source).useLines { lines -> lines.map { it.toInt() }.toSet() }
    fun asStringSet() = File(source).useLines { it.toSet() }
}

interface Puzzle {
    fun answerPart1(): Int
    fun answerPart2(): Int
}

class NoSolutionFoundException : RuntimeException()