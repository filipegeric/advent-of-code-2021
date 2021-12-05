fun main() {
    data class Counter(var zeros: Int = 0, var ones: Int = 0)

    fun getGammaAndEpsilonFromCounters(counters: Map<Int, Counter>): Pair<Int, Int> {
        val gamma = StringBuilder("")
        val epsilon = StringBuilder("")
        for ((index, counter) in counters) {
            if (counter.zeros > counter.ones) {
                gamma.insert(index, '0')
                epsilon.insert(index, '1')
            } else {
                gamma.insert(index, '1')
                epsilon.insert(index, '0')
            }
        }

        val gammaDecimal = gamma.toString().toInt(2)
        val epsilonDecimal = epsilon.toString().toInt(2)

        return gammaDecimal to epsilonDecimal
    }

    fun part1(input: List<String>): Int {
        val counters = mutableMapOf<Int, Counter>()

        for (number in input) {
            for ((index, digit) in number.withIndex()) {
                val counter = counters.getOrPut(index) { Counter() }
                when (digit) {
                    '0' -> counter.zeros++
                    '1' -> counter.ones++
                }
            }
        }

        val (gamma, epsilon) = getGammaAndEpsilonFromCounters(counters)

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 0)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
