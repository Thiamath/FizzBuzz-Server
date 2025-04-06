package org.thiamath.fizzbuzzserver.domain.service

import org.springframework.stereotype.Service
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest

@Service
class FizzBuzzService {
    fun calculateFizzBuzz(request: FizzBuzzRequest): List<String> {
        throw BadRequestError()
    }
}

class BadRequestError(message: String) : Error(message) {
    constructor() : this("")
}
