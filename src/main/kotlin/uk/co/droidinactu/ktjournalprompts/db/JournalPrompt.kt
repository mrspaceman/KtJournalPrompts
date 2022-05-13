package uk.co.droidinactu.ktjournalprompts.db

import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptRequest
import javax.persistence.*

@Table(name = "journal_prompt")
@Entity
class JournalPrompt(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null,
    @Column
    var title: String,
    @Column
    var category: String
) {
    constructor() : this(null, "", "")
    constructor(journalPromptRequest: JournalPromptRequest) : this(
        null,
        journalPromptRequest?.title ?: "JournalPrompt",
        journalPromptRequest?.category ?: "UnKnown"
    )
}
