package org.thiamath.fizzbuzzserver.domain.service

import org.springframework.stereotype.Service
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest

@Service
class FizzBuzzService {
    fun calculateFizzBuzz(request: FizzBuzzRequest): List<String> {
        val (int1, int2, limit, str1, str2) = request
        if (limit < 0) throw BadArgumentException("Limit must be greater than 0")
        if (int1 == 0) throw BadArgumentException("int1 must not be 0")
        if (int2 == 0) throw BadArgumentException("int2 must not be 0")
        if (limit == 0) return emptyList()
        return (1..limit).map {
            when (0) {
                it % (int1 * int2) -> "$str1$str2"
                it % int1 -> str1
                it % int2 -> str2
                else -> it.toString()
            }
        }
    }
}

class BadArgumentException(message: String) : Exception(message)
