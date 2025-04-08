package org.thiamath.fizzbuzzserver.domain.port.inbound

import org.thiamath.fizzbuzzserver.domain.model.StatsData

interface GetStats {
    fun execute(): StatsResponse
}

data class StatsResponse(
    val data: StatsData,
)
