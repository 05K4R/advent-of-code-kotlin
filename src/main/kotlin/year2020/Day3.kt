package year2020

import InputReader
import Puzzle

class Day3(private val map: List<String>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    override fun answerPart1(): Int {
        return encounteredTrees(3, 1)
    }

    override fun answerPart2(): Int {
        return (
                encounteredTrees(1, 1)
                        * encounteredTrees(3, 1)
                        * encounteredTrees(5, 1)
                        * encounteredTrees(7, 1)
                        * encounteredTrees(1, 2)
                )
    }

    private fun encounteredTrees(xStepSize: Int, yStepSize: Int): Int {
        var y = 0
        var x = 0
        var trees = 0
        while (y < map.size) {
            if (map[y][x] == '#') trees++
            x += xStepSize
            if (x >= map[y].length) x -= map[y].length
            y += yStepSize
        }
        return trees
    }
}