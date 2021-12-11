import java.util.*

fun main() {
    val openBrackets = setOf('{', '[', '(', '<')
    val opposite = mapOf('{' to '}', '[' to ']', '(' to ')', '<' to '>')

    fun part1(input: List<String>): Int {
        val values = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        val unmatched = Stack<Char>()

        var sum = 0
        for (line in input) {
            for (c in line) {
                if (c in openBrackets) {
                    val o = opposite[c]!!
                    unmatched.push(o)
                    continue
                }
                if (unmatched.pop() != c) {
                    sum += values[c]!!
                    break
                }
            }
        }
        return sum
    }

    fun calculateScore(completionString: String): Long {
        val values = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

        return completionString.fold(0L) { acc, it -> (acc * 5) + values[it]!! }
    }

    fun getCompletionString(line: String): String? {
        val unmatched = Stack<Char>()
        for (char in line) {
            if (char in openBrackets) {
                val o = opposite[char]!!
                unmatched.push(o)
                continue
            }
            // Discard corrupt elements
            if (unmatched.pop() != char) return null
        }
        return unmatched.reversed().joinToString("")
    }

    fun part2(input: List<String>): Long {

        val scores = mutableListOf<Long>()
        for (line in input) {
            val completionString = getCompletionString(line) ?: continue
            val score = calculateScore(completionString)
            scores.add(score)
        }

        scores.sort()

        return scores[scores.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
