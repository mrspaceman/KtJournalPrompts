package uk.co.droidinactu.ktjournalprompts

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt
import uk.co.droidinactu.ktjournalprompts.db.JournalPromptRepository

private const val TEST_PROMPT_TITLE = "test prompt "
private const val TEST_PROMPT_CATEGORY = "test prompt category"

@SpringBootTest
// @DataJpaTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
internal class JournalPromptServiceTest {

    @Autowired
    private lateinit var journalPromptService: JournalPromptService

    @Autowired
    private lateinit var journalPromptRepository: JournalPromptRepository

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getAllPrompts_withNoPrompts_returnsEmptyList() {
        journalPromptRepository.deleteAll()
        val allPrompts = journalPromptService.getAllPrompts()
        Assertions.assertNotNull(allPrompts)
        Assertions.assertEquals(0, allPrompts.size)
    }

    @Test
    fun getAllPrompts_withSinglePrompt_returnsOnePrompt() {
        journalPromptRepository.deleteAll()
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "1", TEST_PROMPT_CATEGORY + "1"))
        val allPrompts = journalPromptService.getAllPrompts()
        Assertions.assertNotNull(allPrompts)
        Assertions.assertEquals(1, allPrompts.size)
        Assertions.assertEquals(1, allPrompts[0]?.id)
        Assertions.assertEquals(TEST_PROMPT_TITLE + "1", allPrompts[0]?.title)
        Assertions.assertEquals(TEST_PROMPT_CATEGORY + "1", allPrompts[0]?.category)
    }

    @Test
    fun getAllPrompts_withFivePrompt_returnsFivePrompts() {
        journalPromptRepository.deleteAll()
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "1", TEST_PROMPT_CATEGORY + "1"))
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "2", TEST_PROMPT_CATEGORY + "2"))
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "3", TEST_PROMPT_CATEGORY + "3"))
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "4", TEST_PROMPT_CATEGORY + "4"))
        journalPromptRepository.save(JournalPrompt(null, TEST_PROMPT_TITLE + "5", TEST_PROMPT_CATEGORY + "5"))
        val allPrompts = journalPromptService.getAllPrompts()
        Assertions.assertNotNull(allPrompts)
        Assertions.assertEquals(5, allPrompts.size)
    }

    @Test
    fun getPrompt() {
    }

    @Test
    fun importPrompts() {
    }

    @Test
    fun createPrompts() {
    }

    @Test
    fun getAllPromptsInCategory() {
    }

    @Test
    fun createPrompt() {
    }

    @Test
    fun getRandomPrompt() {
    }
}
