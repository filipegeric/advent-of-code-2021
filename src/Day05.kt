import kotlin.math.max
import kotlin.math.min

fun main() {
    class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        private val isHorizontal
            get() = x1 == x2
        private val isVertical
            get() = y1 == y2
        val allDots: List<Pair<Int, Int>>
            get() = when {
                isHorizontal -> (min(y1, y2)..max(y1, y2)).toList().map { x1 to it }
                isVertical -> (min(x1, x2)..max(x1, x2)).toList().map { it to y1 }
                else -> getDiagonalDots()
            }

        private fun getDiagonalDots(): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            var x = x1
            val xIncrement = if (x1 > x2) -1 else 1
            var y = y1
            val yIncrement = if (y1 > y2) -1 else 1
            while (x != x2 + xIncrement && y != y2 + yIncrement) {
                list.add(x to y)
                x += xIncrement
                y += yIncrement
            }
            return list
        }
    }

    fun parse(input: List<String>): List<Line> = input.map { lineString ->
        val (d1String, d2String) = lineString.split(" -> ")
        val (x1String, y1String) = d1String.split(",")
        val (x2String, y2String) = d2String.split(",")
        val x1 = x1String.toInt()
        val y1 = y1String.toInt()
        val x2 = x2String.toInt()
        val y2 = y2String.toInt()
        Line(x1, y1, x2, y2)
    }

    fun countIntersections(lines: List<Line>): Int {
        val coordinates = mutableMapOf<Pair<Int, Int>, Int>()
        for (line in lines) {
            for (dot in line.allDots) {
                val coordinateValue = coordinates.getOrPut(dot) { 0 }
                coordinates[dot] = coordinateValue + 1
            }
        }
        return coordinates.values.count { it > 1 }
    }

    fun part1(input: List<String>): Int {
        val parsedInput = parse(input)
        val lines = parsedInput.filter { it.x1 == it.x2 || it.y1 == it.y2 }
        return countIntersections(lines)
    }

    fun part2(input: List<String>): Int {
        val lines = parse(input)
        return countIntersections(lines)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
