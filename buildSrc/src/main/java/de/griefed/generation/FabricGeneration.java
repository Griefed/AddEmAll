package de.griefed.generation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.IOException;
import java.util.regex.Pattern;

public class FabricGeneration extends ModloaderGeneration implements CodeGeneration {

    public static final Pattern ADD_CODE_REPLACE = Pattern.compile("/\\*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/.*/\\*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###\\*/", Pattern.DOTALL);

    public FabricGeneration(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        super(project, modName, parser, objectMapper);
    }

    private final String modBlockClassTemplate = StringTemplates.FABRIC_MOD_BLOCK_CLASS
            .replace("GROUP", getGroup())
            .replace("MODLOADERCLASS", getModloaderClass().getName().replace(".java", ""));

    private final String modItemsClassTemplate = StringTemplates.FABRIC_MOD_ITEMS_CLASS
            .replace("GROUP", getGroup())
            .replace("MODLOADERCLASS", getModloaderClass().getName().replace(".java", ""));

    private final String modloaderClassTemplate = StringTemplates.FABRIC_MODLOADER_CLASS
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

    /*
    public static final Block TANZANITE_BLOCK = registerBlock("tanzanite_block",
        new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ItemGroups.BUILDING_BLOCKS);
     */

    @Override
    public void updateModBlocksClass() throws IOException {
        String modBlocks = readFromFile(getModBlocksClass());
        StringBuilder blocks = new StringBuilder();
        StringBuilder registration;
        for (BlockDefinition block : getBlockDefinitionParser().getBlocks()) {
            registration = new StringBuilder();
            registration.append("\n\tpublic static final Block ").append(block.getId().toUpperCase()).append("_BLOCK = registerBlock(\"").append(block.getId()).append("_block\",\n")
                    .append("\t\t\tnew Block(FabricBlockSettings.of(Material.").append(block.getMaterial()).append(").strength(").append(block.getStrength()).append("f).luminance(").append(block.getLightLevel()).append(")\n")
                    .append("\t\t\t\t\t.hardness(").append(block.getExplosionResistance()).append(")");
            if (block.isRequiresCorrectTool()) {
                registration.append(".requiresTool()");
            }
            if (block.isInstabreak()) {
                registration.append(".breakInstantly()");
            }
            registration.append("\n\t\t\t\t\t), ItemGroups.BUILDING_BLOCKS);\n");
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
        StringBuilder itemsAdd = new StringBuilder();
        StringBuilder registration;
        StringBuilder add;
        /*for (ItemDefinition item : getItemDefinitionParser().getItems()) {
            //registration
            registration = new StringBuilder();
            registration.append("\n\tpublic static final Item ").append(item.getId().toUpperCase()).append(" = registerItem(\"").append(item.getId()).append("\",\n")
                    .append("\t\t\tnew Item(new FabricItemSettings()));");
            registration.append("\n");
            items.append(registration);
            //add
            add = new StringBuilder();
            add.append("\t\t\taddToItemGroup(ItemGroups.").append(item.getItemGroup()).append(", ").append(item.getId().toUpperCase()).append(");\n");
            itemsAdd.append(add);
        }*/
        modItems = CODE_REPLACE
                .matcher(modItems)
                .replaceAll("/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/\n\n" +
                        items +
                        "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        modItems = ADD_CODE_REPLACE
                .matcher(modItems)
                .replaceAll("/*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST*/\n\n" +
                        itemsAdd +
                        "\t\t/*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST*/");
        writeToFile(getModItemsClass(), modItems);
    }

    @Override
    public void updateModloaderMain() throws IOException {
        String modloaderMain = readFromFile(getModloaderClass());
        modloaderMain = CODE_REPLACE
                .matcher(modloaderMain)
                .replaceAll("""
                        /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        \t\tModItems.registerModItems();
                        \t\tModBlocks.registerModBlocks();
                        \t\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/""");
        writeToFile(getModloaderClass(), modloaderMain);
    }
}
