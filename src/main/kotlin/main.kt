import java.io.File

fun main() {
    val puzzle = loadPuzzle(2020, 3)
    println(puzzle.answerPart2())
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

fun loadPuzzle(year: Int, day: Int): Puzzle {
    return Class.forName("year$year.Day$day")
        ?.getDeclaredConstructor(InputReader::class.java)
        ?.newInstance(InputReader(year, day))
        as Puzzle
}