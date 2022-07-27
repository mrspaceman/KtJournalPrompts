package uk.co.droidinactu.ktjournalprompts

import mu.KotlinLogging
import org.springframework.stereotype.Service
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptImportRequest
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptRequest
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt
import uk.co.droidinactu.ktjournalprompts.db.JournalPromptRepository
import kotlin.random.Random

@Service
class JournalPromptService(private val journalPromptRepository: JournalPromptRepository) {
    private val log = KotlinLogging.logger {}

    val prompts
        get() = journalPromptRepository.findAll().toList()
    val randomPrompt
        get() = prompts[Random.nextInt(prompts.size)]
    val promptCategories
        get() = journalPromptRepository.findAll()
            .map { it?.category }.toSet().toList()

    fun getPrompt(id: Long): JournalPrompt? {
        return prompts.firstOrNull { it?.id == id }
    }

    fun getAllPromptsInCategory(category: String): List<JournalPrompt?> {
        return prompts.filterNotNull().filter { it.equals(category) }
    }

    fun importPrompts(journalPrompts: List<JournalPromptImportRequest>): List<JournalPrompt?> {
        val prompts: MutableList<JournalPromptRequest> = mutableListOf()
        journalPrompts.forEach {
            it.prompts?.forEach { prompt -> prompts.add(JournalPromptRequest(prompt, it.category ?: "Unknown")) }
        }
        return createPrompts(prompts.toList())
    }

    fun createPrompts(journalPrompts: List<JournalPromptRequest>): List<JournalPrompt?> {
        return journalPromptRepository.saveAll(
            journalPrompts.map { JournalPrompt(it) }).toList()
    }

}
