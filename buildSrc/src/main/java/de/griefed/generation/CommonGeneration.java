package de.griefed.generation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.IOException;

public class CommonGeneration extends ModloaderGeneration implements CodeGeneration {
    protected CommonGeneration(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        super(project, modName, parser, objectMapper);
    }

    private final String modBlockClassTemplate = StringTemplates.COMMON_MODBLOCKS_CLASS
            .replace("GROUP", getGroup());

    private final String modItemsClassTemplate = StringTemplates.COMMON_ITEMS_CLASS
            .replace("GROUP", getGroup());

    private final String modloaderClassTemplate = StringTemplates.COMMON_MAIN_CLASS
            .replace("GROUP", getGroup())
            .replace("MODID", getId());

    public void run() throws IOException {
        createModBlockPackage();
        createItemPackage();
        createModBlocksClass();
        createModItemsClass();
        createModloaderClass();
        updateTranslations();
        createBlockFiles();
        createItemFiles();
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

    //public static final RegistryObject<Block> SILVERBLOCK = BLOCKS.register("silverblock", () -> new Block(
    //                        BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.STONE).strength(12f, 10f).requiresCorrectToolForDrops()));
    //public static final RegistryObject<Item> SILVERBLOCK_ITEM = ITEMS.register("silverblock", () -> new BlockItem(SILVERBLOCK.get(), itemBuilder()));
    @Override
    public void updateModBlocksClass() throws IOException {
        String modBlocks = readFromFile(getModBlocksClass());
        StringBuilder blocks = new StringBuilder();
        StringBuilder registration;
        for (BlockDefinition block : getBlockDefinitionParser().getBlocks()) {
            registration = new StringBuilder();
            //block
            registration.append("\n\tpublic static final RegistryObject<Block> ").append(block.getId().toUpperCase()).append(" = BLOCKS.register(\"").append(block.getId()).append("_block\", () -> new Block(\n")
                    .append("\t\t\tBlockBehaviour.Properties.of(Material.").append(block.getMaterial()).append(").sound(SoundType.").append(block.getSoundType()).append(").strength(").append(block.getStrengthOne()).append("f, ").append(block.getStrengthTwo()).append("f)\n")
                    .append("\t\t\t\t\t").append(".lightLevel(state -> ").append(block.getLightLevel()).append(")").append(".explosionResistance(").append(block.getExplosionResistance()).append("f)\n\t\t\t\t\t");
            if (block.isRequiresCorrectTool()) {
                registration.append(".requiresCorrectToolForDrops()");
            }
            if (block.isInstabreak()) {
                registration.append(".instabreak()");
            }
            registration.append("));\n");

            //block item
            registration.append("\n\tpublic static final RegistryObject<Item> ").append(block.getId().toUpperCase()).append("_ITEM = ITEMS.register(\"").append(block.getId()).append("\", () -> new BlockItem(").append(block.getId().toUpperCase()).append(".get(), itemBuilder()));\n");

            blocks.append(registration);
        }
        modBlocks = CODE_REPLACE
                .matcher(modBlocks)
                .replaceAll("/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/" +
                        blocks +
                        "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        writeToFile(getModBlocksClass(), modBlocks);
    }


    //public static final RegistryObject<Item> SILVERNUGGET = ITEMS.register("silvernugget", () -> new Item(itemBuilder().stacksTo(64).rarity(Rarity.COMMON)));
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
                        \t\tGeneratedModBlocks.loadClass();
                        \t\tGeneratedModItems.loadClass();
                        \t\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/""");
        writeToFile(getModloaderClass(), modloaderMain);
    }
}
