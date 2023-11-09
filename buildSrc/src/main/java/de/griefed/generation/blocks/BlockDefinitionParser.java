package de.griefed.generation.blocks;

import com.google.gson.Gson;
import org.gradle.api.Project;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class BlockDefinitionParser {

    private final BlockDefinition[] blocks;
    private final File assetsDirectory;

    public BlockDefinitionParser(Project project) throws Exception {
        this.assetsDirectory = new File(project.getRootDir(), "definitions/assets/blocks");
        Gson gson = new Gson();
        Reader reader = new FileReader(new File(project.getRootDir(), "definitions/blocks.json"));
        this.blocks = gson.fromJson(reader, BlockDefinition[].class);
    }

    public BlockDefinition[] getBlocks() {
        return blocks;
    }

    public File getAssetsDirectory() {
        return assetsDirectory;
    }
}
