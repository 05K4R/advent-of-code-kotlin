package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

class Day11(seatGrid: List<String>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val ferrySeatGrid = parseFerrySeats(seatGrid)

    override fun answerPart1(): Int {
        return findStable(ferrySeatGrid).seats
            .count { (_, seat) -> seat == SeatStatus.Occupied }
    }

    override fun answerPart2(): Int {
        return findStable2(ferrySeatGrid).seats
            .count { (_, seat) -> seat == SeatStatus.Occupied }
    }
}

private tailrec fun findStable(current: FerrySeatGrid): FerrySeatGrid {
    val next = current.nextState()
    return if (next == current) current
    else findStable(next)
}

private tailrec fun findStable2(current: FerrySeatGrid): FerrySeatGrid {
    val next = current.nextState2()
    return if (next == current) current
    else findStable2(next)
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
    fun nextState(): FerrySeatGrid {
        return seats.map {
            it.key to it.value.nextState(adjacent(it.key))
        }
            .toMap()
            .let { FerrySeatGrid(it) }
    }

    fun nextState2(): FerrySeatGrid {
        return seats.map {
            it.key to it.value.nextState(adjacent2(it.key), tolerance = 5)
        }
            .toMap()
            .let { FerrySeatGrid(it) }
    }

    private fun adjacent(base: Coordinate): List<SeatStatus> {
        return (-1..1).flatMap { col -> (-1..1).map { row -> Coordinate(col, row) } }
            .minus(Coordinate(0, 0))
            .mapNotNull { (col, row) -> seats[Coordinate(base.first + col, base.second + row)] }
    }

    private fun adjacent2(base: Coordinate): List<SeatStatus> {
        return (-1..1).flatMap { col -> (-1..1).map { row -> Coordinate(col, row) } }
            .minus(Coordinate(0, 0))
            .mapNotNull { findAdjacentLong(base, it) }
    }

    private fun findAdjacentLong(base: Coordinate, direction: Coordinate): SeatStatus? {
        val seat = seats[base + direction]
        return if (seat == SeatStatus.Floor) findAdjacentLong(base + direction, direction)
        else seat
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