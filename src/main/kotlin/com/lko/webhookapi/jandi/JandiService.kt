package com.lko.webhookapi.jandi

import com.lko.webhookapi.jandi.properties.JandiProperties
import com.lko.webhookapi.jandi.properties.JandiResponse
import com.lko.webhookapi.jandi.properties.WebHook
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Profile
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Profile("jandi")
@Service
class JandiService(@Qualifier("jandiRest") private val jandi: RestTemplate,
                   @Qualifier("jandiProperties") val properties: JandiProperties,
                   @Qualifier("jandiWebHooks") private val jandiWebHooks: MutableList<WebHook>) {

  fun aliases(): ResponseEntity<Map<String, String>> = ResponseEntity(jandiWebHooks.map { it.alias to extractBind(it.template) }.toMap(), HttpStatus.OK)

  fun send(alias: String,
           params: MutableMap<String, String>): ResponseEntity<JandiResponse>
      = validAlias(alias)?.let {
    jandi.postForEntity(it.url,
        HttpEntity(makeDataString(it.template, params), properties.headers),
        JandiResponse::class.java)
  } ?: throw IllegalArgumentException("invalid alias")

  fun makeDataString(data: String,
                     params: MutableMap<String, String>): String {
    var str = data
    for ((key, value) in params) {
      str = str.replace("{{$key}}", "$value")
    }
    return str
  }

  private fun validAlias(alias: String): WebHook? {
    for (webHook in jandiWebHooks) {
      if (webHook.alias == alias) return webHook
    }
    return null
  }

  private fun extractBind(template: String): String {
    return "\\{\\{[a-zA-Z0-9_-]*}}".toRegex()
        .findAll(template)
        .map { matchResult ->
          matchResult.value
              .replace("{{", "")
              .replace("}}", "")
        }
        .toList()
        .joinToString(", ", "{ ", " }") { """ "$it": "" """ }
  }
}