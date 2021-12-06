fun main() {
    data class Board(val matrix: Matrix = Array(5) { IntArray(5) }, var sum: Int = 0) {
        val rowsCount = IntArray(5)
        val columnsCount = IntArray(5)

        var isWon = false

        fun mark(number: Int): Pair<Int, Int> {
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    val el = matrix[i][j]
                    if (number == el) {
                        sum -= el
                        rowsCount[i]++
                        columnsCount[j]++
                        return i to j
                    }
                }
            }
            return -1 to -1
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Board

            if (!matrix.contentDeepEquals(other.matrix)) return false
            if (sum != other.sum) return false

            return true
        }

        override fun hashCode(): Int {
            var result = matrix.contentDeepHashCode()
            result = 31 * result + sum
            return result
        }
    }

    fun parseInput(input: List<String>): Pair<List<Int>, List<Board>> {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = mutableListOf<Board>()
        var i = 0
        for (line in input.drop(1)) {
            if (line.trim().isEmpty()) {
                boards.add(Board())
                continue
            }
            val board = boards.last()
            val row = line.trim().split("\\s+".toRegex()).map { it.toInt() }
            for (j in row.indices) {
                val el = row[j]
                board.matrix[i][j] = el
                board.sum += el
            }
            if (i < 4) i++ else i = 0
        }

        return numbers to boards
    }

    fun part1(input: List<String>): Int {
        val (numbers, boards) = parseInput(input)
        for (number in numbers) {
            for (board in boards) {
                val (i, j) = board.mark(number)
                if (i == -1) continue
                if (board.rowsCount[i] == 5 || board.columnsCount[j] == 5) {
                    return number * board.sum
                }
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val (numbers, boards) = parseInput(input)
        var boardsWon = 0
        for (number in numbers) {
            for (board in boards) {
                if (board.isWon) continue
                val (i, j) = board.mark(number)
                if (i == -1) continue
                if (board.rowsCount[i] == 5 || board.columnsCount[j] == 5) {
                    boardsWon++
                    board.isWon = true
                }
                if (boardsWon == boards.size) {
                    return number * board.sum
                }
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
