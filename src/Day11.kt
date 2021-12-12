import java.util.concurrent.atomic.AtomicInteger

fun main() {

    fun getDirections(i: Int, j: Int, matrix: Matrix): List<Pair<Int, Int>> = listOf(
        i - 1 to j,
        i - 1 to j + 1,
        i to j + 1,
        i + 1 to j + 1,
        i + 1 to j,
        i + 1 to j - 1,
        i to j - 1,
        i - 1 to j - 1
    ).filter { (x, y) -> x in matrix.indices && y in matrix[x].indices }

    fun flash(
        i: Int,
        j: Int,
        matrix: Matrix,
        alreadyFlashed: MutableSet<Pair<Int, Int>>,
        flashes: AtomicInteger
    ) {
        matrix[i][j] = 0
        flashes.incrementAndGet()
        val directions = getDirections(i, j, matrix)
        alreadyFlashed.add(i to j)
        directions.forEach { (x, y) -> if (!alreadyFlashed.contains(x to y)) matrix[x][y]++ }
        directions.forEach { (x, y) ->
            if (matrix[x][y] > 9) flash(x, y, matrix, alreadyFlashed, flashes)
        }
    }

    fun part1(input: List<String>): Int {
        val matrix = input.toMatrix()

        val flashes = AtomicInteger(0)
        repeat(100) {
            val alreadyFlashed = mutableSetOf<Pair<Int, Int>>()
            matrix.forEach { i, j -> matrix[i][j]++ }
            matrix.forEach { i, j ->
                val value = matrix[i][j]
                if (value < 10) return@forEach
                flash(i, j, matrix, alreadyFlashed, flashes)
            }
        }

        return flashes.get()
    }

    fun part2(input: List<String>): Int {
        val matrix = input.toMatrix()
        var step = 0
        while (true) {
            val alreadyFlashed = mutableSetOf<Pair<Int, Int>>()
            var sum = 0
            matrix.forEach { i, j ->
                matrix[i][j]++
                sum += matrix[i][j]
            }
            if (sum == 100) return step
            matrix.forEach { i, j ->
                val value = matrix[i][j]
                if (value < 10) return@forEach
                flash(i, j, matrix, alreadyFlashed, AtomicInteger(0))
            }
            step++
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
