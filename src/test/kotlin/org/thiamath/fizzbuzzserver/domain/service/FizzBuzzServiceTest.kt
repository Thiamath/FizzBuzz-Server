package org.thiamath.fizzbuzzserver.domain.service

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.port.outbound.StatsStore
import kotlin.test.assertEquals

class FizzBuzzServiceTest {
    private val statsStore: StatsStore = mock()
    private val fizzBuzzService: FizzBuzzService = FizzBuzzService(statsStore)

    @Test
    fun `Run calculateFizzBuzz without errors`() {
        // Given
        val request = FizzBuzzRequest(limit = 10)

        // When
        val result = fizzBuzzService.calculateFizzBuzz(request)

        // Then
        assertEquals(request.calculateFizzBuzz(), result)

        // And
        verify(statsStore).store(request)
    }
}
