package year2020

import InputReader
import Puzzle

private typealias Argument = Int
private typealias Accumulator = Int
private typealias ProgramPointer = Int
private typealias Visited = Boolean
private data class Instruction(val operation: Operation, val argument: Argument, val visited: Visited)
private data class ProgramState(val accumulator: Accumulator, val pointer: ProgramPointer)
private enum class TerminateReason { Loop, EndOfProgram }

private enum class Operation {
    ACC { override fun execute(state: ProgramState, argument: Argument) =
            state.copy(accumulator = state.accumulator + argument, pointer = state.pointer + 1)
    },
    JMP { override fun execute(state: ProgramState, argument: Argument) =
            state.copy(pointer = state.pointer + argument)
    },
    NOP { override fun execute(state: ProgramState, argument: Argument) =
            state.copy(pointer = state.pointer + 1)
    };

    abstract fun execute(state: ProgramState, argument: Argument): ProgramState
}

private data class Program(val instructions: List<Instruction>, val state: ProgramState) {
    fun execute(): Pair<Accumulator, TerminateReason> {
        if (instructions.size <= state.pointer) return state.accumulator to TerminateReason.EndOfProgram
        if (instructions[state.pointer].visited) return state.accumulator to TerminateReason.Loop

        val nextInstruction = instructions[state.pointer]
        val nextState = nextInstruction.operation.execute(state, nextInstruction.argument)

        return this.copy(
            instructions = instructions.take(state.pointer)
                    + nextInstruction.copy(visited = true)
                    + instructions.drop(state.pointer + 1),
            state = nextState
        ).execute()
    }

    fun switchJmpAndNop(index: Int): Program {
        val newInstruction = when(instructions[index].operation) {
            Operation.JMP -> instructions[index].copy(operation = Operation.NOP)
            Operation.NOP -> instructions[index].copy(operation = Operation.JMP)
            else -> instructions[index]
        }
        return this.copy(
            instructions = instructions.take(index)
                    + newInstruction
                    + instructions.drop(index + 1)
        )
    }
}

class Day8(programString: List<String>) : Puzzle {
    constructor(inputReader: InputReader) : this(inputReader.asStringList())

    private val program = parseProgram(programString)

    override fun answerPart1(): Int {
        return program.execute().first
    }

    override fun answerPart2(): Int {
        return (0..program.instructions.size)
            .asSequence()
            .map { program.switchJmpAndNop(it) }
            .first { it.execute().second == TerminateReason.EndOfProgram }
            .execute().first
    }
}

private fun parseProgram(programString: List<String>): Program {
    return programString
        .map { Instruction(
            Operation.valueOf(it.substringBefore(" ").toUpperCase()),
            it.substringAfter(" ").toInt(),
            visited = false
            )
        }
        .toList()
        .let { Program(it, ProgramState(0, 0)) }
}

