package org.thiamath.fizzbuzzserver.domain.model

data class StatsData(
    val mostUsed: Stat? = null,
) {
    data class Stat(
        val request: FizzBuzzRequest,
        val count: Int,
    )
}
