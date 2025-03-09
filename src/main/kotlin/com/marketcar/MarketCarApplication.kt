package com.marketcar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarketCarApplication

fun main(args: Array<String>) {
	runApplication<MarketCarApplication>(*args)
}
