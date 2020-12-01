package year2020.day1part1

import NoSolutionFoundException
import Puzzle

class ExpenseReport(private val expenses: Set<Int>) : Puzzle {
    override fun solutionPartOne(): Int {
        for (firstExpense in expenses) {
            val secondExpense = expenses.firstOrNull { it + firstExpense == 2020 } ?: continue
            return firstExpense * secondExpense
        }

        throw NoSolutionFoundException()
    }

    override fun solutionPartTwo(): Int {
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