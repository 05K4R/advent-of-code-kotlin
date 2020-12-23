package year2020

import InputReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day23Test : StringSpec({

    "Cup circle, circles are equal regardless of starting number" {
        CupCircle(listOf(1, 2, 3)) shouldBe CupCircle(listOf(2, 3, 1))
    }

    "Cup circle, pick up after first label" {
        val labels = listOf(1, 2, 3, 4)
        val pickUpLabel = 1
        CupCircle(labels).pickUpAfter(pickUpLabel).second shouldBe CupCircle(labels.dropLast(3))
    }

    "Cup circle, pick up after first label, only three are picked up" {
        val labels = listOf(1, 2, 3, 4, 5)
        val pickUpLabel = 1
        CupCircle(labels).pickUpAfter(pickUpLabel).second shouldBe CupCircle(listOf(1, 5))
    }

    "Cup circle, pick up after second to last label, picks up first to labels" {
        val labels = listOf(1, 2, 3, 4, 5)
        val pickUpLabel = 4
        CupCircle(labels).pickUpAfter(pickUpLabel).second shouldBe CupCircle(listOf(3, 4))
    }

    "Cup circle, place after label, cups are added" {
        val labels = listOf(1, 2, 3)
        val placeLabel = 2
        val placedCups = listOf(4, 5, 6)
        CupCircle(labels).placeAfter(placeLabel, placedCups) shouldBe CupCircle(listOf(1, 2, 4, 5, 6, 3))
    }

    "Cup circle, find destination cup immediately below" {
        val labels = listOf(1, 2, 3)
        CupCircle(labels).destinationCup(2) shouldBe 1
    }

    "Cup circle, find destination cup lower" {
        val labels = listOf(1, 3, 5)
        CupCircle(labels).destinationCup(3) shouldBe 1
    }

    "Cup circle, find destination cup wraparound" {
        val labels = listOf(6, 7, 8)
        CupCircle(labels).destinationCup(3) shouldBe 8
    }

    "Cup circle, get label order except 1" {
        val labels = listOf(1, 2, 3, 4)
        CupCircle(labels).cupOrderExceptOne() shouldBe listOf(2, 3, 4)
    }

    "Cup circle, get label order except 1, 1 in the middle" {
        val labels = listOf(3, 4, 1, 2, 5)
        CupCircle(labels).cupOrderExceptOne() shouldBe listOf(2, 5, 3, 4)
    }

    "Cup circle, new current cup" {
        val labels = listOf(1, 4, 6, 7)
        CupCircle(labels).newCurrentCup(4) shouldBe 6
    }

    "Cup circle, picked up is in sequential order" {
        val labels = listOf(3, 8, 9, 1, 2, 5)
        CupCircle(labels).pickUpAfter(3).first shouldBe listOf(8, 9, 1)
    }

    "Part one, given test" {
        val startingNumbers = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7)
        Day23(startingNumbers, moves = 10).answerPart1() shouldBe 92658374
    }

    "Part one, my input" {
        Day23(InputReader(2020, 23)).answerPart1() shouldBe 53248976
    }
})
