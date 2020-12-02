package year2020

import Puzzle

class Day2(input: Set<String>) : Puzzle {
    private val policiesAndPasswords = input.map { parse(it) }.toSet()

    override fun solutionPartOne(): Int {
        return policiesAndPasswords.filter { policyAndPassword ->
            val occurrences = policyAndPassword.password.count { it == policyAndPassword.letter }
            occurrences >= policyAndPassword.from && occurrences <= policyAndPassword.to
        }.size
    }

    override fun solutionPartTwo(): Int {
        TODO("Not yet implemented")
    }

    private fun parse(input: String): PolicyAndPassword {
        // 1-3 a: abcde
        val parts = input.split(" ")
        val length = parts[0].split("-").map { it.toInt() }
        val cleanedLetter = parts[1].toCharArray().first()
        return PolicyAndPassword(from = length[0], to = length[1], letter = cleanedLetter, password = parts[2])
    }
}

private data class PolicyAndPassword(val from: Int, val to: Int, val letter: Char, val password: String)