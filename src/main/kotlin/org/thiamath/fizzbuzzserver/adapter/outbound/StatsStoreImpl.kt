package org.thiamath.fizzbuzzserver.adapter.outbound

import org.springframework.stereotype.Repository
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.model.StatsData
import org.thiamath.fizzbuzzserver.domain.port.outbound.StatsStore

@Repository
class StatsStoreImpl : StatsStore {
    private val db = mutableMapOf<FizzBuzzRequest, Int>()

    override fun store(request: FizzBuzzRequest) {
        db[request] = db.getOrDefault(request, 0) + 1
    }

    override fun get(): StatsData {
        return StatsData(
            mostUsed = db.entries.maxByOrNull { it.value }?.let {
                StatsData.Stat(
                    request = it.key,
                    count = it.value,
                )
            },
        )
    }
}
