package year2020

import InputReader
import NoSolutionFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day9Test : StringSpec({
    "Part one, all numbers match" {
        val preambleLength = 2
        val data = listOf<Long>(
            1,
            2,
            3,
        )
        shouldThrow<NoSolutionFoundException> {
            Day9(preambleLength, data).answerPart1()
        }
    }

    "Part one, first number doesn't match" {
        val preambleLength = 2
        val data = listOf<Long>(
            1,
            2,
            4,
        )
        Day9(preambleLength, data).answerPart1() shouldBe 4
    }

    "Part one, first preamble isn't included in second number" {
        val preambleLength = 2
        val data = listOf<Long>(
            1,
            2,
            3,
            4,
        )
        Day9(preambleLength, data).answerPart1() shouldBe 4
    }

    "Part one, extra numbers below not matching" {
        val preambleLength = 2
        val data = listOf<Long>(
            1,
            2,
            3,
            4,
            5,
        )
        Day9(preambleLength, data).answerPart1() shouldBe 4
    }

    "Part one, given test" {
        val preambleLength = 5
        val data = listOf<Long>(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576,
        )
        Day9(preambleLength, data).answerPart1() shouldBe 127
    }

    "Part one, my input" {
        Day9(InputReader(2020, 9)).answerPart1() shouldBe 26796446
    }

    "Part two, given test" {
        val preambleLength = 5
        val data = listOf<Long>(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576,
        )
        Day9(preambleLength, data).answerPart2() shouldBe 62
    }

    "Part two, my input" {
        Day9(InputReader(2020, 9)).answerPart2() shouldBe 3353494
    }
})
