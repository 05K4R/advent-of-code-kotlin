package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

class Day11(val seatGrid: List<String>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val ferrySeatGrid = parseFerrySeats(seatGrid)

    override fun answerPart1(): Int {
        return findStable(ferrySeatGrid).seats
            .count { (_, seat) -> seat == SeatStatus.Occupied }
    }

    override fun answerPart2(): Int {
        TODO("Not yet implemented")
    }
}

private tailrec fun findStable(current: FerrySeatGrid): FerrySeatGrid {
    val next = current.nextState()
    return if (next == current) current
    else findStable(next)
}

private fun parseFerrySeats(seatGrid: List<String>): FerrySeatGrid {
    return seatGrid
        .mapIndexed { rowIndex, row ->
            rowIndex to row.map { parseSeat(it) }
        }
        .flatMap { (rowIndex, row) ->
            row.mapIndexed { columnIndex, seat -> Pair(columnIndex, rowIndex) to seat }
        }
        .toMap()
        .let { FerrySeatGrid(it) }
}

private fun parseSeat(seatChar: Char): SeatStatus {
    return when(seatChar) {
            '.' -> SeatStatus.Floor
            'L' -> SeatStatus.Empty
            '#' -> SeatStatus.Occupied
            else -> throw NoSolutionFoundException()
    }
}

private data class FerrySeatGrid(val seats: Map<Pair<Int, Int>, SeatStatus> = emptyMap()) {
    fun nextState(): FerrySeatGrid {
        return seats.map {
            it.key to it.value.nextState(adjacent(it.key))
        }
            .toMap()
            .let { FerrySeatGrid(it) }
    }

    private fun adjacent(base: Pair<Int, Int>): List<SeatStatus> {
        val first = seats[Pair(base.first, base.second - 1)]
        val second = seats[Pair(base.first, base.second + 1)]
        val third = seats[Pair(base.first - 1, base.second)]
        val fourth = seats[Pair(base.first + 1, base.second)]
        val fifth = seats[Pair(base.first + 1, base.second + 1)]
        val sixth = seats[Pair(base.first + 1, base.second - 1)]
        val seventh = seats[Pair(base.first - 1, base.second + 1)]
        val eight = seats[Pair(base.first - 1, base.second - 1)]

        return listOfNotNull(first, second, third, fourth, fifth, sixth, seventh, eight)
    }
}
private enum class SeatStatus {
    Floor, Empty, Occupied;

    fun nextState(adjacent: List<SeatStatus>): SeatStatus {
        return when (this) {
            Floor -> Floor
            Empty -> if (adjacent.none { it == Occupied }) Occupied else Empty
            Occupied -> if (adjacent.count { it == Occupied } >= 4) Empty else Occupied
        }
    }
}