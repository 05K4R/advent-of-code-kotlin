import year2020.day1part1.ExpenseReport
import java.io.File
import java.lang.RuntimeException

fun main() {
    val expenseReport = ExpenseReport(InputReader(year = 2020, day = 1, Part.One).asIntSet())
    println(expenseReport.solutionPartTwo())
}

class InputReader(year: Int, day: Int, part: Part) {
    private val source = "src/main/resources/input/year$year/day${day}part${part.toInt()}.txt"

    fun asIntSet() = File(source).useLines { lines ->
        lines.map { it.toInt() }.toSet()
    }
}

interface Puzzle {
    fun solutionPartOne(): Int
    fun solutionPartTwo(): Int
}

class NoSolutionFoundException : RuntimeException()

enum class Part {
    One, Two;
    fun toInt() = when(this) {
        One -> 1
        Two -> 2
    }
}