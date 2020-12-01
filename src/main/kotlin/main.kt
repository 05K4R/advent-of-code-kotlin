import year2020.Day1
import java.io.File
import java.lang.RuntimeException

fun main() {
    val expenseReport = Day1(InputReader(year = 2020, day = 1).asIntSet())
    println(expenseReport.solutionPartTwo())
}

class InputReader(year: Int, day: Int) {
    private val source = "src/main/resources/input/year$year/day${day}.txt"

    fun asIntSet() = File(source).useLines { lines ->
        lines.map { it.toInt() }.toSet()
    }
}

interface Puzzle {
    fun solutionPartOne(): Int
    fun solutionPartTwo(): Int
}

class NoSolutionFoundException : RuntimeException()