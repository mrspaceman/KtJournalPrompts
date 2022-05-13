package uk.co.droidinactu.ktjournalprompts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CommonsRequestLoggingFilter

@SpringBootApplication
open class KtJournalPromptsApplication

fun main(args: Array<String>) {
    runApplication<KtJournalPromptsApplication>(*args)
}

@Bean
fun requestLoggingFilter(): CommonsRequestLoggingFilter? {
    val loggingFilter = CommonsRequestLoggingFilter()
    loggingFilter.setIncludeClientInfo(true)
    loggingFilter.setIncludeQueryString(true)
    loggingFilter.setIncludePayload(true)
    loggingFilter.setMaxPayloadLength(64000)
    return loggingFilter
}
