import java.io.File

fun main() {
    val year = 2020
    val day = 8
    val part = 2
    println("Day $day part $part = ${calculateAnswer(year, day, part)}")
}

class InputReader(year: Int, day: Int) {
    private val source = "src/main/resources/input/year$year/day${day}.txt"
    private fun <T> read(block: (Sequence<String>) -> T) = File(source).useLines { block.invoke(it) }

    fun asIntSet() = read { lines -> lines.map { it.toInt() }.toSet() }
    fun asStringSet() = read { it.toSet() }
    fun asStringList() = read { it.toList() }
}

class InvalidPartException(part: Int) : RuntimeException("Invalid part: $part")
class NoSolutionFoundException : RuntimeException()
interface Puzzle {
    fun answerPart1(): Int
    fun answerPart2(): Int
}

private fun calculateAnswer(year: Int, day: Int, part: Int): Int {
    val puzzle = Class.forName("year$year.Day$day")
        ?.getDeclaredConstructor(InputReader::class.java)
        ?.newInstance(InputReader(year, day))
            as Puzzle
    return when (part) {
        1 -> puzzle.answerPart1()
        2 -> puzzle.answerPart2()
        else -> throw InvalidPartException(part)
    }
}