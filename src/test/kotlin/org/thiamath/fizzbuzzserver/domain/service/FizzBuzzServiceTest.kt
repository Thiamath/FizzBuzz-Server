package org.thiamath.fizzbuzzserver.domain.service

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FizzBuzzServiceTest {
    private val fizzBuzzService: FizzBuzzService = FizzBuzzService()


    @ParameterizedTest
    @MethodSource("okArguments")
    fun `Run calculateFizzBuzz without errors`(request: FizzBuzzRequest, response: List<String>) {
        val result = fizzBuzzService.calculateFizzBuzz(request)
        assertEquals(response, result)
    }

    @ParameterizedTest
    @MethodSource("errorArguments")
    fun `calculateFizzBuzz with errors`(request: FizzBuzzRequest) {
        assertFailsWith<BadArgumentException> { fizzBuzzService.calculateFizzBuzz(request) }
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
                listOf("1", "2", "helloworld", "4", "world", "helloworld", "7", "8", "helloworld", "world"),
            ),
        )

        @JvmStatic
        fun errorArguments() = listOf(
            Arguments.of(FizzBuzzRequest(int1 = 0)),
            Arguments.of(FizzBuzzRequest(int2 = 0)),
            Arguments.of(FizzBuzzRequest(limit = -1)),
        )
    }
}
