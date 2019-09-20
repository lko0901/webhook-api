package com.lko.webhookapi

import com.lko.webhookapi.jandi.properties.JandiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import kotlin.reflect.jvm.internal.impl.utils.DFS

@SpringBootApplication
@EnableConfigurationProperties(JandiProperties::class)
class WebHookApiApplication
  private val PROPS = arrayOf("--spring.config.name=application,application-local")
  fun main(args: Array<String>) {
    runApplication<WebHookApiApplication>(
        *(mutableListOf<String>().apply {
          this.addAll(args)
          this.addAll(PROPS)
        }.toTypedArray()))
  }


