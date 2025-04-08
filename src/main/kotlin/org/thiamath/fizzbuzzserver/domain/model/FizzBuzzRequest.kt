package org.thiamath.fizzbuzzserver.domain.model

/**
 * Data class representing a FizzBuzz request.
 */
data class FizzBuzzRequest(
    val int1: Int = 3,
    val int2: Int = 5,
    val limit: Int = 100,
    val str1: String = "fizz",
    val str2: String = "buzz",
) {
    companion object {
        const val LIMIT_ERROR_MESSAGE = "Limit must be greater than or equal to 0"
        const val INT1_ERROR_MESSAGE = "int1 must not be 0"
        const val INT2_ERROR_MESSAGE = "int2 must not be 0"
    }

    init {
        require(limit >= 0) { LIMIT_ERROR_MESSAGE }
        require(int1 != 0) { INT1_ERROR_MESSAGE }
        require(int2 != 0) { INT2_ERROR_MESSAGE }
    }

    fun calculateFizzBuzz(): List<String> =
        (1..limit).map {
            when (0) {
                it % (int1 * int2) -> "$str1$str2"
                it % int1 -> str1
                it % int2 -> str2
                else -> it.toString()
            }
        }
}
