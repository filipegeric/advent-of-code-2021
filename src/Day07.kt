import kotlin.math.abs

fun main() {

    fun getMinCost(positions: IntArray, costIncreaseStrategy: (Int, Int) -> Int): Int {
        positions.sort()
        var minCost = Int.MAX_VALUE

        for (fixedValue in positions.first()..positions.last()) {
            var cost = 0
            for (value in positions) cost += costIncreaseStrategy(fixedValue, value)
            if (cost < minCost) minCost = cost
        }

        return minCost
    }

    fun sumFirstNNumbers(n: Int): Int = (n * (n + 1)) / 2

    fun part1(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }.toIntArray()
        return getMinCost(positions) { fixedValue, value -> abs(fixedValue - value) }
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }.toIntArray()
        val result = getMinCost(positions) { fixedValue, value ->
            val n = abs(fixedValue - value)
            sumFirstNNumbers(n)
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
