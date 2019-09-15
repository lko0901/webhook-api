package com.lko.webhookapi.controller

import com.lko.webhookapi.properties.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class JandiController(@Qualifier("jandiWebHooks")private val jandiWebHooks: MutableList<WebHook>,
                      @Qualifier("jandi") private val jandi: RestTemplate) {

  @PostMapping("/send/{alias}")
  fun sendToAlias(@PathVariable alias:String) {
    var webHook:WebHook = validAlias(alias)
    if (!webHook.isEmpty()) {

    }
  }

  private fun validAlias(alias:String): WebHook {
    for (webHook in jandiWebHooks) {
      if (webHook.alias == alias) return webHook
    }
    return WebHook.emptyWebHook()
  }
}