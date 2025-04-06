package org.thiamath.fizzbuzzserver.domain.port.inbound

interface GetFizzBuzz {
    fun execute(int1: Int, int2: Int, limit: Int, str1: String, str2: String)
}
