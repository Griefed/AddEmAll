package de.griefed.generation.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.griefed.generation.blocks.BlockDefinition;
import de.griefed.generation.blocks.BlockDefinitionParser;
import org.gradle.api.Project;

import java.io.IOException;

public class CommonSubProjectGenerator extends CodeGenerator implements CodeGeneration {
    public CommonSubProjectGenerator(Project project, String modName, BlockDefinitionParser parser, ObjectMapper objectMapper) {
        super(project, modName, parser, objectMapper);
    }

    private final String commonGeneratedModBlocksClassTemplate = """
            package GROUP.block;
                        
            import GROUP.Constants;
            import GROUP.platform.Services;
            import GROUP.registry.RegistrationProvider;
            import GROUP.registry.RegistryObject;
            import net.minecraft.core.Registry;
            import net.minecraft.world.item.*;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.level.block.SoundType;
            import net.minecraft.world.level.block.state.BlockBehaviour;
            import net.minecraft.world.level.material.Material;
                        
            public class GeneratedModBlocks {
                public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registry.BLOCK_REGISTRY, Constants.MOD_ID);
                public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);
                        
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        
                private static Item.Properties itemBuilder() {
                    return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
                }
                        
                // Called in the mod initializer / constructor in order to make sure that items are registered
                public static void loadClass() {
                }
            }
            """
            .replace("GROUP", getGroup());

    private final String commonGeneratedItemsClassTemplate = """
            package GROUP.item;
                        
            import GROUP.Constants;
            import GROUP.platform.Services;
            import GROUP.registry.RegistrationProvider;
            import GROUP.registry.RegistryObject;
            import net.minecraft.core.Registry;
            import net.minecraft.world.item.Item;
                        
            public class GeneratedModItems {
                public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);
                        
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        
                private static Item.Properties itemBuilder() {
                    return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
                }
                        
                // Called in the mod initializer / constructor in order to make sure that items are registered
                public static void loadClass() {
                }
            }
                        
            """
            .replace("GROUP", getGroup());

    private final String commonClassTemplate = """
            package GROUP;

            import GROUP.block.GeneratedModBlocks;
            import GROUP.item.GeneratedModItems;
            import GROUP.platform.Services;
            import net.minecraft.core.Registry;
            import net.minecraft.network.chat.Component;
            import net.minecraft.world.food.FoodProperties;
            import net.minecraft.world.item.ItemStack;
            import net.minecraft.world.item.Items;
            import net.minecraft.world.item.TooltipFlag;

            import java.util.List;

            // This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
            // import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
            // common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
            // however it will be compatible with all supported mod loaders.
            public class CommonClass {

                // The loader specific projects are able to import and use any code from the common project. This allows you to
                // write the majority of your code here and load it from your loader specific projects. This example has some
                // code that gets invoked by the entry point of the loader specific projects.
                public static void init() {

                    Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
                    Constants.LOG.info("The ID for diamonds is {}", Registry.ITEM.getKey(Items.DIAMOND));

                    // It is common for all supported loaders to provide a similar feature that can not be used directly in the
                    // common code. A popular way to get around this is using Java's built-in service loader feature to create
                    // your own abstraction layer. You can learn more about this in our provided services class. In this example
                    // we have an interface in the common code and use a loader specific implementation to delegate our call to
                    // the platform specific approach.
                    if (Services.PLATFORM.isModLoaded("MODID")) {
                        Constants.LOG.info("Hello to MODID");
                    }

                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                }

                public static void onItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {
                    if (!stack.isEmpty()) {
                        final FoodProperties food = stack.getItem().getFoodProperties();

                        if (food != null) {
                            tooltip.add(Component.literal("Nutrition: " + food.getNutrition()));
                            tooltip.add(Component.literal("Saturation: " + food.getSaturationModifier()));
                        }
                    }
                }
            }
            """
            .replace("GROUP", getGroup())
            .replace("MODID", getId());

    private static final String BLOCK_REGISTRATION_TEMPLATE = """
            \tpublic static final RegistryObject<Block> %s = BLOCKS.register("%s_block",
             \t\t\t() -> new Block(BlockBehaviour.Properties.of(Material.%s).sound(SoundType.%s)
            				\t.strength(%df, %df).lightLevel(state -> %d).explosionResistance(%df)TOOLBREAK));
                       """;

    private static final String BLOCK_ITEM_REGISTRATION_TEMPLATE = """
            \tpublic static final RegistryObject<Item> %s_ITEM = ITEMS.register("%s",
                \t\t() -> new BlockItem(%s.get(), itemBuilder()));
                        """;

    private static final String BLOCK_REGISTRATION = """
            %s
            %s
            """;

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
            System.out.println(getModBlocksClass().getName() + "-class created: " + getModBlocksClass().getAbsolutePath());
            writeToFile(getModBlocksClass(), commonGeneratedModBlocksClassTemplate);
        }
    }

    @Override
    public void createModItemsClass() throws IOException {
        if (!getModItemsClass().exists() && getModItemsClass().createNewFile()) {
            System.out.println(getModItemsClass().getName() + "-class created: " + getModItemsClass().getAbsolutePath());
            writeToFile(getModItemsClass(), commonGeneratedItemsClassTemplate);
        }
    }

    @Override
    public void createModloaderClass() throws IOException {
        if (!getModloaderClass().exists() && getModloaderClass().createNewFile()) {
            System.out.println(getModloaderClass().getName() + "-class created: " + getModloaderClass().getAbsolutePath());
            writeToFile(getModloaderClass(), commonClassTemplate);
        }
    }

    @Override
    public void updateModBlocksClass() throws IOException {
        String modBlocks = readFromFile(getModBlocksClass());
        StringBuilder blocks = new StringBuilder();
        String blockToRegister;
        String blockItemToRegister;
        for (BlockDefinition block : getBlockDefinitionParser().getBlocks()) {
            //block
            blockToRegister = String.format(
                    BLOCK_REGISTRATION_TEMPLATE,
                    block.getId().toUpperCase(), block.getId(),
                    block.getMaterial(), block.getSoundType(), block.getStrengthOne(), block.getStrengthTwo(),
                    block.getLightLevel(), block.getExplosionResistance());
            if (block.isRequiresCorrectTool()) {
                blockToRegister = blockToRegister.replace("TOOL", "\n\t\t\t\t\t.requiresCorrectToolForDrops()");
            } else {
                blockToRegister = blockToRegister.replace("TOOL", "");
            }
            if (block.isInstabreak()) {
                blockToRegister = blockToRegister.replace("BREAK", ".instabreak()");
            } else {
                blockToRegister = blockToRegister.replace("BREAK", "");
            }

            //block item
            blockItemToRegister = String.format(
                    BLOCK_ITEM_REGISTRATION_TEMPLATE,
                    block.getId().toUpperCase(), block.getId(), block.getId().toUpperCase()
            );

            //add block and block item
            blocks.append(String.format(
                    BLOCK_REGISTRATION,
                    blockToRegister, blockItemToRegister
            ));
        }
        modBlocks = GENERATED_CODE_PATTERN
                .matcher(modBlocks)
                .replaceAll(
                        "/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/\n\n" +
                                blocks +
                                "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        writeToFile(getModBlocksClass(), modBlocks);
    }


    //public static final RegistryObject<Item> SILVERNUGGET = ITEMS.register("silvernugget", () -> new Item(itemBuilder().stacksTo(64).rarity(Rarity.COMMON)));
    @SuppressWarnings({"StringBufferReplaceableByString", "MismatchedQueryAndUpdateOfStringBuilder", "unused"})
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
        modItems = GENERATED_CODE_PATTERN
                .matcher(modItems)
                .replaceAll("/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/\n\n" +
                        items +
                        "\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/");
        writeToFile(getModItemsClass(), modItems);
    }

    @Override
    public void updateModloaderMain() throws IOException {
        String modloaderMain = readFromFile(getModloaderClass());
        modloaderMain = GENERATED_CODE_PATTERN
                .matcher(modloaderMain)
                .replaceAll("""
                        /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        \t\tGeneratedModBlocks.loadClass();
                        \t\tGeneratedModItems.loadClass();
                        \t\t/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/""");
        writeToFile(getModloaderClass(), modloaderMain);
    }
}
