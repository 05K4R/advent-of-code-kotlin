package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day8Test : StringSpec({
    "Part one, terminate infinite loop at 0" {
        val program = listOf(
            "jmp +1",
            "jmp -1",
        )
        Day8(program).answerPart1() shouldBe 0
    }

    "Part one, increase acc" {
        val program = listOf(
            "jmp +1",
            "acc +1",
            "jmp -2",
        )
        Day8(program).answerPart1() shouldBe 1
    }

    "Part one, nop doesn't increase acc" {
        val program = listOf(
            "jmp +1",
            "nop +1",
            "jmp -2",
        )
        Day8(program).answerPart1() shouldBe 0
    }

    "Part one, given test" {
        val program = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6",
        )
        Day8(program).answerPart1() shouldBe 5
    }

    "Part one, my input" {
        Day8(InputReader(2020, 8)).answerPart1() shouldBe 1859
    }
})