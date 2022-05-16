package uk.co.droidinactu.ktjournalprompts

import org.springframework.stereotype.Service
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptImportRequest
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptRequest
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt
import uk.co.droidinactu.ktjournalprompts.db.JournalPromptRepository
import kotlin.random.Random

@Service
class JournalPromptService(private val journalPromptRepository: JournalPromptRepository) {

    fun getAllPrompts(): List<JournalPrompt?> {
        return journalPromptRepository.findAll().toList()
    }

    fun getPrompt(id: Long): JournalPrompt? {
        return getAllPrompts().firstOrNull { it?.id == id }
    }

    fun importPrompts(promptToImport: List<JournalPromptImportRequest>): List<JournalPrompt?> {
        val prompts: MutableList<JournalPromptRequest> = mutableListOf()
        promptToImport.forEach {
            it.prompts?.forEach { prompt -> prompts.add(JournalPromptRequest(prompt, it.category ?: "Unknown")) }
        }
        return createPrompts(prompts.toList())
    }

    fun createPrompts(journalPrompts: List<JournalPromptRequest>): List<JournalPrompt?> {
        return journalPromptRepository.saveAll(
            journalPrompts.map { JournalPrompt(it) }).toList()
    }

    fun getAllPromptsInCategory(category: String): List<JournalPrompt?> {
        return getAllPrompts().filterNotNull().filter { it.equals(category) }
    }

    fun createPrompt(journalPromptReq: JournalPromptRequest): JournalPrompt {
        return journalPromptRepository.save(
            JournalPrompt(journalPromptReq)
        )
    }

    fun getRandomPrompt(): JournalPrompt? {
        val prompts = getAllPrompts()
        return prompts[Random.nextInt(prompts.size)]
    }

}
