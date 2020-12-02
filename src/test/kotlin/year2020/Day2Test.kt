package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day2Test : StringSpec({
    "Part one, valid password" {
        val givenPolicyAndPassword = "1-3 a: abcde"
        Day2(setOf(givenPolicyAndPassword)).solutionPartOne() shouldBe 1
    }

    "Part one, invalid password" {
        val givenPolicyAndPassword = "1-3 b: cdefg"
        Day2(setOf(givenPolicyAndPassword)).solutionPartOne() shouldBe 0
    }

    "Part one, given test" {
        val givenPolicyAndPasswords = setOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )
        Day2(givenPolicyAndPasswords).solutionPartOne() shouldBe 2
    }

    "Part one, my input" {
        Day2(InputReader(2020, 2)).solutionPartOne() shouldBe 469
    }

    "Part two, valid password" {
        val givenPolicyAndPassword = "1-3 a: abcde"
        Day2(setOf(givenPolicyAndPassword)).solutionPartTwo() shouldBe 1
    }

    "Part two, invalid password 1" {
        val givenPolicyAndPassword = "1-3 b: cdefg"
        Day2(setOf(givenPolicyAndPassword)).solutionPartTwo() shouldBe 0
    }

    "Part two, invalid password 2" {
        val givenPolicyAndPassword = "2-9 c: ccccccccc"
        Day2(setOf(givenPolicyAndPassword)).solutionPartTwo() shouldBe 0
    }

    "Part two, given test" {
        val givenPolicyAndPasswords = setOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )
        Day2(givenPolicyAndPasswords).solutionPartTwo() shouldBe 1
    }

    "Part two, my input" {
        Day2(InputReader(2020, 2)).solutionPartTwo() shouldBe 267
    }
})
