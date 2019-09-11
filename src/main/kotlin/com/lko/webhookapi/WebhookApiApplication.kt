package com.lko.webhookapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebhookApiApplication

fun main(args: Array<String>) {
  runApplication<WebhookApiApplication>(*args)
}
