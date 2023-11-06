package de.griefed.generation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.IOException;

public class ForgeGeneration extends ModloaderGeneration implements CodeGeneration {
    public ForgeGeneration(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        super(project, modName, parser, objectMapper);
    }

    private final String modBlockClassTemplate = StringTemplates.FORGE_MOD_BLOCK_CLASS
            .replace("GROUP", getGroup())
            .replace("MODLOADERCLASS", getModloaderClass().getName().replace(".java", ""));

    private final String modItemsClassTemplate = StringTemplates.FORGE_MOD_ITEMS_CLASS
            .replace("GROUP", getGroup())
            .replace("MODLOADERCLASS", getModloaderClass().getName().replace(".java", ""));

    private final String modloaderClassTemplate = StringTemplates.FORGE_MODLOADER_CLASS
            .replace("GROUP", getGroup())
            .replace("MODNAME", getModName())
            .replace("SUBNAME", getSubName())
            .replace("MODID", getId());

    @Override
    public void run() throws IOException {
        createModBlockPackage();
        createItemPackage();
        createModBlocksClass();
        createModItemsClass();
        createModloaderClass();
        updateModBlocksClass();
        updateModItemsClass();
        updateModloaderMain();
    }

    @Override
    public void createModBlocksClass() throws IOException {
        if (!getModBlocksClass().exists() && getModBlocksClass().createNewFile()) {
            System.out.println("ModBlock-class created: " + getModBlocksClass().getAbsolutePath());
            writeToFile(getModBlocksClass(), modBlockClassTemplate);
        }
    }

    @Override
    public void createModItemsClass() throws IOException {
        if (!getModItemsClass().exists() && getModItemsClass().createNewFile()) {
            System.out.println("ModItems-class created: " + getModItemsClass().getAbsolutePath());
            writeToFile(getModItemsClass(), modItemsClassTemplate);
        }
    }

    @Override
    public void createModloaderClass() throws IOException {
        if (!getModloaderClass().exists() && getModloaderClass().createNewFile()) {
            System.out.println("ModItems-class created: " + getModloaderClass().getAbsolutePath());
            writeToFile(getModloaderClass(), modloaderClassTemplate);
        }
    }

    @Override
    public void updateModBlocksClass() throws IOException {
        String modBlocks = readFromFile(getModBlocksClass());
        StringBuilder blocks = new StringBuilder();
        StringBuilder registration;
        for (BlockDefinition block : getBlockDefinitionParser().getBlocks()) {
            registration = new StringBuilder();
            registration.append("\n\tpublic static final RegistryObject<Block> ").append(block.getId().toUpperCase()).append("_BLOCK = registerBlock(\"").append(block.getId()).append("_block\",\n")
                    .append("\t\t\t() -> new Block(BlockBehaviour.Properties.of(Material.").append(block.getMaterial()).append(")\n")
                    .append("\t\t\t\t\t.strength(").append(block.getStrength()).append("f).lightLevel(state -> ").append(block.getLightLevel()).append(")").append(".explosionResistance(").append(block.getExplosionResistance()).append("f)\n\t\t\t\t\t");
            if (block.isRequiresCorrectTool()) {
                registration.append(".requiresCorrectToolForDrops()");
            }
            if (block.isInstabreak()) {
                registration.append(".instabreak()");
            }
            registration.append("), CreativeModeTab.TAB_BUILDING_BLOCKS);\n");
            blocks.append(registration);
        }
        modBlocks = CODE_REPLACE
                .matcher(modBlocks)
                .replaceAll("/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/" +
                        blocks +
                        "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        writeToFile(getModBlocksClass(), modBlocks);
    }

    @Override
    public void updateModItemsClass() throws IOException {
        String modItems = readFromFile(getModItemsClass());
        StringBuilder items = new StringBuilder();
        StringBuilder registration;
        /*for (ItemDefinition item : getItemDefinitionParser().getItems()) {
            registration = new StringBuilder();
            registration.append("\n\tpublic static final RegistryObject<Item> ").append(item.getId().toUpperCase()).append(" = ITEMS.register(\"").append(item.getId()).append("\",\n")
                    .append("\t\t\t() -> new Item(new Item.Properties()));");
            registration.append("\n");
            items.append(registration);
        }*/
        modItems = CODE_REPLACE
                .matcher(modItems)
                .replaceAll("/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/\n\n" +
                        items +
                        "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        writeToFile(getModItemsClass(), modItems);
    }

    @Override
    public void updateModloaderMain() throws IOException {
        String modloaderMain = readFromFile(getModloaderClass());
        modloaderMain = CODE_REPLACE
                .matcher(modloaderMain)
                .replaceAll("""
                        /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        \t\tModItems.register(modEventBus);
                        \t\tModBlocks.register(modEventBus);
                        \t\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/""");
        writeToFile(getModloaderClass(), modloaderMain);
    }
}
