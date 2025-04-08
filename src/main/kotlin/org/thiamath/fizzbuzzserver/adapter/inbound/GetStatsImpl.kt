package org.thiamath.fizzbuzzserver.adapter.inbound

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.thiamath.fizzbuzzserver.domain.port.inbound.GetStats
import org.thiamath.fizzbuzzserver.domain.port.inbound.StatsResponse
import org.thiamath.fizzbuzzserver.domain.service.StatsService

/**
 * Implementation of the GetStats use case.
 *
 * @property statsService The service to retrieve statistics data.
 */
@RestController
class GetStatsImpl(
    private val statsService: StatsService,
) : GetStats {

    /**
     * Executes the use case to get the statistics.
     *
     * @return The response containing the statistics data.
     */
    @GetMapping("/stats")
    @ResponseBody
    override fun execute(): StatsResponse = StatsResponse(
        data = statsService.getStats()
    )
}
