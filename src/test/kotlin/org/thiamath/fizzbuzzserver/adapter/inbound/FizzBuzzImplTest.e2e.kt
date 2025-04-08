package org.thiamath.fizzbuzzserver.adapter.inbound

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest

@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @ParameterizedTest
    @MethodSource("okArguments")
    fun `should return fizzbuzz response based on input parameters`(
        int1: Int?,
        int2: Int?,
        limit: Int?,
        str1: String?,
        str2: String?,
        expectedResult: String,
    ) {
        // Build request with optional parameters
        val requestBuilder = get("/fizzbuzz")

        int1?.let { requestBuilder.param("int1", it.toString()) }
        int2?.let { requestBuilder.param("int2", it.toString()) }
        limit?.let { requestBuilder.param("limit", it.toString()) }
        str1?.let { requestBuilder.param("str1", it) }
        str2?.let { requestBuilder.param("str2", it) }

        // Execute request and verify
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value(expectedResult))
    }

    @ParameterizedTest
    @MethodSource("errorArguments")
    fun `should return error response for invalid parameters`(
        int1: Int?,
        int2: Int?,
        limit: Int?,
        str1: String?,
        str2: String?,
        expectedErrorMessage: String,
    ) {
        // Build request with optional parameters
        val requestBuilder = get("/fizzbuzz")

        int1?.let { requestBuilder.param("int1", it.toString()) }
        int2?.let { requestBuilder.param("int2", it.toString()) }
        limit?.let { requestBuilder.param("limit", it.toString()) }
        str1?.let { requestBuilder.param("str1", it) }
        str2?.let { requestBuilder.param("str2", it) }

        // Execute request and verify
        mockMvc.perform(requestBuilder)
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.error").value("Bad Request"))
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.message").value(expectedErrorMessage))
    }

    companion object {
        @JvmStatic
        fun okArguments() = listOf(
            // Test with default parameters
            Arguments.of(
                null, null, null, null, null,
                "1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz, 16, 17, fizz, 19, buzz, fizz, 22, 23, fizz, buzz, 26, fizz, 28, 29, fizzbuzz, 31, 32, fizz, 34, buzz, fizz, 37, 38, fizz, buzz, 41, fizz, 43, 44, fizzbuzz, 46, 47, fizz, 49, buzz, fizz, 52, 53, fizz, buzz, 56, fizz, 58, 59, fizzbuzz, 61, 62, fizz, 64, buzz, fizz, 67, 68, fizz, buzz, 71, fizz, 73, 74, fizzbuzz, 76, 77, fizz, 79, buzz, fizz, 82, 83, fizz, buzz, 86, fizz, 88, 89, fizzbuzz, 91, 92, fizz, 94, buzz, fizz, 97, 98, fizz, buzz"
            ),

            // Test with custom int1 and int2
            Arguments.of(
                2, 3, 10, null, null,
                "1, fizz, buzz, fizz, 5, fizzbuzz, 7, fizz, buzz, fizz"
            ),

            // Test with custom strings
            Arguments.of(
                3, 5, 10, "hello", "world",
                "1, 2, hello, 4, world, hello, 7, 8, hello, world"
            ),

            // Test with only int1 modified
            Arguments.of(
                2, null, 10, null, null,
                "1, fizz, 3, fizz, buzz, fizz, 7, fizz, 9, fizzbuzz"
            ),

            // Test with only int2 modified
            Arguments.of(
                null, 2, 10, null, null,
                "1, buzz, fizz, buzz, 5, fizzbuzz, 7, buzz, fizz, buzz"
            )
        )

        @JvmStatic
        fun errorArguments() = listOf(
            // Test with negative limit
            Arguments.of(
                null, null, -1, null, null,
                FizzBuzzRequest.LIMIT_ERROR_MESSAGE
            ),

            // Test with zero int1
            Arguments.of(
                0, null, 10, null, null,
                FizzBuzzRequest.INT1_ERROR_MESSAGE
            ),

            // Test with zero int2
            Arguments.of(
                null, 0, 10, null, null,
                FizzBuzzRequest.INT2_ERROR_MESSAGE
            )
        )
    }
}
