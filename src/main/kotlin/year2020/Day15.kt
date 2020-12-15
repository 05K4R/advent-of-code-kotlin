package year2020

import InputReader
import Puzzle

class Day15(private val startingNumbers: List<Int>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asCommaSeparatedIntList())

    override fun answerPart1(): Int {
        return allSpokenNumbers(2020, startingNumbers).last()
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }

    private tailrec fun allSpokenNumbers(stopLength: Int, spokenNumbers: List<Int>): List<Int> {
        if (spokenNumbers.size >= stopLength) return spokenNumbers

        val lastSpokenNumber = spokenNumbers.last()
        val previouslySpokenNumbers = spokenNumbers.dropLast(1)
        return if (previouslySpokenNumbers.contains(lastSpokenNumber)) {
            val turnLastSpoken = previouslySpokenNumbers.lastIndexOf(lastSpokenNumber) + 1
            allSpokenNumbers(stopLength, spokenNumbers + (spokenNumbers.size - turnLastSpoken))
        } else {
            allSpokenNumbers(stopLength, spokenNumbers + 0)
        }
    }
}