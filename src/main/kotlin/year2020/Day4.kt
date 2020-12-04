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

    private fun parsePassports(strings: List<String>): Set<Passport> {
        val passports = mutableSetOf<Passport>()
        val parsedFields = mutableMapOf<String, String>()

        for (string in strings) {
            if (string.isBlank()) {
                passports.add(createPassport(parsedFields))
                parsedFields.clear()
            } else {
                val fieldsAndValues = string.split(" ")
                for (fieldAndValue in fieldsAndValues) {
                    val (field, value) = fieldAndValue.split(":")
                    parsedFields[field] = value
                }
            }
        }

        passports.add(createPassport(parsedFields))
        return passports
    }

    private fun createPassport(
        parsedFields: Map<String, String>
    ): Passport {
        return Passport(
                birthYear = parsedFields["byr"],
                issueYear = parsedFields["iyr"],
                expirationYear = parsedFields["eyr"],
                height = parsedFields["hgt"],
                hairColor = parsedFields["hcl"],
                eyeColor = parsedFields["ecl"],
                passportId = parsedFields["pid"],
                countryId = parsedFields["cid"],
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
            height == null -> true
            height.endsWith("cm") -> height.substringBefore("cm").toInt() in 150..193
            height.endsWith("in") -> height.substringBefore("in").toInt() in 59..76
            else -> false
        }
    }

    private fun validHairColor(): Boolean {
        return when {
            hairColor == null -> true
            hairColor.startsWith("#") -> {
                val hex = hairColor.substringAfter("#")
                hex.length == 6 && hex.all { (it in 'a'..'f' || it in '0'..'9') }
            }
            else -> false
        }
    }

    private fun validEyeColor() = eyeColor == "amb" || eyeColor == "blu" || eyeColor == "brn"
                || eyeColor == "gry" || eyeColor == "grn" || eyeColor == "hzl" || eyeColor == "oth"

    private fun validPassportId() = passportId?.length == 9 && passportId.all { it.isDigit() }
}