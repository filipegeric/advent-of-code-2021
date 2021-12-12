import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

typealias Matrix = Array<IntArray>

inline fun Matrix.forEach(block: (i: Int, j: Int) -> Unit) {
    for (i in this.indices) {
        for (j in this[i].indices) {
            block(i, j)
        }
    }
}

fun Matrix.print() {
    for (i in this.indices) {
        for (j in this[i].indices) {
            print(this[i][j])
        }
        println()
    }
}

fun List<String>.toMatrix(): Matrix = this.map { s ->
    s.split("").drop(1).dropLast(1).map { it.toInt() }.toIntArray()
}.toTypedArray()