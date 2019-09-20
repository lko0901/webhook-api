package com.lko.webhookapi.controller

import com.lko.webhookapi.jandi.JandiService
import com.lko.webhookapi.jandi.properties.*
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Profile("jandi")
@RestController
@RequestMapping("/jandi")
class JandiController(private val jandiService: JandiService) {

  @GetMapping("/aliases")
  fun getAliases(): ResponseEntity<Map<String, String>> = jandiService.aliases()

  @PostMapping("/send/{alias}")
  fun sendToAlias(@PathVariable alias: String,
                  @RequestBody params: MutableMap<String, String>): ResponseEntity<JandiResponse> = jandiService.send(alias, params)
}