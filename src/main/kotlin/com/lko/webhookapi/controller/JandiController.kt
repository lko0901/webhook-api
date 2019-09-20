package com.lko.webhookapi.controller

import com.lko.webhookapi.jandi.JandiService
import com.lko.webhookapi.jandi.properties.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Profile("jandi")
@RestController("/jandi")
class JandiController(@Qualifier("jandiWebHooks") private val jandiWebHooks: MutableList<WebHook>,
                      private val jandiService: JandiService) {

  @GetMapping(value = "/aliases")
  fun getAliases(): ResponseEntity<Map<String, String>> = jandiService.aliases()

  @PostMapping(value = "/send/{alias}")
  fun sendToAlias(@PathVariable alias: String,
                  @RequestBody params: MutableMap<String, String>): ResponseEntity<JandiResponse> = jandiService.send(alias, params)
}