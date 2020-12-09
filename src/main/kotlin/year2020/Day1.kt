package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

class Day1(private val expenses: Set<Int>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asIntSet())

    override fun answerPart1(): Int {
        for (firstExpense in expenses) {
            val secondExpense = expenses.firstOrNull { it + firstExpense == 2020 } ?: continue
            return firstExpense * secondExpense
        }

        throw NoSolutionFoundException()
    }

    override fun answerPart2(): Int {
        for (firstExpense in expenses) {
            for (secondExpense in expenses) {
                for (thirdExpense in expenses) {
                    if (firstExpense + secondExpense + thirdExpense == 2020)
                        return firstExpense * secondExpense * thirdExpense
                }
            }
        }

        throw NoSolutionFoundException()
    }
}