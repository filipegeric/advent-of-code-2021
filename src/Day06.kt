fun main() {
    fun updateOccurrences(occurrences: MutableMap<Int, Long>) {
        val zeroCount = occurrences[0]!!
        for ((value) in occurrences) {
            val nextValue = if (value == 8) 0 else value + 1
            occurrences[value] = occurrences[nextValue]!!
            if (value == 6 && zeroCount > 0) {
                occurrences[value] = occurrences[value]!! + zeroCount
            }
            if (value == 8) {
                occurrences[value] = zeroCount
            }
        }
    }

    fun getOccurrences(numbers: List<Int>): MutableMap<Int, Long> {
        val occurrences = mutableMapOf<Int, Long>()
        (0..8).forEach { occurrences[it] = 0 }

        for (number in numbers) {
            occurrences[number] = occurrences[number]!! + 1
        }
        return occurrences
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val occurrences = getOccurrences(numbers)

        repeat(80) { updateOccurrences(occurrences) }

        return occurrences.values.sum().toInt()
    }

    fun part2(input: List<String>): Long {
        val numbers = input[0].split(",").map { it.toInt() }
        val occurrences = getOccurrences(numbers)

        repeat(256) { updateOccurrences(occurrences) }

        return occurrences.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
