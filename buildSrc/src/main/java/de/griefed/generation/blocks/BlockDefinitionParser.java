package de.griefed.generation.blocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;

public class BlockDefinitionParser {

    private final BlockDefinition[] definitions;
    private final File assetsDirectory;

    public BlockDefinitionParser(Project project, ObjectMapper objectMapper) throws IOException {
        this.definitions = objectMapper.readValue(new File(project.getRootDir(), "definitions/blocks.json"), BlockDefinition[].class);
        this.assetsDirectory = new File(project.getRootDir(),"definitions/assets/blocks");
    }

    public BlockDefinition[] getBlocks() {
        return definitions;
    }

    public File getAssetsDirectory() { return assetsDirectory; }
}
