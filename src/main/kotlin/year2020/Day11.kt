package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

class Day11(seatGrid: List<String>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val ferrySeatGrid = parseFerrySeats(seatGrid)

    override fun answerPart1(): Int {
        return findStable(ferrySeatGrid, extended = false, tolerance = 4).seats
            .count { (_, seat) -> seat == SeatStatus.Occupied }
    }

    override fun answerPart2(): Int {
        return findStable(ferrySeatGrid, extended = true, tolerance = 5).seats
            .count { (_, seat) -> seat == SeatStatus.Occupied }
    }
}

private tailrec fun findStable(current: FerrySeatGrid, extended: Boolean, tolerance: Int): FerrySeatGrid {
    val next = current.nextState(extended, tolerance)
    return if (next == current) current
    else findStable(next, extended, tolerance)
}

private fun parseFerrySeats(seatGrid: List<String>): FerrySeatGrid {
    return seatGrid
        .mapIndexed { rowIndex, row ->
            rowIndex to row.map { parseSeat(it) }
        }
        .flatMap { (rowIndex, row) ->
            row.mapIndexed { columnIndex, seat -> Coordinate(columnIndex, rowIndex) to seat }
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

private data class FerrySeatGrid(val seats: Map<Coordinate, SeatStatus> = emptyMap()) {
    fun nextState(extended: Boolean, tolerance: Int): FerrySeatGrid {
        return seats.map {
            it.key to it.value.nextState(adjacent(it.key, extended), tolerance)
        }
            .toMap()
            .let { FerrySeatGrid(it) }
    }

    private fun adjacent(base: Coordinate, extended: Boolean): List<SeatStatus> {
        return (-1..1).flatMap { col -> (-1..1).map { row -> Coordinate(col, row) } }
            .minus(Coordinate(0, 0))
            .mapNotNull { findAdjacentLong(base, it, extended) }
    }

    private tailrec fun findAdjacentLong(base: Coordinate, direction: Coordinate, extended: Boolean = false): SeatStatus? {
        return if (extended) {
            val seat = seats[base + direction]
            if (seat == SeatStatus.Floor) findAdjacentLong(base + direction, direction, extended)
            else seat
        } else {
            seats[base + direction]
        }
    }
}
private enum class SeatStatus {
    Floor, Empty, Occupied;

    fun nextState(adjacent: List<SeatStatus>, tolerance: Int = 4): SeatStatus {
        return when (this) {
            Floor -> Floor
            Empty -> if (adjacent.none { it == Occupied }) Occupied else Empty
            Occupied -> if (adjacent.count { it == Occupied } >= tolerance) Empty else Occupied
        }
    }
}

typealias Coordinate = Pair<Int, Int>
private operator fun Coordinate.plus(that: Coordinate) = Coordinate(this.first + that.first, this.second + that.second)