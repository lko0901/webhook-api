package com.lko.webhookapi.jandi.properties

import org.hibernate.validator.constraints.URL
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Profile("jandi")
@Component("jandiProperties")
@ConfigurationProperties("jandi")
@Validated
class JandiProperties {
  @NotEmpty
  lateinit var headers: MultiValueMap<String, String>
  @NotEmpty
  lateinit var webHooks: List<WebHook>
}

@Validated
class WebHook {
  @NotBlank
  var alias: String = ""
  @URL
  var url: String = ""
  @NotBlank
  var template: String = ""
}

class JandiResponse {
  var code: String = ""
  var msg: String = ""
}