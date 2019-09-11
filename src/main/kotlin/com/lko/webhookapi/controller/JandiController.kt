package com.lko.webhookapi.controller

import com.lko.webhookapi.properties.WebHook
import com.sun.javaws.exceptions.InvalidArgumentException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JandiController(@Qualifier("jandiWebHooks")private val jandiWebHooks: MutableList<WebHook>) {

  @PostMapping("/send/{alias}")
  fun sendToAlias(@PathVariable alias:String) {
    if (!validAlias(alias)) {
      throw InvalidArgumentException(arrayOf("일치하는 webhook이 없습니다."))
    }
  }

  private fun validAlias(alias:String): Boolean {
    for (webHook in jandiWebHooks) {
      if (webHook.alias == alias) return true
    }
    return false
  }
}