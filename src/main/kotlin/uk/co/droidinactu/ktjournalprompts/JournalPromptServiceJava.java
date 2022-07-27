package uk.co.droidinactu.ktjournalprompts;

import org.springframework.stereotype.Service;
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptImportRequest;
import uk.co.droidinactu.ktjournalprompts.controller.JournalPromptRequest;
import uk.co.droidinactu.ktjournalprompts.db.JournalPrompt;
import uk.co.droidinactu.ktjournalprompts.db.JournalPromptRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JournalPromptServiceJava {

    private JournalPromptRepository journalPromptRepository;

    public JournalPromptServiceJava(JournalPromptRepository journalPromptRepository) {
        this.journalPromptRepository = journalPromptRepository;
    }

    public List<JournalPrompt> getPrompts() {
        return StreamSupport
                .stream(journalPromptRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public JournalPrompt getRandomPrompt() {
        List<JournalPrompt> prompts = getPrompts();
        return prompts.get(new Random().nextInt(prompts.size()));
    }

    public List<String> getPromptCategories() {
        List<JournalPrompt> prompts = getPrompts();
        return prompts.stream().map(p -> p.getCategory()).collect(Collectors.toList());
    }

    public Optional<JournalPrompt> getPrompt(Long promptId) {
        List<JournalPrompt> prompts = getPrompts();
        return prompts.stream().filter(p -> p.getId() == promptId).findFirst();
    }

    public void importPrompts(List<JournalPromptImportRequest> journalPrompts) {
    }

    public void createPrompts(List<JournalPromptRequest> journalPrompts) {
    }
}
