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

    fun getCounters(input: List<String>): Map<Int, Counter> {
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

        return counters
    }

    fun getCounterAt(index: Int, input: List<String>): Counter {
        val counter = Counter()
        for (el in input) {
            when (el[index]) {
                '0' -> counter.zeros++
                '1' -> counter.ones++
            }
        }
        return counter
    }

    fun part1(input: List<String>): Int {
        val counters = getCounters(input)
        val (gamma, epsilon) = getGammaAndEpsilonFromCounters(counters)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val oxygenList = input.toMutableList()
        val co2List = input.toMutableList()

        for (i in input.first().indices) {
            val counterOxygen = getCounterAt(i, oxygenList)
            val oxygenChar = if (counterOxygen.zeros > counterOxygen.ones) '1' else '0'
            oxygenList.removeIf { it[i] == oxygenChar && oxygenList.size > 1 }

            val counterCo2 = getCounterAt(i, co2List)
            val co2Char = if (counterCo2.zeros > counterCo2.ones) '0' else '1'
            co2List.removeIf { it[i] == co2Char && co2List.size > 1 }
        }

        return oxygenList.first().toInt(2) * co2List.first().toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
