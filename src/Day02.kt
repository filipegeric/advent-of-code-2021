fun main() {
    data class Command(val direction: String, val value: Int)

    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0

        for(line in input) {
            val (direction, valueString) = line.split(" ")
            val command = Command(direction, valueString.toInt())
            when(command.direction) {
                "forward" -> horizontalPosition += command.value
                "down" -> depth += command.value
                "up" -> depth -= command.value
            }
        }

        return depth * horizontalPosition
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0
        var aim = 0

        for(line in input) {
            val (direction, valueString) = line.split(" ")
            val command = Command(direction, valueString.toInt())
            when(command.direction) {
                "forward" -> {
                    horizontalPosition += command.value
                    depth += aim * command.value
                }
                "down" -> aim += command.value
                "up" -> aim -= command.value
            }
        }

        return depth * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
