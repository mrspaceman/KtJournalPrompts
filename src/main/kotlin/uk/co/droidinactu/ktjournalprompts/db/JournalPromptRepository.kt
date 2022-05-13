package uk.co.droidinactu.ktjournalprompts.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface JournalPromptRepository : CrudRepository<JournalPrompt?, Long?>
