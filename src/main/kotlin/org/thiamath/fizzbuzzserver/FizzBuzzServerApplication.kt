package org.thiamath.fizzbuzzserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FizzBuzzServerApplication

fun main(args: Array<String>) {
    runApplication<FizzBuzzServerApplication>(*args)
}
