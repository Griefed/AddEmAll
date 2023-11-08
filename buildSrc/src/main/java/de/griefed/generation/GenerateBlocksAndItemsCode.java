package de.griefed.generation;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinitionParser;
import de.griefed.generation.generator.CommonSubProjectGenerator;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GenerateBlocksAndItemsCode extends DefaultTask {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature());

    @TaskAction
    public void generateCode() {
        try {
            Properties gradleProperties = new Properties();
            gradleProperties.load(new FileInputStream(new File(getProject().getRootDir(), "gradle.properties")));
            String modName = gradleProperties.getProperty("mod_name");

            BlockDefinitionParser parser = new BlockDefinitionParser(getProject(), objectMapper);
            CommonSubProjectGenerator common = new CommonSubProjectGenerator(getProject().findProject("Common"), modName, parser, objectMapper);
            common.run();
        } catch (IOException e) {
            throw new RuntimeException("Error generating block and item code.", e);
        }
    }
}
