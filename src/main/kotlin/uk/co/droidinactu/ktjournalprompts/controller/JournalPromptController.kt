package uk.co.droidinactu.ktjournalprompts.controller

import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.co.droidinactu.ktjournalprompts.JournalPromptService
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt
import kotlin.random.Random

@RestController
class JournalPromptController(
    private val journalPromptService: JournalPromptService
) {

    private val log = KotlinLogging.logger {}

    @PostMapping(
        "/journal-prompts",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createJournalPrompts(
        @RequestBody journalPrompts: List<JournalPromptRequest>
    ): List<JournalPrompt?> {
        log.info { "Creating ${journalPrompts.size} journal prompts" }
        return journalPromptService.createPrompts(journalPrompts)
    }

    @PostMapping(
        "/journal-prompts/import",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun importJournalPrompts(
        @RequestBody journalPrompts: List<JournalPromptImportRequest>
    ): List<JournalPrompt?> {
        log.info { "Importing ${journalPrompts.size} journal prompts" }
        val prompts: MutableList<JournalPromptRequest> = mutableListOf()
        journalPrompts.forEach {
            it.prompts?.forEach { prompt -> prompts.add(JournalPromptRequest(prompt, it.category ?: "Unknown")) }
        }
        return journalPromptService.createPrompts(prompts.toList())
    }

    @GetMapping(
        "/journal-prompts",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getJournalPrompts(): List<JournalPrompt?> {
        log.trace { "Getting all journal prompts" }

        return journalPromptService.getAllPrompts()
    }

    @GetMapping(
        "/journal-prompts/random",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getRandomJournalPrompt(): JournalPrompt? {
        log.trace { "Getting all journal prompts" }

        val prompts = journalPromptService.getAllPrompts()
        return prompts.get(Random.nextInt(prompts.size))
    }

    @GetMapping(
        "/journal-prompts/{id}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getJournalPrompt(id: Long): JournalPrompt? {
        log.trace { "Getting journal prompt with id: $id" }

        val prompt = journalPromptService.getPrompt(id);
        return prompt
    }

    @GetMapping(
        "/journal-prompts/{category}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getJournalPromptsByCategory(category: String): List<JournalPrompt?> {
        log.trace { "Getting journal prompts by category: $category" }
        val prompts = journalPromptService.getAllPromptsInCategory(category)
        return prompts
    }
}
