package org.thiamath.fizzbuzzserver.domain.service

import org.springframework.stereotype.Service
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.port.outbound.StatsStore

@Service
class FizzBuzzService(
    private val statsStore: StatsStore,
) {
    fun calculateFizzBuzz(request: FizzBuzzRequest): List<String> {
        statsStore.store(request)
        return request.calculateFizzBuzz()
    }
}
