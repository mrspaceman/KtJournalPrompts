package uk.co.droidinactu.ktjournalprompts

import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class VersionController {

    private val log = KotlinLogging.logger {}

    @Value("\${journalpromptserver.name:unknownApp}")
    private val name: String? = null

    @get:GetMapping("/version")
    @Value("\${journalpromptserver.version:unknownVersion}")
    val version: String? = null
        get() {
            log.trace(this.javaClass.name + ": Getting Application Version")
            return "{\"name\":\"$name\",\"version\": \"$field\"}"
        }
}
