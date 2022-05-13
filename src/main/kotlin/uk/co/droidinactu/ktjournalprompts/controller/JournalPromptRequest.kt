package uk.co.droidinactu.ktjournalprompts.controller

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
data class JournalPromptRequest(
    @JsonProperty("title")
    var title: String?,
    @JsonProperty("category")
    var category: String?
) {
    override fun toString(): String {
        return "JournalPromptRequest(title=$title, category=$category)"
    }
}
