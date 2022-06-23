package uk.co.droidinactu.ktjournalprompts

import org.springframework.boot.Banner.Mode.CONSOLE
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CommonsRequestLoggingFilter


@SpringBootApplication
class KtJournalPromptsApplication

fun main(args: Array<String>) {
    runApplication<KtJournalPromptsApplication>(*args) {
        setBannerMode(CONSOLE)
    }
}

@Bean
fun requestLoggingFilter(): CommonsRequestLoggingFilter {
    val loggingFilter = CommonsRequestLoggingFilter()
    loggingFilter.setIncludeClientInfo(true)
    loggingFilter.setIncludeQueryString(true)
    loggingFilter.setIncludePayload(true)
    loggingFilter.setMaxPayloadLength(64000)
    return loggingFilter
}
