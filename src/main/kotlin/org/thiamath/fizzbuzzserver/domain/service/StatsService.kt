package org.thiamath.fizzbuzzserver.domain.service

import org.springframework.stereotype.Service
import org.thiamath.fizzbuzzserver.domain.model.StatsData
import org.thiamath.fizzbuzzserver.domain.port.outbound.StatsStore

@Service
class StatsService(
    private val statsStore: StatsStore,
) {
    fun getStats(): StatsData = statsStore.get()
}
