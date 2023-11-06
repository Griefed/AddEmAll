package de.griefed.generation;

public class StringTemplates {
    public static final String FORGE_MOD_BLOCK_CLASS = """
            package GROUP.block;

            import GROUP.MODLOADERCLASS;
            import GROUP.item.ModItems;
            import net.minecraft.world.item.BlockItem;
            import net.minecraft.world.item.CreativeModeTab;
            import net.minecraft.world.item.Item;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.level.block.state.BlockBehaviour;
            import net.minecraft.world.level.material.Material;
            import net.minecraftforge.eventbus.api.IEventBus;
            import net.minecraftforge.registries.DeferredRegister;
            import net.minecraftforge.registries.ForgeRegistries;
            import net.minecraftforge.registries.RegistryObject;

            import java.util.function.Supplier;

            public class ModBlocks {
                public static final DeferredRegister<Block> BLOCKS =
                        DeferredRegister.create(ForgeRegistries.BLOCKS, MODLOADERCLASS.MOD_ID);

                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                GENERATED_BLOCKS_CODE
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

                private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
                    RegistryObject<T> toReturn = BLOCKS.register(name, block);
                    registerBlockItem(name, toReturn, tab);
                    return toReturn;
                }

                private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
                    return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
                }

                public static void register(IEventBus eventBus) {
                    BLOCKS.register(eventBus);
                }
            }
                        """;

    public static final String FORGE_MOD_ITEMS_CLASS = """
            package GROUP.item;

            import GROUP.MODLOADERCLASS;
            import net.minecraft.world.item.Item;
            import net.minecraftforge.eventbus.api.IEventBus;
            import net.minecraftforge.registries.DeferredRegister;
            import net.minecraftforge.registries.ForgeRegistries;
            import net.minecraftforge.registries.RegistryObject;

            public class ModItems {
                public static final DeferredRegister<Item> ITEMS =
                        DeferredRegister.create(ForgeRegistries.ITEMS, MODLOADERCLASS.MOD_ID);

                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                GENERATED_ITEMS_CODE
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

                public static void register(IEventBus eventBus) {
                    ITEMS.register(eventBus);
                }
            }
            """;

    public static final String FORGE_MODLOADER_CLASS = """
            package GROUP;

            import com.mojang.logging.LogUtils;
            import GROUP.block.ModBlocks;
            import GROUP.item.ModItems;
            import net.minecraftforge.common.MinecraftForge;
            import net.minecraftforge.eventbus.api.IEventBus;
            import net.minecraftforge.eventbus.api.SubscribeEvent;
            import net.minecraftforge.event.entity.player.ItemTooltipEvent;
            import net.minecraftforge.fml.common.Mod;
            import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
            import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
            import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
            import org.slf4j.Logger;

            // The value here should match an entry in the META-INF/mods.toml file
            @Mod(Constants.MOD_ID)
            public class SUBNAME {
                public static final String MOD_ID = "MODID";
                private static final Logger LOGGER = LogUtils.getLogger();

                // Very Important Comment
                public SUBNAME() {
                    // Use Forge to bootstrap the Common mod.
                    Constants.LOG.info("Hello world!");
                    LOGGER.info("Hello Forge World!");
                    CommonClass.init();
                    
                    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                    GENERATED_CONSTRUCTOR_CODE
                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

                    modEventBus.addListener(this::commonSetup);

                    MinecraftForge.EVENT_BUS.register(this);
                    MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
                }

                private void commonSetup(final FMLCommonSetupEvent event) {

                }

                // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
                @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
                public static class ClientModEvents {
                    @SubscribeEvent
                    public static void onClientSetup(FMLClientSetupEvent event) {

                    }
                }

                private void onItemTooltip(ItemTooltipEvent event) {
                    CommonClass.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
                }
            }
                        """;

    public static final String BLOCKSTATE = """
            {
              "variants": {
                "": { "model": "MODID:block/BLOCKID_block" }
              }
            }
            """;

    public static final String BLOCK_MODELS = """
            {
              "parent": "block/cube_all",
              "textures": {
                "all": "MODID:block/BLOCKID_block"
              }
            }
            """;

    public static final String BLOCK_MODELS_ITEM = """
            {
              "parent": "MODID:block/BLOCKID_block"
            }
            """;

    public static final String TRANSLATION = """
            {
              "DO.NOT.EDIT.MANUALLY.BEGIN": "BEGIN",
              GENERATED_TRANSLATION_CODE    
              "DO.NOT.EDIT.MANUALLY.END": "END"
            }
            """;

    public static final String FABRIC_MOD_BLOCK_CLASS = """
            package GROUP.block;
                        
            import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
            import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
            import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
            import GROUP.MODLOADERCLASS;
            import net.minecraft.block.Block;
            import net.minecraft.block.Material;
            import net.minecraft.item.BlockItem;
            import net.minecraft.item.Item;
            import net.minecraft.item.ItemGroup;
            import net.minecraft.item.ItemGroups;
            import net.minecraft.registry.Registries;
            import net.minecraft.registry.Registry;
            import net.minecraft.util.Identifier;
                        
            public class ModBlocks {
                
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                GENERATED_BLOCKS_CODE
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        
                private static Block registerBlockWithoutItem(String name, Block block) {
                    return Registry.register(Registries.BLOCK, new Identifier(MODLOADERCLASS.MOD_ID, name), block);
                }
                        
                private static Block registerBlock(String name, Block block, ItemGroup tab) {
                    registerBlockItem(name, block, tab);
                    return Registry.register(Registries.BLOCK, new Identifier(MODLOADERCLASS.MOD_ID, name), block);
                }
                        
                private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
                    Item item = Registry.register(Registries.ITEM, new Identifier(MODLOADERCLASS.MOD_ID, name),
                            new BlockItem(block, new FabricItemSettings()));
                    ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
                    return item;
                }
                        
                public static void registerModBlocks() {
                    MODLOADERCLASS.LOGGER.debug("Registering ModBlocks for " + MODLOADERCLASS.MOD_ID);
                }
            }
            """;

    public static final String FABRIC_MOD_ITEMS_CLASS = """
            package GROUP.item;
                        
            import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
            import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
            import GROUP.MODLOADERCLASS;
            import net.minecraft.item.Item;
            import net.minecraft.item.ItemGroup;
            import net.minecraft.item.ItemGroups;
            import net.minecraft.registry.Registries;
            import net.minecraft.registry.Registry;
            import net.minecraft.util.Identifier;
                        
            public class ModItems {
            
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                GENERATED_ITEMS_CODE
                /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                        
                private static Item registerItem(String name, Item item) {
                    return Registry.register(Registries.ITEM, new Identifier(MODLOADERCLASS.MOD_ID, name), item);
                }
                        
                public static void addItemsToItemGroups() {
                    /*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                    GENERATED_ITEMS_ADD_CODE
                    /*###GENERATED ADD CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                }
                        
                public static void addToItemGroup(ItemGroup group, Item item) {
                    ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
                }
                        
                public static void registerModItems() {
                    TutorialMod.LOGGER.debug("Registering Mod Items for " + TutorialMod.MOD_ID);
                        
                    addItemsToItemGroups();
                }
            }
            """;

    public static final String FABRIC_MODLOADER_CLASS = """
            package GROUP;
                        
            import net.fabricmc.api.ModInitializer;
            import GROUP.block.ModBlocks;
            import GROUP.item.ModItems;
            import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
            import org.slf4j.Logger;
            import org.slf4j.LoggerFactory;
                        
            // Very important comment
            public class SUBNAME implements ModInitializer {
                public static final String MOD_ID = "MODID";
                public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
                        
            	@Override
            	public void onInitialize() {
            	    Constants.LOG.info("Hello world!");
            	    LOGGER.info("Hello Fabric World!");
                    CommonClass.init();
                    
                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                    GENERATED_ITEMS_CODE
                    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
                    
            		ItemTooltipCallback.EVENT.register(CommonClass::onItemTooltip);
            	}
            }
            """;

    public static final String COMMON_MODBLOCKS_CLASS = """
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
            """;

    public static final String COMMON_ITEMS_CLASS = """
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
                        
            """;

    public static final String COMMON_MAIN_CLASS = """
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
            """;
}
