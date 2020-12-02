package year2020

import InputReader
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

    "Part one, my input" {
        Day1(InputReader(2020, 1)).solutionPartOne() shouldBe 646779
    }

    "Part two, given test" {
        val givenExpenses = setOf(1721, 979, 366, 299, 675, 1457)
        Day1(givenExpenses).solutionPartTwo() shouldBe 241861950
    }

    "Part two, my input" {
        Day1(InputReader(2020, 1)).solutionPartTwo() shouldBe 246191688
    }
})