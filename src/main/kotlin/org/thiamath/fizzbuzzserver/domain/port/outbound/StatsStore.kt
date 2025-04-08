package org.thiamath.fizzbuzzserver.domain.port.outbound

import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.model.StatsData


interface StatsStore {
    fun store(request: FizzBuzzRequest)
    fun get(): StatsData
}
