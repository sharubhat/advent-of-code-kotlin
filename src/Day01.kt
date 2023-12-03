fun main() {
    val digits = mapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun getCalibrationValue(it: String) =
        "${it.first { it.isDigit() }}${it.last() { it.isDigit() }}".toInt()

    fun String.possibleWordsAt(startingAt: Int): List<String> =
        (3..5).map {
            substring(startingAt, (startingAt + it).coerceAtMost(length))
        }

    fun getCalibrationValueProb2(line: String): Int {
        val res = getCalibrationValue(
            line.mapIndexedNotNull { index, c ->
                when {
                    c.isDigit() -> c.digitToInt()
                    else -> line.possibleWordsAt(index).firstNotNullOfOrNull { digits[it] }
                }
            }.joinToString()
        )
        "$line -> $res".println()
        return res
    }

    val input = readInput("Day01")
    input.map { getCalibrationValue(it) }.foldRight(0) { x, y -> x + y }.println()
    input.map { getCalibrationValueProb2(it) }.foldRight(0) { x, y -> x + y }.println()
}
