package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day3Test : StringSpec({
    "Part one, one tree" {
        val map = listOf(
            "....",
            "...#",
        )
        Day3(map).answerPart1() shouldBe 1
    }

    "Part one, no trees" {
        val map = listOf(
            ".###",
            "###.",
        )
        Day3(map).answerPart1() shouldBe 0
    }

    "Part one, repeating pattern two trees" {
        val map = listOf(
            "....",
            "...#",
            "..#.",
        )
        Day3(map).answerPart1() shouldBe 2
    }

    "Part one, repeating pattern no trees" {
        val map = listOf(
            ".###",
            "###.",
            "##.#",
        )
        Day3(map).answerPart1() shouldBe 0
    }

    "Part one, given test" {
        val map = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#",
        )
        Day3(map).answerPart1() shouldBe 7
    }

    "Part one, my input" {
        Day3(InputReader(2020, 3)).answerPart1() shouldBe 148
    }

    "Part two, given test" {
        val map = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#",
        )
        Day3(map).answerPart2() shouldBe 336
    }

    "Part two, my input" {
        Day3(InputReader(2020, 3)).answerPart2() shouldBe 727923200
    }
})