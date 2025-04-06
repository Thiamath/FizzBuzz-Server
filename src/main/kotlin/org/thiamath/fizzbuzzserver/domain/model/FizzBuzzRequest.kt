package org.thiamath.fizzbuzzserver.domain.model

data class FizzBuzzRequest(
    val int1: Int = 3,
    val int2: Int = 5,
    val limit: Int = 100,
    val str1: String = "fizz",
    val str2: String = "buzz",
)
