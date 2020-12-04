package year2020

import InputReader
import Puzzle

class Day4(passportStrings: List<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val passports = parsePassports(passportStrings)

    override fun answerPart1(): Int {
        return passports.count { it.hasRequiredFields() }
    }

    override fun answerPart2(): Int {
        return passports.count { it.hasRequiredFields() && it.hasValidFieldValues() }
    }
}

private fun parsePassports(strings: List<String>): Set<Passport> {
    return strings.fold(listOf(""), ::mergeMultipleLines)
        .map(::createPassport)
        .toSet()
}

private fun mergeMultipleLines(previous: List<String>, newLine: String): List<String> {
    return if (newLine.isBlank()) previous + ""
    else previous.take(previous.size - 1) + (previous.last() + "$newLine ")
}

private fun createPassport(passportString: String): Passport {
    return passportString.split(" ")
        .filter { it.isNotBlank() }
        .map { it.split(":") }
        .map { Pair(it[0], it[1]) }
        .toMap()
        .let {
            Passport(
                birthYear = it["byr"].orEmpty(),
                issueYear = it["iyr"].orEmpty(),
                expirationYear = it["eyr"].orEmpty(),
                height = it["hgt"].orEmpty(),
                hairColor = it["hcl"].orEmpty(),
                eyeColor = it["ecl"].orEmpty(),
                passportId = it["pid"].orEmpty(),
                countryId = it["cid"].orEmpty(),
            )
        }
}

private data class Passport(
    val birthYear: String,
    val issueYear: String,
    val expirationYear: String,
    val height: String,
    val hairColor: String,
    val eyeColor: String,
    val passportId: String,
    val countryId: String,
) {
    fun hasRequiredFields() = birthYear.isNotBlank()
            && issueYear.isNotBlank()
            && expirationYear.isNotBlank()
            && height.isNotBlank()
            && hairColor.isNotBlank()
            && eyeColor.isNotBlank()
            && passportId.isNotBlank()

    fun hasValidFieldValues() = validBirthYear()
            && validIssueYear()
            && validExpirationYear()
            && validHeight()
            && validHairColor()
            && validEyeColor()
            && validPassportId()

    fun validBirthYear() = birthYear.toInt() in 1920..2002
    fun validIssueYear() = issueYear.toInt() in 2010..2020
    fun validExpirationYear() = expirationYear.toInt() in 2020..2030
    fun validHeight(): Boolean {
        return when {
            height.endsWith("cm") -> height.removeSuffix("cm").toInt() in 150..193
            height.endsWith("in") -> height.removeSuffix("in").toInt() in 59..76
            else -> false
        }
    }

    fun validHairColor() = Regex("#[a-f0-9]{6}").matches(hairColor)
    fun validEyeColor() = Regex("(brn|amb|blu|gry|grn|hzl|oth)").matches(eyeColor)
    fun validPassportId() = Regex("[0-9]{9}").matches(passportId)
}