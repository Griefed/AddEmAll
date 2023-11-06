package de.griefed.generation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.IOException;

public class CommonGeneration extends ModloaderGeneration {
    protected CommonGeneration(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        super(project, modName, parser, objectMapper);
    }

    public void run() throws IOException {
        updateTranslations();
        createBlockFiles();
        createItemFiles();
    }
}
