package uk.co.droidinactu.ktjournalprompts

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptRequest

@JsonTest
class KtJournalPromptsApplicationTests {

    private val jsonStr = "\n" +
            "{\n" +
            "  \"title\": \"Name the one thing you are most grateful for in your life at this moment.\",\n" +
            "  \"category\": \"Gratitude Journal Prompts\"\n" +
            "}"

    private val jsonListStr = "\n" +
            "[{\n" +
            "  \"title\": \"Name the one thing you are most grateful for in your life at this moment.\",\n" +
            "  \"category\": \"Gratitude Journal Prompts\"\n" +
            "},\n" +
            "  {\n" +
            "    \"title\": \"Which people in your life are you most grateful for.\",\n" +
            "    \"category\": \"Gratitude Journal Prompts\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Write about something that made you smile today.\",\n" +
            "    \"category\": \"Gratitude Journal Prompts\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"When were you last surprised?\",\n" +
            "    \"category\": \"Gratitude Journal Prompts\"\n" +
            "  }]"

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun contextLoads() {
        val mapped = mapper.readValue(jsonStr, JournalPromptRequest::class.java)
        println("mapped: $mapped")
        Assertions.assertNotNull(mapped)

        val mapped2: List<JournalPromptRequest> = mapper.readValue(jsonListStr)
        Assertions.assertEquals(4, mapped2.size)
        println("mapped: $mapped2")
    }

}
