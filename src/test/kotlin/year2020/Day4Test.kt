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

    "Part two, invalid passport 1" {
        val passport = listOf(
            "eyr:1972 cid:100",
            "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
        )
        Day4(passport).answerPart2() shouldBe 0
    }

    "Part two, invalid passport 2" {
        val passport = listOf(
            "iyr:2019",
            "hcl:#602927 eyr:1967 hgt:170cm",
            "ecl:grn pid:012533040 byr:1946",
        )
        Day4(passport).answerPart2() shouldBe 0
    }

    "Part two, invalid passport 3" {
        val passport = listOf(
            "hcl:dab227 iyr:2012",
            "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
        )
        Day4(passport).answerPart2() shouldBe 0
    }

    "Part two, invalid passport 4" {
        val passport = listOf(
            "hgt:59cm ecl:zzz",
            "eyr:2038 hcl:74454a iyr:2023",
            "pid:3556412378 byr:2007",
        )
        Day4(passport).answerPart2() shouldBe 0
    }

    "Part two, valid passport 1" {
        val passport = listOf(
            "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980",
            "hcl:#623a2f",
        )
        Day4(passport).answerPart2() shouldBe 1
    }

    "Part two, valid passport 2" {
        val passport = listOf(
            "eyr:2029 ecl:blu cid:129 byr:1989",
            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
        )
        Day4(passport).answerPart2() shouldBe 1
    }

    "Part two, valid passport 3" {
        val passport = listOf(
            "hcl:#888785",
            "hgt:164cm byr:2001 iyr:2015 cid:88",
            "pid:545766238 ecl:hzl",
            "eyr:2022",
        )
        Day4(passport).answerPart2() shouldBe 1
    }

    "Part two, valid passport 4" {
        val passport = listOf(
            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719",
        )
        Day4(passport).answerPart2() shouldBe 1
    }

    "Part two, two valid and two invalid passports" {
        val passport = listOf(
            "eyr:1972 cid:100",
            "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
            "",
            "iyr:2019",
            "hcl:#602927 eyr:1967 hgt:170cm",
            "ecl:grn pid:012533040 byr:1946",
            "",
            "eyr:2029 ecl:blu cid:129 byr:1989",
            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
            "",
            "hcl:#888785",
            "hgt:164cm byr:2001 iyr:2015 cid:88",
            "pid:545766238 ecl:hzl",
            "eyr:2022",
        )
        Day4(passport).answerPart2() shouldBe 2
    }

    "Part two, my input" {
        Day4(InputReader(2020, 4)).answerPart2() shouldBe 140
    }
})