package de.griefed.generation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

public abstract class ModloaderGeneration {

    public static final Pattern CODE_REPLACE = Pattern.compile("/\\*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/.*/\\*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/", Pattern.DOTALL);
    public static final Pattern LANG_REPLACE = Pattern.compile("\"DO\\.NOT\\.EDIT\\.MANUALLY\\.BEGIN\": \"BEGIN\".*\"DO\\.NOT\\.EDIT\\.MANUALLY\\.END\": \"END\"", Pattern.DOTALL);

    private final Project project;
    private final String modName;
    private final String subName;
    private final BlockDefinitionParser blockDefinitionParser;
    private final ObjectMapper objectMapper;

    private final String group;
    private final File groupDirectory;
    private final String modloader;
    private final String id;
    private final File modBlocksClass;
    private final File modItemsClass;
    private final File blocksGroup;
    private final File itemGroup;
    private final File modloaderClass;
    private final File assetsDirectory;
    private final File translationsFile;

    protected ModloaderGeneration(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        this.project = project;
        this.blockDefinitionParser = parser;
        this.modName = modName;
        this.subName = modName + project.getName().substring(0,1).toUpperCase() + project.getName().substring(1);
        this.objectMapper = objectMapper;

        this.group = project.getGroup().toString();
        this.groupDirectory = new File(project.getProjectDir(), "src/main/java/" + group.replace(".", "/"));
        this.modloader = project.getName();
        this.id = group.substring(group.lastIndexOf(".") + 1);
        this.blocksGroup = new File(groupDirectory, "block");
        this.modBlocksClass = new File(blocksGroup, "ModBlocks.java");
        this.itemGroup = new File(groupDirectory, "item");
        this.modItemsClass = new File(itemGroup, "ModItems.java");
        this.modloaderClass = new File(groupDirectory, subName + ".java");
        this.assetsDirectory = new File(project.getProjectDir(),"src/main/resources/assets/" + id);
        this.translationsFile = new File(assetsDirectory, "lang/en_us.json");
    }

    /**
     * Create the <code>your.group.modid.block</code>-package
     */
    protected void createModBlockPackage() {
        if (!blocksGroup.exists() && blocksGroup.mkdirs()) {
            System.out.println("Group-directory created " + blocksGroup.getAbsolutePath());
        }
    }

    /**
     * Create the <code>your.group.modid.item</code>-package
     */
    protected void createItemPackage() {
        if (!itemGroup.exists() && itemGroup.mkdirs()) {
            System.out.println("Item-directory created: " + itemGroup.getAbsolutePath());
        }
    }

    public void writeToFile(File file, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
        }
    }

    public String readFromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        return sb.toString();

    }

    /**
     * Create all translations required for our blocks and items to work.
     * @throws IOException when the file could not be created or edited.
     */
    protected void updateTranslations() throws IOException {
        if (!translationsFile.exists() && translationsFile.getParentFile().mkdirs() && translationsFile.createNewFile()) {
            writeToFile(
                    translationsFile,
                    StringTemplates.TRANSLATION.replace("GENERATED_TRANSLATION_CODE",buildTranslationText())
            );
        } else {
            String translations = readFromFile(getTranslationsFile());
            if (translations.contains("\"DO.NOT.EDIT.MANUALLY.BEGIN\": \"BEGIN\",") && translations.contains("\"DO.NOT.EDIT.MANUALLY.END\": \"END\"")) {
                //translations file from our generation
                translations = LANG_REPLACE
                        .matcher(translations)
                        .replaceAll("\"DO.NOT.EDIT.MANUALLY.BEGIN\": \"BEGIN\",\n" +
                                        buildTranslationText() + "\n" +
                                "\"DO.NOT.EDIT.MANUALLY.END\": \"END\"");
                writeToFile(this.translationsFile,translations);
            } else {
                //translations not from our generation, add our block
                ObjectNode translationNode = (ObjectNode) objectMapper.readTree(this.translationsFile);
                String itemPrefix = "\"item." + id + ".";
                String blockPrefix = "\"block." + id + ".";
                translationNode.put("DO.NOT.EDIT.MANUALLY.BEGIN","BEGIN");
                for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
                    translationNode.put(itemPrefix + block.getId() + "_block", block.getTranslation());
                    translationNode.put(blockPrefix + block.getId() + "_block", block.getTranslation());
                }
                translationNode.put("DO.NOT.EDIT.MANUALLY.END", "END");
                objectMapper.writeValue(this.translationsFile, translationNode);
            }
        }
    }

    private String buildTranslationText() {
        StringBuilder translations = new StringBuilder();
        String itemPrefix = "\"item." + id + ".";
        String blockPrefix = "\"block." + id + ".";
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //add item
            //key
            translations.append("\n").append(itemPrefix).append(block.getId()).append("_block").append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",\n");
            //add block
            //key
            translations.append("\n").append(blockPrefix).append(block.getId()).append("_block").append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",\n");
        }
        return translations.toString();
    }

    /**
     * Create all files required for the added blocks to work.<br>
     * @throws IOException when the file could not be created or edited.
     */
    public void createBlockFiles() throws IOException {
        File blockstatesDir = new File(assetsDirectory, "blockstates");
        File blockModelsDir = new File(assetsDirectory, "models/block");
        File itemModelsDir = new File(assetsDirectory, "models/item");
        File itemTexturesDir = new File(assetsDirectory, "textures/item");
        File blockTexturesDir = new File(assetsDirectory, "textures/block");
        blockstatesDir.mkdirs();
        blockModelsDir.mkdirs();
        itemModelsDir.mkdirs();
        itemTexturesDir.mkdirs();
        blockTexturesDir.mkdirs();
        String blockstatesTemp = "BLOCKID_block.json";
        String blockModelTemp = "BLOCKID_block.json";
        String itemBlockModelTemp = "BLOCKID_block.json";
        String blockTextureTemp = "BLOCKID_block.png";
        File blockstate;
        File blockModel;
        File itemBlockModel;
        File blockTexture;
        File textureSource;
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //blockstate
            blockstate = new File(blockstatesDir, blockstatesTemp.replace("BLOCKID", block.getId()));
            writeToFile(blockstate, StringTemplates.BLOCKSTATE.replace("MODID", id).replace("BLOCKID", block.getId()));
            //block model
            blockModel = new File(blockModelsDir, blockModelTemp.replace("BLOCKID", block.getId()));
            writeToFile(blockModel, StringTemplates.BLOCK_MODELS.replace("MODID", id).replace("BLOCKID", block.getId()));
            //item block model
            itemBlockModel = new File(itemModelsDir, itemBlockModelTemp.replace("BLOCKID", block.getId()));
            writeToFile(itemBlockModel, StringTemplates.BLOCK_MODELS_ITEM.replace("MODID", id).replace("BLOCKID", block.getId()));
            //block texture
            blockTexture = new File(blockTexturesDir, blockTextureTemp.replace("BLOCKID", block.getId()));
            textureSource = new File(blockDefinitionParser.getAssetsDirectory(), block.getId() + ".png");
            Files.copy(textureSource.toPath(), blockTexture.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Create all files required for the added items to work.<br>
     * Depending on the modloader, this creates various json-files inside the <code>resources/assets</code>-directory.
     * @throws IOException when the file could not be created or edited.
     */
    public void createItemFiles() throws IOException {
        /*File itemModelsDir = new File(getAssetsDirectory(), "models/item");
        String itemModelTemp = "BLOCKID.json";
        File itemModel;
        for (ItemDefinition item : getItemDefinitionParser().getItems()) {
            //item model
            itemModel = new File(itemModelsDir, itemModelTemp.replace("BLOCKID", block.getId()));
            writeToFile(itemModel, StringTemplates.FORGE_MODELS_ITEM.replace("MODID", getId()).replace("BLOCKID", block.getId()));
        }*/
    }

    public Project getProject() {
        return project;
    }

    public String getModName() {
        return modName;
    }

    public String getSubName() {
        return subName;
    }

    public BlockDefinitionParser getBlockDefinitionParser() {
        return blockDefinitionParser;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getGroup() {
        return group;
    }

    public File getGroupDirectory() {
        return groupDirectory;
    }

    public String getModloader() {
        return modloader;
    }

    public String getId() {
        return id;
    }

    public File getModBlocksClass() {
        return modBlocksClass;
    }

    public File getModItemsClass() {
        return modItemsClass;
    }

    public File getBlocksGroup() {
        return blocksGroup;
    }

    public File getItemGroup() {
        return itemGroup;
    }

    public File getModloaderClass() {
        return modloaderClass;
    }

    public File getAssetsDirectory() { return assetsDirectory; }

    public File getTranslationsFile() {
        return translationsFile;
    }
}
