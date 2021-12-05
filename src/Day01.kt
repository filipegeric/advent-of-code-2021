fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        for (i in 1 until input.size) {
            val previousValue = input[i - 1].toInt()
            val currentValue = input[i].toInt()
            if(currentValue > previousValue) count++
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for(i in 3 until input.size) {
            val previousValue = input[i - 3].toInt()
            val currentValue = input[i].toInt()
            if(currentValue > previousValue) count++
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
