package year2020

import InputReader
import Puzzle

class Day4(passportStrings: List<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val passports = parsePassports(passportStrings)

    override fun answerPart1(): Int {
        return passports.count { it.isValid() }
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
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
    fun isValid(): Boolean {
        return birthYear != null
                && issueYear != null
                && expirationYear != null
                && height != null
                && hairColor != null
                && eyeColor != null
                && passportId != null
    }
}