package de.griefed.generation.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import de.griefed.generation.blocks.TextureScaler;
import org.gradle.api.Project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public abstract class CodeGenerator {

    public static final Pattern GENERATED_CODE_PATTERN = Pattern.compile("/\\*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/.*/\\*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/", Pattern.DOTALL);
    public static final Pattern LANG_REPLACE = Pattern.compile("\"DO\\.NOT\\.EDIT\\.MANUALLY\\.BEGIN\": \"BEGIN\".*\"DO\\.NOT\\.EDIT\\.MANUALLY\\.END\": \"END\"", Pattern.DOTALL);

    private static final String BLOCKSTATE_TEMPLATE = """
            {
              "variants": {
                "": { "model": "%s:block/generated/%s/%s_block" }
              }
            }
            """;

    private static final String BLOCK_MODELS_TEMPLATE = """
            {
              "parent": "block/cube_all",
              "textures": {
                "all": "%s:block/generated/%s/%s_block"
              }
            }
            """;

    private static final String BLOCK_MODELS_ITEM_TEMPLATE = """
            {
              "parent": "%s:block/generated/%s/%s_block"
            }
            """;

    private static final String TRANSLATION_TEMPLATE = """
            {
              "DO.NOT.EDIT.MANUALLY.BEGIN": "BEGIN",
              GENERATED_TRANSLATION_CODE
              "DO.NOT.EDIT.MANUALLY.END": "END"
            }
            """;

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

    protected CodeGenerator(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        this.project = project;
        this.blockDefinitionParser = parser;
        this.modName = modName;
        this.subName = modName + project.getName().substring(0, 1).toUpperCase() + project.getName().substring(1);
        this.objectMapper = objectMapper;

        this.group = project.getGroup().toString();
        this.groupDirectory = new File(project.getProjectDir(), "src/main/java/" + group.replace(".", "/"));
        this.modloader = project.getName();
        this.id = group.substring(group.lastIndexOf(".") + 1);
        this.blocksGroup = new File(groupDirectory, "block");
        this.modBlocksClass = new File(blocksGroup, "GeneratedModBlocks.java");
        this.itemGroup = new File(groupDirectory, "item");
        this.modItemsClass = new File(itemGroup, "GeneratedModItems.java");
        if (project.getName().equalsIgnoreCase("common")) {
            this.modloaderClass = new File(groupDirectory, "CommonClass.java");
        } else {
            this.modloaderClass = new File(groupDirectory, subName + ".java");
        }
        this.assetsDirectory = new File(project.getProjectDir(), "src/main/resources/assets/" + id);
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
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
     *
     * @throws IOException when the file could not be created or edited.
     */
    protected void updateTranslations() throws IOException {
        if (!translationsFile.exists() && translationsFile.getParentFile().mkdirs() && translationsFile.createNewFile()) {
            writeToFile(
                    translationsFile,
                    TRANSLATION_TEMPLATE.replace("GENERATED_TRANSLATION_CODE", buildTranslationText())
            );
        } else {
            String translations = readFromFile(getTranslationsFile());
            if (translations.contains("\"DO.NOT.EDIT.MANUALLY.BEGIN\": \"BEGIN\",") && translations.contains("\"DO.NOT.EDIT.MANUALLY.END\": \"END\"")) {
                //translations file from our generation
                translations = LANG_REPLACE
                        .matcher(translations)
                        .replaceAll("\"DO.NOT.EDIT.MANUALLY.BEGIN\": \"BEGIN\"," +
                                buildTranslationText() +
                                "  \"DO.NOT.EDIT.MANUALLY.END\": \"END\"");
                writeToFile(this.translationsFile, translations);
            } else {
                //translations not from our generation, add our block
                ObjectNode translationNode = (ObjectNode) objectMapper.readTree(this.translationsFile);
                String itemPrefix = "\"item.%s.generated.%s.%s_block";
                String blockPrefix = "\"block.%s.generated.%s.%s_block\"";
                translationNode.put("DO.NOT.EDIT.MANUALLY.BEGIN", "BEGIN");
                for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
                    translationNode.put(
                            String.format(itemPrefix, id, block.getMaterial().toLowerCase(), block.getId()),
                            block.getTranslation());
                    translationNode.put(
                            String.format(blockPrefix, id, block.getMaterial().toLowerCase(), block.getId()),
                            block.getTranslation());
                }
                translationNode.put("DO.NOT.EDIT.MANUALLY.END", "END");
                objectMapper.writeValue(this.translationsFile, translationNode);
            }
        }
    }

    private StringBuilder buildTranslationText() {
        StringBuilder translations = new StringBuilder();
        String itemPrefix = "\"item.%s.generated.%s.%s_block";
        String blockPrefix = "\"block.%s.generated.%s.%s_block";
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //add item
            //key
            translations.append("\n  ").append(String.format(itemPrefix, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",");
            //add block
            //key
            translations.append("\n  ").append(String.format(blockPrefix, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",\n");
        }
        return translations;
    }

    /**
     * Create all files required for the added blocks to work.<br>
     *
     * @throws IOException when the file could not be created or edited.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
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
        String blockstatesTemp = "generated/%s/%s_block.json";
        String blockModelTemp = "generated/%s/%s_block.json";
        String itemBlockModelTemp = "generated/%s/%s.json";
        String blockTextureTemp = "generated/%s/%s_block.png";
        File blockstate;
        File blockModel;
        File itemBlockModel;
        File blockTexture;
        File textureSource;
        File textureMCMeta;
        BufferedImage texture;
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //blockstate
            blockstate = new File(blockstatesDir, String.format(blockstatesTemp, block.getMaterial().toLowerCase(), block.getId()));
            blockstate.getParentFile().mkdirs();
            writeToFile(blockstate, String.format(BLOCKSTATE_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //block model
            blockModel = new File(blockModelsDir, String.format(blockModelTemp, block.getMaterial().toLowerCase(), block.getId()));
            blockModel.getParentFile().mkdirs();
            writeToFile(blockModel, String.format(BLOCK_MODELS_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //item block model
            itemBlockModel = new File(itemModelsDir, String.format(itemBlockModelTemp, block.getMaterial().toLowerCase(), block.getId()));
            itemBlockModel.getParentFile().mkdirs();
            writeToFile(itemBlockModel, String.format(BLOCK_MODELS_ITEM_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //block texture
            blockTexture = new File(blockTexturesDir, String.format(blockTextureTemp, block.getMaterial().toLowerCase(), block.getId()));
            blockTexture.getParentFile().mkdirs();
            textureSource = new File(blockDefinitionParser.getAssetsDirectory(), block.getId() + ".png");
            texture = ImageIO.read(textureSource);
            if (texture.getWidth() > 16 && texture.getHeight() > 16) {
                texture = TextureScaler.getScaledInstance(texture);
                ImageIO.write(texture, "png", textureSource);
            }
            if (texture.getWidth() < texture.getHeight()) {
                textureMCMeta = new File(blockTexture.getParent(), blockTexture.getName() + ".mcmeta");
                textureMCMeta.createNewFile();
                writeToFile(textureMCMeta,
                        """
                                {
                                  "animation": {}
                                }
                                """);
            }
            Files.copy(textureSource.toPath(), blockTexture.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Create all files required for the added items to work.<br>
     * Depending on the modloader, this creates various json-files inside the <code>resources/assets</code>-directory.
     *
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

    public File getAssetsDirectory() {
        return assetsDirectory;
    }

    public File getTranslationsFile() {
        return translationsFile;
    }
}
