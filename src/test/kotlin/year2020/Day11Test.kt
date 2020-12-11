package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day11Test : StringSpec({
    "Part one, one empty becomes occupied" {
        val seatGrid = listOf(
            "L",
        )

        Day11(seatGrid).answerPart1() shouldBe 1
    }

    "Part one, one occupied is stable" {
        val seatGrid = listOf(
            "#",
        )

        Day11(seatGrid).answerPart1() shouldBe 1
    }

    "Part one, all but middle becomes occupied" {
        val seatGrid = listOf(
            ".L.",
            "LLL",
            ".L.",
        )

        Day11(seatGrid).answerPart1() shouldBe 4
    }

    "Part one, given test" {
        val seatGrid = listOf(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL",
        )

        Day11(seatGrid).answerPart1() shouldBe 37
    }

    "Part one, my input" {
        Day11(InputReader(2020, 11)).answerPart1() shouldBe 2424
    }

    "Part two, given test" {
        val seatGrid = listOf(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL",
        )

        Day11(seatGrid).answerPart2() shouldBe 26
    }

    "Part two, my input" {
        Day11(InputReader(2020, 11)).answerPart2() shouldBe 2208
    }
})