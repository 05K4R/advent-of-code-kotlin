package year2020

import InputReader
import Puzzle
import kotlin.math.max

class Day23(private val cupLabels: List<Int>, private val moves: Int = 100) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asSingleDigitIntList())

    override fun answerPart1(): Int {
        var movesDone = 0
        var currentCup = cupLabels.first()
        var placeCup: Int
        var circle = CupCircle(cupLabels)
        while(movesDone < moves) {
            var pickedUp: List<Int>
            val cool = circle.pickUpAfter(currentCup)
            pickedUp = cool.first
            circle = cool.second
            placeCup = circle.destinationCup(currentCup)
            circle = circle.placeAfter(placeCup, pickedUp)
            currentCup = circle.newCurrentCup(currentCup)
            movesDone++
        }
        var s = ""
        circle.cupOrderExceptOne().forEach { s = s.plus(it) }
        return s.toInt()
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }
}

class CupCircle(initialLabels: List<Int>) {
    private val labels = startingWithOne(initialLabels)

    fun pickUpAfter(label: Int): Pair<List<Int>, CupCircle> {
        val labelIndex = labels.indexOf(label)
        val labelsWithoutWraparound = labels.take(labelIndex + 1) + labels.drop(labelIndex + 4)
        val newLabels = labelsWithoutWraparound.drop(max(0, labelIndex + 4 - labels.size))
        val pickedLabels = (labels + labels).dropWhile { it != label }.drop(1).take(3)
        return pickedLabels to CupCircle(newLabels)
    }

    fun placeAfter(label: Int, cupLabels: List<Int>): CupCircle {
        val newLabels = labels.takeWhile { it != label } + label + cupLabels + labels.dropWhile { it != label }.drop(1)
        return CupCircle(newLabels)
    }

    fun destinationCup(label: Int): Int {
        var targetLabel = label - 1
        while(!labels.contains(targetLabel)) {
            targetLabel--
            if (targetLabel < 0) targetLabel = 9
        }
        return targetLabel
    }

    fun cupOrderExceptOne(): List<Int> = labels.drop(1)

    fun newCurrentCup(last: Int): Int {
        val targetIndex = labels.indexOf(last) + 1
        return if (targetIndex >= labels.size) labels.first()
        else labels[targetIndex]
    }

    private fun startingWithOne(labels: List<Int>) = labels.dropWhile { it != 1 } + labels.takeWhile { it != 1 }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return labels == (other as CupCircle).labels
    }

    override fun hashCode(): Int {
        return labels.hashCode()
    }

    override fun toString(): String {
        return "CupCircle(labels=$labels)"
    }
}