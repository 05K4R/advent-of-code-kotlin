package year2020

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day1Test : StringSpec({
    "Part one, 1 and 2019 should be 2019" {
        Day1(setOf(1, 2019)).solutionPartOne() shouldBe 2019
    }

    "Part one, given test" {
        val givenExpenses = setOf(1721, 979, 366, 299, 675, 1457)
        Day1(givenExpenses).solutionPartOne() shouldBe 514579
    }

    "Part two, given test" {
        val givenExpenses = setOf(1721, 979, 366, 299, 675, 1457)
        Day1(givenExpenses).solutionPartTwo() shouldBe 241861950
    }
})