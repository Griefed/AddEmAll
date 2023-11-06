package de.griefed.generation;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Generates Forge, Fabric and Quilt block-implementations from block-definitions in a JSON-file.<br>
 * See <code>definitions/blocks.json</code><br>
 * Take note that textures for blocks must be present in <code>definitions/blocks</code> and the name of the textures
 * must match the ID of the block.
 */
public class BlockAndItemCodeGenerator implements Plugin<Project> {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature());

    public void apply(Project project) {
        project.getTasks().register("blockAndItemCodeGen", task -> task.doLast(s -> System.out.println("Hello from plugin " + this.getClass().getName())));
        try {
            Properties gradleProperties = new Properties();
            gradleProperties.load(new FileInputStream(new File(project.getRootDir(),"gradle.properties")));
            String modName = gradleProperties.getProperty("mod_name");
            BlockDefinitionParser parser = new BlockDefinitionParser(project, objectMapper);

            CommonGeneration common = new CommonGeneration(project.findProject("common"), modName, parser, objectMapper);
            FabricGeneration fabric = new FabricGeneration(project.findProject("fabric"), modName, parser, objectMapper);
            ForgeGeneration forge = new ForgeGeneration(project.findProject("forge"), modName, parser, objectMapper);

            common.run();
            fabric.run();
            forge.run();
        } catch (IOException e) {
            throw new RuntimeException("Error generating block and item code.", e);
        }
    }
}
