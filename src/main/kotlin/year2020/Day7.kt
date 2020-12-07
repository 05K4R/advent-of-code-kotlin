package year2020

import InputReader
import Puzzle

class Day7(private val stringRules: Set<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringSet())

    private val rules = parseRules(stringRules)

    override fun answerPart1(): Int {
        return rules.filter { (a, b) -> canContainShinyYellow(a, b) }.count()
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }

    private fun canContainShinyYellow(a: String, b: Set<String>): Boolean {
        if (b.isEmpty()) return false
        if (b.contains("shiny gold bag")) return true
        return b.any { canContainShinyYellow(it, rules[it] ?: emptySet()) }
    }
}

private fun parseRules(stringRules: Set<String>): Map<String, Set<String>> {
    return stringRules
        .map { parseRule(it) }
        .toMap()
}

private fun parseRule(stringRule: String): Pair<String, Set<String>> {
    val matches = Regex("(\\w+ \\w+) bag")
        .findAll(stringRule)
        .map { it.value }
        .toList()
    return Pair(matches[0], matches.drop(1).toSet())
}