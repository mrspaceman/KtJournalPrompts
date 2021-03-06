package uk.co.droidinactu.ktjournalprompts.controller

import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import uk.co.droidinactu.ktjournalprompts.JournalPromptService
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt

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
        return journalPromptService.importPrompts(journalPrompts)
    }

    @GetMapping(
        "/journal-prompts",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllJournalPrompts(): List<JournalPrompt?> {
        log.trace { "Getting all journal prompts" }
        return journalPromptService.prompts
    }

    @GetMapping(
        "/journal-prompts/categories",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllJournalPromptCategories(): List<String?> {
        log.trace { "Getting all journal prompt categories" }
        return journalPromptService.promptCategories
    }

    @GetMapping(
        "/journal-prompts/random",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getRandomJournalPrompt(): JournalPrompt? {
        log.trace { "Getting all journal prompts" }
        return journalPromptService.randomPrompt
    }

    @GetMapping(
        "/journal-prompts/{id}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getJournalPrompt(@PathVariable id: Long): JournalPrompt? {
        log.trace { "Getting journal prompt with id: $id" }
        return journalPromptService.getPrompt(id)
    }

    @GetMapping(
        "/journal-prompts/{category}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getJournalPromptsByCategory(@PathVariable category: String): List<JournalPrompt?> {
        log.trace { "Getting journal prompts by category: $category" }
        return journalPromptService.getAllPromptsInCategory(category)
    }
}
