fun main() {

    fun getDirections(i: Int, j: Int, matrix: Matrix): List<Pair<Int, Int>> {
        return arrayOf(
            i to j + 1, i to j - 1, i + 1 to j, i - 1 to j
        ).filter { (x, y) -> x in matrix.indices && y in matrix[x].indices }
    }

    fun isLowPoint(i: Int, j: Int, matrix: Matrix): Boolean {
        val value = matrix[i][j]
        val directions = getDirections(i, j, matrix)
        val values = directions.map { (x, y) -> matrix[x][y] }
        return values.all { it > value }
    }

    fun part1(input: List<String>): Int {
        val matrix = input.toMatrix()

        var sum = 0
        matrix.forEach { i, j ->
            if (isLowPoint(i, j, matrix)) sum += matrix[i][j] + 1
        }
        return sum
    }

    fun getBasin(
        i: Int, j: Int, matrix: Matrix, basin: MutableSet<Pair<Int, Int>>, checked: MutableSet<Pair<Int, Int>>
    ) {
        checked.add(i to j)
        val directions = getDirections(i, j, matrix)
        val higherAdjacent = directions.filter { (x, y) ->
            matrix[x][y] != 9 && !checked.contains(x to y) && matrix[x][y] - matrix[i][j] >= 1
        }
        checked.addAll(higherAdjacent)
        basin.addAll(higherAdjacent)
        higherAdjacent.forEach { (x, y) -> getBasin(x, y, matrix, basin, checked) }
    }

    fun part2(input: List<String>): Int {
        val matrix = input.toMatrix()

        val checkedFields = mutableSetOf<Pair<Int, Int>>()

        val sizes = mutableListOf<Int>()
        matrix.forEach { i, j ->
            if (!isLowPoint(i, j, matrix)) return@forEach
            val s = mutableSetOf(i to j)
            getBasin(i, j, matrix, s, checkedFields)
            sizes.add(s.size)
            println(s)
        }

        val (first, second, third) = sizes.sortedDescending()

        return first * second * third
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

private fun List<String>.toMatrix(): Matrix = this.map { s ->
    s.split("").drop(1).dropLast(1).map { it.toInt() }.toIntArray()
}.toTypedArray()

private inline fun Matrix.forEach(block: (i: Int, j: Int) -> Unit) {
    for (i in this.indices) {
        for (j in this[i].indices) {
            block(i, j)
        }
    }
}
