package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day15Test : StringSpec ({

    "Part one, first given test" {
        val startingNumbers = listOf(1, 3, 2)
        Day15(startingNumbers).answerPart1() shouldBe 1
    }

    "Part one, second given test" {
        val startingNumbers = listOf(2, 1, 3)
        Day15(startingNumbers).answerPart1() shouldBe 10
    }

    "Part one, third given test" {
        val startingNumbers = listOf(1, 2, 3)
        Day15(startingNumbers).answerPart1() shouldBe 27
    }

    "Part one, fourth given test" {
        val startingNumbers = listOf(2, 3, 1)
        Day15(startingNumbers).answerPart1() shouldBe 78
    }

    "Part one, fifth given test" {
        val startingNumbers = listOf(3, 2, 1)
        Day15(startingNumbers).answerPart1() shouldBe 438
    }

    "Part one, sixth given test" {
        val startingNumbers = listOf(3, 1, 2)
        Day15(startingNumbers).answerPart1() shouldBe 1836
    }

    "Part one, my input" {
        Day15(InputReader(2020, 15)).answerPart1() shouldBe 211
    }
})