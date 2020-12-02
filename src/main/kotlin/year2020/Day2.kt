package year2020

import InputReader
import Puzzle

class Day2(input: Set<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringSet())

    private val policiesAndPasswords = input.map { parse(it) }.toSet()

    override fun solutionPartOne(): Int {
        return policiesAndPasswords.filter { policyAndPassword ->
            val occurrences = policyAndPassword.password.count { it == policyAndPassword.letter }
            occurrences >= policyAndPassword.firstNumber && occurrences <= policyAndPassword.secondNumber
        }.size
    }

    override fun solutionPartTwo(): Int {
        return policiesAndPasswords.filter {
            (it.password[it.firstNumber - 1] == it.letter) xor (it.password[it.secondNumber - 1] == it.letter)
        }.size
    }

    private fun parse(input: String): PolicyAndPassword {
        // 1-3 a: abcde
        val parts = input.split(" ")
        val numbers = parts[0].split("-").map { it.toInt() }
        val cleanedLetter = parts[1].toCharArray().first()
        return PolicyAndPassword(firstNumber = numbers[0], secondNumber = numbers[1], letter = cleanedLetter, password = parts[2])
    }
}

private data class PolicyAndPassword(val firstNumber: Int, val secondNumber: Int, val letter: Char, val password: String)