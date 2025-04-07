package org.thiamath.fizzbuzzserver.domain.service

import org.springframework.stereotype.Service
import org.thiamath.fizzbuzzserver.domain.model.FizzBuzzRequest
import org.thiamath.fizzbuzzserver.domain.service.BadArgumentException.Companion.ErrorMessage.*

@Service
class FizzBuzzService {
    fun calculateFizzBuzz(request: FizzBuzzRequest): List<String> {
        val (int1, int2, limit, str1, str2) = request
        if (limit < 0) throw BadArgumentException("$LIMIT_GT_0")
        if (int1 == 0) throw BadArgumentException("$INT1_NE_0")
        if (int2 == 0) throw BadArgumentException("$INT2_NE_0")
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

class BadArgumentException(message: String) : Exception(message) {
    companion object {
        enum class ErrorMessage(private val message: String) {
            LIMIT_GT_0("Limit must be greater than 0"),
            INT1_NE_0("int1 must not be 0"),
            INT2_NE_0("int2 must not be 0");

            override fun toString(): String {
                return message
            }
        }
    }
}
