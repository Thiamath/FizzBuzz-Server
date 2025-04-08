package org.thiamath.fizzbuzzserver.adapter.outbound

import org.junit.jupiter.api.Test
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import kotlin.test.assertEquals


class StatsStoreImplTest {
    private val statsStore = StatsStoreImpl()

    @Test
    fun `test store and get`() {
        // Given
        val request = FizzBuzzRequest(3, 5, 15, "Fizz", "Buzz")

        // When
        statsStore.store(request)

        // Then
        val stats = statsStore.get()
        assertEquals(1, stats.mostUsed?.count)
        assertEquals(request, stats.mostUsed?.request)
    }

    @Test
    fun `test store multiple requests`() {
        // Given
        val request1 = FizzBuzzRequest(3, 5, 15, "Fizz", "Buzz")
        val request2 = FizzBuzzRequest(2, 4, 8, "Fizz", "Buzz")

        // When
        statsStore.store(request1)
        statsStore.store(request2)
        statsStore.store(request2)

        // Then
        val stats = statsStore.get()
        assertEquals(2, stats.mostUsed?.count)
        assertEquals(request2, stats.mostUsed?.request)
    }
}
