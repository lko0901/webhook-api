package com.lko.webhookapi.properties

import lombok.Getter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Getter
@Component
@ConfigurationProperties("jandi")
class JandiProperties {
  lateinit var baseUrl:String
  lateinit var method: String
  lateinit var headers: MutableMap<String, String>
  lateinit var webHooks: MutableList<WebHook>
}

class WebHook {
  lateinit var alias:String
  lateinit var url: String
}