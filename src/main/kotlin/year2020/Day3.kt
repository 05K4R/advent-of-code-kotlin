package year2020

import InputReader
import Puzzle

class Day3(val map: List<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    override fun answerPart1(): Int {
        var x = 0
        var y = 0
        var trees = 0
        while (x < map.size) {
            if (map[x][y] == '#') trees++
            y += 3
            if (y >= map[x].length) y -= map[x].length
            x += 1
        }
        return trees
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }
}