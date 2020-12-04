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
                birthYear = it["byr"],
                issueYear = it["iyr"],
                expirationYear = it["eyr"],
                height = it["hgt"],
                hairColor = it["hcl"],
                eyeColor = it["ecl"],
                passportId = it["pid"],
                countryId = it["cid"],
            )
        }
}

private data class Passport(
    val birthYear: String?,
    val issueYear: String?,
    val expirationYear: String?,
    val height: String?,
    val hairColor: String?,
    val eyeColor: String?,
    val passportId: String?,
    val countryId: String?,
) {
    fun hasRequiredFields(): Boolean {
        return birthYear != null
                && issueYear != null
                && expirationYear != null
                && height != null
                && hairColor != null
                && eyeColor != null
                && passportId != null
    }

    fun hasValidFieldValues(): Boolean {
        return validBirthYear()
                && validIssueYear()
                && validExpirationYear()
                && validHeight()
                && validHairColor()
                && validEyeColor()
                && validPassportId()
    }

    private fun validBirthYear() = birthYear?.toInt() in 1920..2002
    private fun validIssueYear() = issueYear?.toInt() in 2010..2020
    private fun validExpirationYear() = expirationYear?.toInt() in 2020..2030
    private fun validHeight(): Boolean {
        return when {
            height == null -> false
            height.endsWith("cm") -> height.removeSuffix("cm").toInt() in 150..193
            height.endsWith("in") -> height.removeSuffix("in").toInt() in 59..76
            else -> false
        }
    }

    private fun validHairColor(): Boolean {
        return when {
            hairColor == null -> false
            hairColor.startsWith("#") -> {
                val hex = hairColor.removePrefix("#")
                hex.length == 6 && hex.all { (it in 'a'..'f' || it in '0'..'9') }
            }
            else -> false
        }
    }

    private fun validEyeColor() = eyeColor == "amb" || eyeColor == "blu" || eyeColor == "brn"
                || eyeColor == "gry" || eyeColor == "grn" || eyeColor == "hzl" || eyeColor == "oth"

    private fun validPassportId() = passportId?.length == 9 && passportId.all { it.isDigit() }
}