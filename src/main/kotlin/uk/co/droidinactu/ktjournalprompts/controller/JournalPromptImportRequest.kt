package uk.co.droidinactu.ktjournalprompts.controller

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
data class JournalPromptImportRequest(
    @JsonProperty("category")
    var category: String?,
    @JsonProperty("prompts")
    var prompts: List<String>?
) {
    override fun toString(): String {
        return "JournalPromptRequest(category=$category has ${prompts?.size} prompts)"
    }
}
