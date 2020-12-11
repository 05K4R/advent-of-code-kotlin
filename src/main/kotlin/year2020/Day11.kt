package year2020

import InputReader
import NoSolutionFoundException
import Puzzle

typealias Coordinate = Pair<Int, Int>
operator fun Coordinate.plus(that: Coordinate) = Coordinate(this.first + that.first, this.second + that.second)
typealias SeatMap = Map<Coordinate, Seat>

class Day11(seatGrid: List<String>) : Puzzle<Int> {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val waitingArea = parseWaitingArea(seatGrid)

    override fun answerPart1(): Int {
        return findStable(waitingArea, ignoreFloor = false, tolerance = 4).seats
            .count { (_, seat) -> seat == Seat.Occupied }
    }

    override fun answerPart2(): Int {
        return findStable(waitingArea, ignoreFloor = true, tolerance = 5).seats
            .count { (_, seat) -> seat == Seat.Occupied }
    }
}

private tailrec fun findStable(current: WaitingArea, ignoreFloor: Boolean, tolerance: Int): WaitingArea {
    val next = current.nextState(ignoreFloor, tolerance)
    return if (next == current) current
    else findStable(next, ignoreFloor, tolerance)
}

private data class WaitingArea(val seats: SeatMap) {
    private val directions = (-1..1).flatMap { col -> (-1..1).map { row -> Coordinate(col, row) } }.minus(Coordinate(0, 0))

    fun nextState(ignoreFloor: Boolean, tolerance: Int): WaitingArea {
        return seats.map {
            it.key to it.value.nextState(adjacentSeats(start = it.key, ignoreFloor), tolerance)
        }.toMap().let { WaitingArea(it) }
    }

    fun adjacentSeats(start: Coordinate, ignoreFloor: Boolean): List<Seat> {
        return directions.mapNotNull { searchAdjacent(start, direction = it, ignoreFloor) }
    }

    private tailrec fun searchAdjacent(base: Coordinate, direction: Coordinate, ignoreFloor: Boolean = false): Seat? {
        val seat = seats[base + direction]
        return if (ignoreFloor and (seat == Seat.Floor)) searchAdjacent(base + direction, direction, ignoreFloor)
        else seat
    }
}

enum class Seat { Floor, Empty, Occupied;
    fun nextState(adjacent: List<Seat>, tolerance: Int = 4): Seat {
        return when (this) {
            Floor -> Floor
            Empty -> if (adjacent.none { it == Occupied }) Occupied else Empty
            Occupied -> if (adjacent.count { it == Occupied } >= tolerance) Empty else Occupied
        }
    }
}

private fun parseWaitingArea(seatGrid: List<String>): WaitingArea {
    return seatGrid
        .mapIndexed { rowIndex, row ->
            rowIndex to row.map { parseSeat(it) }
        }
        .flatMap { (rowIndex, row) ->
            row.mapIndexed { columnIndex, seat -> Coordinate(columnIndex, rowIndex) to seat }
        }
        .toMap()
        .let { WaitingArea(it) }
}

private fun parseSeat(seatChar: Char): Seat {
    return when(seatChar) {
        '.' -> Seat.Floor
        'L' -> Seat.Empty
        '#' -> Seat.Occupied
        else -> throw NoSolutionFoundException()
    }
}