package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

class Day9(private val preambleLength: Int, private val encryptedData: List<Long>) : Puzzle<Long> {
    constructor(inputReader: InputReader) : this(25, inputReader.asLongList())

    override fun answerPart1(): Long {
        return encryptedData.windowed(preambleLength + 1)
            .filter { it.take(preambleLength).cannotSumTo(it.last()) }
            .map { it.last() }
            .firstOrNull()
            ?: throw NoSolutionFoundException()
    }

    override fun answerPart2(): Long {
        val targetNumber = encryptedData.windowed(preambleLength + 1)
            .filter { it.take(preambleLength).cannotSumTo(it.last()) }
            .map { it.last() }
            .firstOrNull()
            ?: throw NoSolutionFoundException()

        return encryptedData.takeWhile { it != targetNumber }
            .findSummableTo(targetNumber)
            .minAndMaxSum()
    }
}

private fun List<Long>.cannotSumTo(sum: Long): Boolean {
    return !this.flatMap { firstInt ->
        this.minus(firstInt)
            .map { secondInt -> firstInt + secondInt }
    }
        .distinct()
        .any { it == sum }
}

private fun List<Long>.findSummableTo(sum: Long): List<Long> {
    return (0..this.size)
        .map { this.drop(it) }
        .map { it.untilSumAtLeast(sum) }
        .firstOrNull { it.sum() == sum }
        ?: throw NoSolutionFoundException()
}

private fun List<Long>.untilSumAtLeast(sum: Long): List<Long> {
    return this.take(
        (0..this.size).firstOrNull { this.take(it).sum() >= sum } ?: 0
    )
}

private fun List<Long>.minAndMaxSum() = (this.minOrNull()!! + this.maxOrNull()!!)