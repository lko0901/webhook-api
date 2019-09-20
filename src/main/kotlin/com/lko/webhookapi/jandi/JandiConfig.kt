package com.lko.webhookapi.jandi

import com.lko.webhookapi.jandi.properties.JandiProperties
import com.lko.webhookapi.jandi.properties.WebHook
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Profile("jandi")
@Configuration
class JandiConfig(@Qualifier("jandiProperties") val properties: JandiProperties) {
  @Bean("jandiRest")
  fun jandi(): RestTemplate {
    return RestTemplateBuilder()
        .setReadTimeout(Duration.ofMillis(300))
        .setConnectTimeout(Duration.ofMillis(1000))
        .build()
  }

  @Bean("jandiWebHooks")
  fun jandiWebHooks(): List<WebHook> {
    return properties.webHooks
  }
}