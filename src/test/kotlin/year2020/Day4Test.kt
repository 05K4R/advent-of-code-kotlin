package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day4Test : StringSpec({
    "Part one, all fields present, valid passport" {
        val passport = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
        )
        Day4(passport).answerPart1() shouldBe 1
    }

    "Part one, missing hgt, invalid passport" {
        val passport = listOf(
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
        )
        Day4(passport).answerPart1() shouldBe 0
    }

    "Part one, missing cid, valid passport" {
        val passport = listOf(
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
        )
        Day4(passport).answerPart1() shouldBe 1
    }

    "Part one, missing cid and byr, invalid passport" {
        val passport = listOf(
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in",
        )
        Day4(passport).answerPart1() shouldBe 0
    }

    "Part one, two valid passports" {
        val passports = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
        )
        Day4(passports).answerPart1() shouldBe 2
    }

    "Part one, two invalid passports" {
        val passports = listOf(
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in",
        )
        Day4(passports).answerPart1() shouldBe 0
    }

    "Part one, one valid one invalid passport" {
        val passports = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in",
        )
        Day4(passports).answerPart1() shouldBe 1
    }

    "Part one, my input" {
        Day4(InputReader(2020, 4)).answerPart1() shouldBe 222
    }
})