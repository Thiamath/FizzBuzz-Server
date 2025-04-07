package org.thiamath.fizzbuzzserver.adapter.inbound

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.port.inbound.FizzBuzzResponse
import org.thiamath.fizzbuzzserver.domain.port.inbound.GetFizzBuzz
import org.thiamath.fizzbuzzserver.domain.service.FizzBuzzService

@RestController
class FizzBuzzImpl(private val fizzBuzzService: FizzBuzzService) : GetFizzBuzz {
    @GetMapping("/fizzbuzz", produces = ["application/json"])
    @ResponseBody
    override fun execute(
        @RequestParam(name = "int1", defaultValue = "3") int1: Int,
        @RequestParam(name = "int2", defaultValue = "5") int2: Int,
        @RequestParam(name = "limit", defaultValue = "100") limit: Int,
        @RequestParam(name = "str1", defaultValue = "fizz") str1: String,
        @RequestParam(name = "str2", defaultValue = "buzz") str2: String,
    ) =
        FizzBuzzResponse(
            data = fizzBuzzService.calculateFizzBuzz(
                FizzBuzzRequest(int1, int2, limit, str1, str2)
            ).joinToString(", ")
        )
}
