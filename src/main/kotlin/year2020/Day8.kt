package year2020

import InputReader
import Puzzle

private typealias Argument = Int
private typealias Instruction = Pair<Operation, Argument>

private typealias Accumulator = Int
private typealias ProgramPointer = Int
private typealias Visited = Boolean
private typealias ProgramState = Pair<Accumulator, ProgramPointer>

private enum class TerminateReason { Loop, EndOfProgram }

private enum class Operation {
    ACC {
        override fun execute(state: ProgramState, argument: Argument) =
            ProgramState(state.first + argument, state.second + 1)
    },
    JMP {
        override fun execute(state: ProgramState, argument: Argument) =
            ProgramState(state.first, state.second + argument)
    },
    NOP {
        override fun execute(state: ProgramState, argument: Argument) =
            ProgramState(state.first, state.second + 1)
    };

    abstract fun execute(state: ProgramState, argument: Argument): ProgramState
}
private data class Program(val instructions: List<Pair<Instruction, Visited>>, val state: ProgramState) {
    fun execute(): Pair<Accumulator, TerminateReason> {
        if (instructions.size <= state.second) return state.first to TerminateReason.EndOfProgram
        if (instructions[state.second].second) return state.first to TerminateReason.Loop

        val nextInstruction = instructions[state.second].first
        val nextState = nextInstruction.first.execute(state, nextInstruction.second)

        return this.copy(
            instructions = instructions.take(state.second)
                    + Pair(nextInstruction, true)
                    + instructions.drop(state.second + 1),
            state = nextState
        ).execute()
    }

    fun switchJmpAndNop(index: Int): Program {
        val newInstruction = when(instructions[index].first.first) {
            Operation.JMP -> Instruction(Operation.NOP, instructions[index].first.second)
            Operation.NOP -> Instruction(Operation.JMP, instructions[index].first.second)
            else -> Instruction(instructions[index].first.first, instructions[index].first.second)
        }
        return this.copy(
            instructions = instructions.take(index)
                    + Pair(newInstruction, instructions[index].second)
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
            it.substringAfter(" ").toInt())
        }.map { it to false }
        .toList()
        .let { Program(it, ProgramState(0, 0)) }
}

