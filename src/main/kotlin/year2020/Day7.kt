package year2020

import InputReader
import Puzzle

class Day7(stringRules: Set<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringSet())

    private val rules = parseRules(stringRules)

    override fun answerPart1(): Int {
        return rules.filter { (_, contained) -> canContainShinyYellow(contained) }.count()
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }

    private fun canContainShinyYellow(contained: Set<String>): Boolean {
        if (contained.isEmpty()) return false
        if (contained.contains("shiny gold bag")) return true
        return contained.any { canContainShinyYellow(rules[it] ?: emptySet()) }
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
    return matches.first() to matches.drop(1).toSet()
}