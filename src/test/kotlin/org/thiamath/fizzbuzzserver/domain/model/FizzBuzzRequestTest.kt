package org.thiamath.fizzbuzzserver.domain.model

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FizzBuzzRequestTest {

    @ParameterizedTest
    @MethodSource("okArguments")
    fun `Calculate Fizz-Buzz`(request: FizzBuzzRequest, response: List<String>) {
        val result = request.calculateFizzBuzz()
        assertEquals(response, result)
    }

    @ParameterizedTest
    @MethodSource("errorArguments")
    fun `Check constructor validation`(int1: Int, int2: Int, limit: Int, errorMessage: String) {
        val exception = kotlin.runCatching { FizzBuzzRequest(int1, int2, limit) }.exceptionOrNull()
        assert(exception is IllegalArgumentException)
        assertEquals(errorMessage, exception?.message)
    }

    companion object {
        @JvmStatic
        fun okArguments() = listOf(
            // Happy path
            Arguments.of(
                FizzBuzzRequest(limit = 10),
                listOf("1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz", "buzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(limit = 0),
                emptyList<String>(),
            ),
            Arguments.of(
                FizzBuzzRequest(int1 = 1, limit = 10),
                listOf("fizz", "fizz", "fizz", "fizz", "fizzbuzz", "fizz", "fizz", "fizz", "fizz", "fizzbuzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(int1 = -1, limit = 10),
                listOf("fizz", "fizz", "fizz", "fizz", "fizzbuzz", "fizz", "fizz", "fizz", "fizz", "fizzbuzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(int2 = 1, limit = 10),
                listOf("buzz", "buzz", "fizzbuzz", "buzz", "buzz", "fizzbuzz", "buzz", "buzz", "fizzbuzz", "buzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(int2 = -1, limit = 10),
                listOf("buzz", "buzz", "fizzbuzz", "buzz", "buzz", "fizzbuzz", "buzz", "buzz", "fizzbuzz", "buzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(str1 = "hello", limit = 10),
                listOf("1", "2", "hello", "4", "buzz", "hello", "7", "8", "hello", "buzz"),
            ),
            Arguments.of(
                FizzBuzzRequest(str2 = "world", limit = 10),
                listOf("1", "2", "fizz", "4", "world", "fizz", "7", "8", "fizz", "world"),
            ),
            Arguments.of(
                FizzBuzzRequest(str1 = "hello", str2 = "world", limit = 10),
                listOf("1", "2", "hello", "4", "world", "hello", "7", "8", "hello", "world"),
            ),
        )

        @JvmStatic
        fun errorArguments() = listOf(
            // Test with negative limit
            Arguments.of(
                1, 1, -1,
                FizzBuzzRequest.LIMIT_ERROR_MESSAGE
            ),

            // Test with zero int1
            Arguments.of(
                0, 1, 10,
                FizzBuzzRequest.INT1_ERROR_MESSAGE
            ),

            // Test with zero int2
            Arguments.of(
                1, 0, 10,
                FizzBuzzRequest.INT2_ERROR_MESSAGE
            )
        )
    }
}
