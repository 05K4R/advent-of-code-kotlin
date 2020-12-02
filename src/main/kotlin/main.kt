import year2020.Day1
import year2020.Day2
import java.io.File
import java.lang.RuntimeException

fun main() {
    println(Day1(InputReader(year = 2020, day = 1).asIntSet()).solutionPartOne())
    println(Day1(InputReader(year = 2020, day = 1).asIntSet()).solutionPartTwo())

    println(Day2(InputReader(year = 2020, day = 2).asStringSet()).solutionPartOne())
    println(Day2(InputReader(year = 2020, day = 2).asStringSet()).solutionPartTwo())
}

class InputReader(year: Int, day: Int) {
    private val source = "src/main/resources/input/year$year/day${day}.txt"

    fun asIntSet() = File(source).useLines { lines -> lines.map { it.toInt() }.toSet() }
    fun asStringSet() = File(source).useLines { it.toSet() }
}

interface Puzzle {
    fun solutionPartOne(): Int
    fun solutionPartTwo(): Int
}

class NoSolutionFoundException : RuntimeException()