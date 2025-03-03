package com.expenify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpenifyApplication

fun main(args: Array<String>) {
	runApplication<ExpenifyApplication>(*args)
}
