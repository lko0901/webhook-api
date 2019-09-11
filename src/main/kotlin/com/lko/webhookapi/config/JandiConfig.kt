package com.lko.webhookapi.config

import com.lko.webhookapi.properties.JandiProperties
import com.lko.webhookapi.properties.WebHook
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration
import javax.annotation.PostConstruct

@Configuration
class JandiConfig {
  lateinit var properties: JandiProperties
  lateinit var webHooks: MutableList<WebHook>

  @PostConstruct
  fun init() {
    this.webHooks = properties.webHooks
  }

  @Bean("jandi")
  fun jandi():RestTemplate {
    return RestTemplateBuilder()
        .rootUri(properties.baseUrl)
        .setReadTimeout(Duration.ofMillis(300))
        .setConnectTimeout(Duration.ofMillis(1000))
        .build()
  }

  @Bean("jandiWebHooks")
  fun jandiWebHooks():MutableList<WebHook> {
    return this.webHooks
  }
}