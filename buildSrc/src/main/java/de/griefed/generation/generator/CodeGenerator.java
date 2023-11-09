package de.griefed.generation.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static final String BLOCKSTATES_FILETEMPLATE = "generated/%s/%s_block.json";
    public static final String BLOCKSTATES_SLAB_FILETEMPLATE = "generated/%s/%s_slab.json";
    public static final String BLOCKSTATES_STAIRS_FILETEMPLATE = "generated/%s/%s_stairs.json";

    public static final String BLOCK_MODEL_FILETEMPLATE = "generated/%s/%s_block.json";
    public static final String BLOCK_MODEL_SLAB_FILETEMPLATE = "generated/%s/%s_slab.json";
    public static final String BLOCK_MODEL_SLAB_TOP_FILETEMPLATE = "generated/%s/%s_slab_top.json";
    public static final String BLOCK_MODEL_STAIRS_FILETEMPLATE = "generated/%s/%s_stairs.json";
    public static final String BLOCK_MODEL_STAIRS_INNER_FILETEMPLATE = "generated/%s/%s_stairs_inner.json";
    public static final String BLOCK_MODEL_STAIRS_OUTER_FILETEMPLATE = "generated/%s/%s_stairs_outer.json";

    public static final String ITEM_BLOCK_MODEL_FILETEMPLATE = "generated/%s/%s.json";
    public static final String ITEM_BLOCK_MODEL_SLAB_FILETEMPLATE = "generated/%s/%s_slab.json";
    public static final String ITEM_BLOCK_MODEL_STAIRS_FILETEMPLATE = "generated/%s/%s_stairs.json";

    public static final String BLOCK_TEXTURE_FILETEMPLATE = "generated/%s/%s_block.png";

    public static final String ITEM_TRANSLATION_KEY_TEMPLATE = "item.%s.generated.%s.%s_block";
    public static final String ITEM_SLAB_TRANSLATION_KEY_TEMPLATE = "item.%s.generated.%s.%s_slab";
    public static final String ITEM_STAIRS_TRANSLATION_KEY_TEMPLATE = "item.%s.generated.%s.%s_stairs";

    public static final String BLOCK_TRANSLATION_KEY_TEMPLATE = "block.%s.generated.%s.%s_block";
    public static final String BLOCK_SLAB_TRANSLATION_KEY_TEMPLATE = "block.%s.generated.%s.%s_slab";
    public static final String BLOCK_STAIRS_TRANSLATION_KEY_TEMPLATE = "block.%s.generated.%s.%s_stairs";
    public static final String ANIMATION_CONTENT_TEMPLATE = """
            {
              "animation": {}
            }
            """;
    private static final String BLOCKSTATE_CONTENT_TEMPLATE = """
            {
              "variants": {
                "": { "model": "%s:block/generated/%s/%s_block" }
              }
            }
            """;
    private static final String BLOCKSTATE_SLAB_CONTENT_TEMPLATE = """
            {
              "variants": {
                "type=bottom": {
                  "model": "%s:block/generated/%s/%s_slab"
                },
                "type=double": {
                  "model": "%s:block/generated/%s/%s_block"
                },
                "type=top": {
                  "model": "%s:block/generated/%s/%s_slab_top"
                }
              }
            }
            """;
    private static final String BLOCKSTATE_STAIRS_CONTENT_TEMPLATE = """
            {
              "variants": {
                "facing=east,half=bottom,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 270,
                  "uvlock": true
                },
                "facing=east,half=bottom,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner"
                },
                "facing=east,half=bottom,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 270,
                  "uvlock": true
                },
                "facing=east,half=bottom,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer"
                },
                "facing=east,half=bottom,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs"
                },
                "facing=east,half=top,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "uvlock": true
                },
                "facing=east,half=top,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 90,
                  "uvlock": true
                },
                "facing=east,half=top,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "uvlock": true
                },
                "facing=east,half=top,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 90,
                  "uvlock": true
                },
                "facing=east,half=top,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "x": 180,
                  "uvlock": true
                },
                "facing=north,half=bottom,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 180,
                  "uvlock": true
                },
                "facing=north,half=bottom,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 270,
                  "uvlock": true
                },
                "facing=north,half=bottom,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 180,
                  "uvlock": true
                },
                "facing=north,half=bottom,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 270,
                  "uvlock": true
                },
                "facing=north,half=bottom,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "y": 270,
                  "uvlock": true
                },
                "facing=north,half=top,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 270,
                  "uvlock": true
                },
                "facing=north,half=top,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "uvlock": true
                },
                "facing=north,half=top,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 270,
                  "uvlock": true
                },
                "facing=north,half=top,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "uvlock": true
                },
                "facing=north,half=top,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "x": 180,
                  "y": 270,
                  "uvlock": true
                },
                "facing=south,half=bottom,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner"
                },
                "facing=south,half=bottom,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 90,
                  "uvlock": true
                },
                "facing=south,half=bottom,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer"
                },
                "facing=south,half=bottom,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 90,
                  "uvlock": true
                },
                "facing=south,half=bottom,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "y": 90,
                  "uvlock": true
                },
                "facing=south,half=top,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 90,
                  "uvlock": true
                },
                "facing=south,half=top,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 180,
                  "uvlock": true
                },
                "facing=south,half=top,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 90,
                  "uvlock": true
                },
                "facing=south,half=top,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 180,
                  "uvlock": true
                },
                "facing=south,half=top,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "x": 180,
                  "y": 90,
                  "uvlock": true
                },
                "facing=west,half=bottom,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 90,
                  "uvlock": true
                },
                "facing=west,half=bottom,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "y": 180,
                  "uvlock": true
                },
                "facing=west,half=bottom,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 90,
                  "uvlock": true
                },
                "facing=west,half=bottom,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "y": 180,
                  "uvlock": true
                },
                "facing=west,half=bottom,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "y": 180,
                  "uvlock": true
                },
                "facing=west,half=top,shape=inner_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 180,
                  "uvlock": true
                },
                "facing=west,half=top,shape=inner_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_inner",
                  "x": 180,
                  "y": 270,
                  "uvlock": true
                },
                "facing=west,half=top,shape=outer_left": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 180,
                  "uvlock": true
                },
                "facing=west,half=top,shape=outer_right": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs_outer",
                  "x": 180,
                  "y": 270,
                  "uvlock": true
                },
                "facing=west,half=top,shape=straight": {
                  "model": "MODID:block/generated/MATERIAL/BLOCKID_stairs",
                  "x": 180,
                  "y": 180,
                  "uvlock": true
                }
              }
            }
            """;
    private static final String BLOCK_MODELS_CONTENT_TEMPLATE = """
            {
              "parent": "block/cube_all",
              "textures": {
                "all": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_SLAB_CONTENT_TEMPLATE = """
            {
              "parent": "minecraft:block/slab",
              "textures": {
                "bottom": "%s:block/generated/%s/%s_block",
                "top": "%s:block/generated/%s/%s_block",
                "side": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_STAIRS_TEMPLATE = """
            {
              "parent": "minecraft:block/stairs",
              "textures": {
                "bottom": "%s:block/generated/%s/%s_block",
                "top": "%s:block/generated/%s/%s_block",
                "side": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_STAIRS_INNER_TEMPLATE = """
            {
              "parent": "minecraft:block/inner_stairs",
              "textures": {
                "bottom": "%s:block/generated/%s/%s_block",
                "top": "%s:block/generated/%s/%s_block",
                "side": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_STAIRS_OUTER_TEMPLATE = """
            {
              "parent": "minecraft:block/outer_stairs",
              "textures": {
                "bottom": "%s:block/generated/%s/%s_block",
                "top": "%s:block/generated/%s/%s_block",
                "side": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_SLAB_TOP_CONTENT_TEMPLATE = """
            {
              "parent": "minecraft:block/slab_top",
              "textures": {
                "bottom": "%s:block/generated/%s/%s_block",
                "top": "%s:block/generated/%s/%s_block",
                "side": "%s:block/generated/%s/%s_block"
              }
            }
            """;
    private static final String BLOCK_MODELS_ITEM_CONTENT_TEMPLATE = """
            {
              "parent": "%s:block/generated/%s/%s_block"
            }
            """;
    private static final String BLOCK_MODELS_SLAB_ITEM_CONTENT_TEMPLATE = """
            {
              "parent": "%s:block/generated/%s/%s_slab"
            }
            """;
    private static final String BLOCK_MODELS_STAIRS_ITEM_CONTENT_TEMPLATE = """
            {
              "parent": "%s:block/generated/%s/%s_stairs"
            }
            """;
    private static final String TRANSLATION_CONTENT_TEMPLATE = """
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
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
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
                    TRANSLATION_CONTENT_TEMPLATE.replace("GENERATED_TRANSLATION_CODE", buildTranslationText())
            );
        } else {
            String translations = readFromFile(getTranslationsFile());
            //translations file from our generation
            translations = LANG_REPLACE
                    .matcher(translations)
                    .replaceAll("\"DO.NOT.EDIT.MANUALLY.BEGIN\": \"BEGIN\"," +
                            buildTranslationText() +
                            "  \"DO.NOT.EDIT.MANUALLY.END\": \"END\"");
            writeToFile(this.translationsFile, translations);
        }
    }

    private StringBuilder buildTranslationText() {
        StringBuilder translations = new StringBuilder();
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //add item
            //key
            translations.append("\n  \"").append(String.format(ITEM_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",");
            //add block
            //key
            translations.append("\n  \"").append(String.format(BLOCK_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
            //value
            translations.append(" \"").append(block.getTranslation()).append("\",\n");
            if (block.generateSlab()) {
                //add item
                //key
                translations.append("\n  \"").append(String.format(ITEM_SLAB_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
                //value
                translations.append(" \"").append(block.getTranslation()).append(" Slab").append("\",");
                //add block
                //key
                translations.append("\n  \"").append(String.format(BLOCK_SLAB_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
                //value
                translations.append(" \"").append(block.getTranslation()).append(" Slab").append("\",\n");
            }
            if (block.generateStair()) {
                //add item
                //key
                translations.append("\n  \"").append(String.format(ITEM_STAIRS_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
                //value
                translations.append(" \"").append(block.getTranslation()).append(" Stairs").append("\",");
                //add block
                //key
                translations.append("\n  \"").append(String.format(BLOCK_STAIRS_TRANSLATION_KEY_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId())).append("\":");
                //value
                translations.append(" \"").append(block.getTranslation()).append(" Stairs").append("\",\n");
            }
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
        File blockstate;
        File blockModel;
        File itemBlockModel;
        File blockTexture;
        File textureSource;
        File textureMCMeta;
        BufferedImage texture;
        for (BlockDefinition block : blockDefinitionParser.getBlocks()) {
            //blockstate
            blockstate = new File(blockstatesDir, String.format(BLOCKSTATES_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
            blockstate.getParentFile().mkdirs();
            writeToFile(blockstate, String.format(BLOCKSTATE_CONTENT_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //block model
            blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
            blockModel.getParentFile().mkdirs();
            writeToFile(blockModel, String.format(BLOCK_MODELS_CONTENT_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //item block model
            itemBlockModel = new File(itemModelsDir, String.format(ITEM_BLOCK_MODEL_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
            itemBlockModel.getParentFile().mkdirs();
            writeToFile(itemBlockModel, String.format(BLOCK_MODELS_ITEM_CONTENT_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));

            //block texture
            blockTexture = new File(blockTexturesDir, String.format(BLOCK_TEXTURE_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
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
                        ANIMATION_CONTENT_TEMPLATE);
            }
            Files.copy(textureSource.toPath(), blockTexture.toPath(), StandardCopyOption.REPLACE_EXISTING);

            if (block.generateSlab()) {
                //blockstate slab
                blockstate = new File(blockstatesDir, String.format(BLOCKSTATES_SLAB_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockstate, String.format(BLOCKSTATE_SLAB_CONTENT_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //block model slab
                blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_SLAB_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockModel, String.format(BLOCK_MODELS_SLAB_CONTENT_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //block model slab top
                blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_SLAB_TOP_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockModel, String.format(BLOCK_MODELS_SLAB_TOP_CONTENT_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //item block model slab
                itemBlockModel = new File(itemModelsDir, String.format(ITEM_BLOCK_MODEL_SLAB_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(itemBlockModel, String.format(BLOCK_MODELS_SLAB_ITEM_CONTENT_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));
            }
            if (block.generateStair()) {
                //blockstate stairs
                blockstate = new File(blockstatesDir, String.format(BLOCKSTATES_STAIRS_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockstate, BLOCKSTATE_STAIRS_CONTENT_TEMPLATE
                        .replace("MODID", id)
                        .replace("MATERIAL", block.getMaterial().toLowerCase())
                        .replace("BLOCKID", block.getId()));
                //block model stairs
                blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_STAIRS_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockModel, String.format(BLOCK_MODELS_STAIRS_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //block model stairs inner
                blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_STAIRS_INNER_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockModel, String.format(BLOCK_MODELS_STAIRS_INNER_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //block model stairs outer
                blockModel = new File(blockModelsDir, String.format(BLOCK_MODEL_STAIRS_OUTER_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(blockModel, String.format(BLOCK_MODELS_STAIRS_OUTER_TEMPLATE,
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId(),
                        id, block.getMaterial().toLowerCase(), block.getId()));
                //item block model slab
                itemBlockModel = new File(itemModelsDir, String.format(ITEM_BLOCK_MODEL_STAIRS_FILETEMPLATE, block.getMaterial().toLowerCase(), block.getId()));
                writeToFile(itemBlockModel, String.format(BLOCK_MODELS_STAIRS_ITEM_CONTENT_TEMPLATE, id, block.getMaterial().toLowerCase(), block.getId()));
            }
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
