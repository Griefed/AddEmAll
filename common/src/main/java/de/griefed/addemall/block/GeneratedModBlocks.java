package de.griefed.addemall.block;

import de.griefed.addemall.Constants;
import de.griefed.addemall.platform.Services;
import de.griefed.addemall.registry.RegistrationProvider;
import de.griefed.addemall.registry.RegistryObject;
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

	public static final RegistryObject<Block> GREEN_ZEN = BLOCKS.register("generated/dirt/green_zen_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS)
					.strength(2f, 2f).lightLevel(state -> 4).explosionResistance(0f)));

	public static final RegistryObject<Item> GREEN_ZEN_ITEM = ITEMS.register("generated/dirt/green_zen",
    		() -> new BlockItem(GREEN_ZEN.get(), itemBuilder()));

	public static final RegistryObject<Block> YELLOW_ZEN = BLOCKS.register("generated/dirt/yellow_zen_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS)
					.strength(2f, 2f).lightLevel(state -> 4).explosionResistance(0f)));

	public static final RegistryObject<Item> YELLOW_ZEN_ITEM = ITEMS.register("generated/dirt/yellow_zen",
    		() -> new BlockItem(YELLOW_ZEN.get(), itemBuilder()));

	public static final RegistryObject<Block> BLUE_ZEN = BLOCKS.register("generated/dirt/blue_zen_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS)
					.strength(2f, 2f).lightLevel(state -> 4).explosionResistance(0f)));

	public static final RegistryObject<Item> BLUE_ZEN_ITEM = ITEMS.register("generated/dirt/blue_zen",
    		() -> new BlockItem(BLUE_ZEN.get(), itemBuilder()));

	public static final RegistryObject<Block> BROWN_ZEN = BLOCKS.register("generated/dirt/brown_zen_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS)
					.strength(2f, 2f).lightLevel(state -> 4).explosionResistance(0f)));

	public static final RegistryObject<Item> BROWN_ZEN_ITEM = ITEMS.register("generated/dirt/brown_zen",
    		() -> new BlockItem(BROWN_ZEN.get(), itemBuilder()));

	public static final RegistryObject<Block> RED_ZEN = BLOCKS.register("generated/dirt/red_zen_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS)
					.strength(2f, 2f).lightLevel(state -> 4).explosionResistance(0f)));

	public static final RegistryObject<Item> RED_ZEN_ITEM = ITEMS.register("generated/dirt/red_zen",
    		() -> new BlockItem(RED_ZEN.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_1 = BLOCKS.register("generated/metal/metal_floor_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_1_ITEM = ITEMS.register("generated/metal/metal_floor_1",
    		() -> new BlockItem(METAL_FLOOR_1.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_2 = BLOCKS.register("generated/metal/metal_floor_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_2_ITEM = ITEMS.register("generated/metal/metal_floor_2",
    		() -> new BlockItem(METAL_FLOOR_2.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_3 = BLOCKS.register("generated/metal/metal_floor_3_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_3_ITEM = ITEMS.register("generated/metal/metal_floor_3",
    		() -> new BlockItem(METAL_FLOOR_3.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_4 = BLOCKS.register("generated/metal/metal_floor_4_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_4_ITEM = ITEMS.register("generated/metal/metal_floor_4",
    		() -> new BlockItem(METAL_FLOOR_4.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_5 = BLOCKS.register("generated/metal/metal_floor_5_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_5_ITEM = ITEMS.register("generated/metal/metal_floor_5",
    		() -> new BlockItem(METAL_FLOOR_5.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_FLOOR_6 = BLOCKS.register("generated/metal/metal_floor_6_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_FLOOR_6_ITEM = ITEMS.register("generated/metal/metal_floor_6",
    		() -> new BlockItem(METAL_FLOOR_6.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_PLATING_1 = BLOCKS.register("generated/metal/metal_plating_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_PLATING_1_ITEM = ITEMS.register("generated/metal/metal_plating_1",
    		() -> new BlockItem(METAL_PLATING_1.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_PLATING_2 = BLOCKS.register("generated/metal/metal_plating_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_PLATING_2_ITEM = ITEMS.register("generated/metal/metal_plating_2",
    		() -> new BlockItem(METAL_PLATING_2.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_PLATING_3 = BLOCKS.register("generated/metal/metal_plating_3_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_PLATING_3_ITEM = ITEMS.register("generated/metal/metal_plating_3",
    		() -> new BlockItem(METAL_PLATING_3.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_PLATING_4 = BLOCKS.register("generated/metal/metal_plating_4_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_PLATING_4_ITEM = ITEMS.register("generated/metal/metal_plating_4",
    		() -> new BlockItem(METAL_PLATING_4.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_ROOF_1 = BLOCKS.register("generated/metal/metal_roof_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_ROOF_1_ITEM = ITEMS.register("generated/metal/metal_roof_1",
    		() -> new BlockItem(METAL_ROOF_1.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_ROOF_2 = BLOCKS.register("generated/metal/metal_roof_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_ROOF_2_ITEM = ITEMS.register("generated/metal/metal_roof_2",
    		() -> new BlockItem(METAL_ROOF_2.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_ROOF_3 = BLOCKS.register("generated/metal/metal_roof_3_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_ROOF_3_ITEM = ITEMS.register("generated/metal/metal_roof_3",
    		() -> new BlockItem(METAL_ROOF_3.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_ROOF_4 = BLOCKS.register("generated/metal/metal_roof_4_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_ROOF_4_ITEM = ITEMS.register("generated/metal/metal_roof_4",
    		() -> new BlockItem(METAL_ROOF_4.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_ROOF_5 = BLOCKS.register("generated/metal/metal_roof_5_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_ROOF_5_ITEM = ITEMS.register("generated/metal/metal_roof_5",
    		() -> new BlockItem(METAL_ROOF_5.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_VENT_1 = BLOCKS.register("generated/metal/metal_vent_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_VENT_1_ITEM = ITEMS.register("generated/metal/metal_vent_1",
    		() -> new BlockItem(METAL_VENT_1.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_1 = BLOCKS.register("generated/metal/metal_wall_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_1_ITEM = ITEMS.register("generated/metal/metal_wall_1",
    		() -> new BlockItem(METAL_WALL_1.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_2 = BLOCKS.register("generated/metal/metal_wall_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_2_ITEM = ITEMS.register("generated/metal/metal_wall_2",
    		() -> new BlockItem(METAL_WALL_2.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_3 = BLOCKS.register("generated/metal/metal_wall_3_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_3_ITEM = ITEMS.register("generated/metal/metal_wall_3",
    		() -> new BlockItem(METAL_WALL_3.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_4 = BLOCKS.register("generated/metal/metal_wall_4_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_4_ITEM = ITEMS.register("generated/metal/metal_wall_4",
    		() -> new BlockItem(METAL_WALL_4.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_5 = BLOCKS.register("generated/metal/metal_wall_5_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_5_ITEM = ITEMS.register("generated/metal/metal_wall_5",
    		() -> new BlockItem(METAL_WALL_5.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_6 = BLOCKS.register("generated/metal/metal_wall_6_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_6_ITEM = ITEMS.register("generated/metal/metal_wall_6",
    		() -> new BlockItem(METAL_WALL_6.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_7 = BLOCKS.register("generated/metal/metal_wall_7_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_7_ITEM = ITEMS.register("generated/metal/metal_wall_7",
    		() -> new BlockItem(METAL_WALL_7.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_8 = BLOCKS.register("generated/metal/metal_wall_8_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_8_ITEM = ITEMS.register("generated/metal/metal_wall_8",
    		() -> new BlockItem(METAL_WALL_8.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_9 = BLOCKS.register("generated/metal/metal_wall_9_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_9_ITEM = ITEMS.register("generated/metal/metal_wall_9",
    		() -> new BlockItem(METAL_WALL_9.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_10 = BLOCKS.register("generated/metal/metal_wall_10_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_10_ITEM = ITEMS.register("generated/metal/metal_wall_10",
    		() -> new BlockItem(METAL_WALL_10.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_11 = BLOCKS.register("generated/metal/metal_wall_11_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_11_ITEM = ITEMS.register("generated/metal/metal_wall_11",
    		() -> new BlockItem(METAL_WALL_11.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_12 = BLOCKS.register("generated/metal/metal_wall_12_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_12_ITEM = ITEMS.register("generated/metal/metal_wall_12",
    		() -> new BlockItem(METAL_WALL_12.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_13 = BLOCKS.register("generated/metal/metal_wall_13_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_13_ITEM = ITEMS.register("generated/metal/metal_wall_13",
    		() -> new BlockItem(METAL_WALL_13.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_14 = BLOCKS.register("generated/metal/metal_wall_14_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_14_ITEM = ITEMS.register("generated/metal/metal_wall_14",
    		() -> new BlockItem(METAL_WALL_14.get(), itemBuilder()));

	public static final RegistryObject<Block> METAL_WALL_15 = BLOCKS.register("generated/metal/metal_wall_15_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
					.strength(12f, 12f).lightLevel(state -> 0).explosionResistance(12f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> METAL_WALL_15_ITEM = ITEMS.register("generated/metal/metal_wall_15",
    		() -> new BlockItem(METAL_WALL_15.get(), itemBuilder()));

	public static final RegistryObject<Block> RED_STRIPES = BLOCKS.register("generated/stone/red_stripes_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> RED_STRIPES_ITEM = ITEMS.register("generated/stone/red_stripes",
    		() -> new BlockItem(RED_STRIPES.get(), itemBuilder()));

	public static final RegistryObject<Block> CARPET_1 = BLOCKS.register("generated/wool/carpet_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.WOOL)
					.strength(4f, 4f).lightLevel(state -> 0).explosionResistance(0f)));

	public static final RegistryObject<Item> CARPET_1_ITEM = ITEMS.register("generated/wool/carpet_1",
    		() -> new BlockItem(CARPET_1.get(), itemBuilder()));

	public static final RegistryObject<Block> CARPET_2 = BLOCKS.register("generated/wool/carpet_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.WOOL)
					.strength(4f, 4f).lightLevel(state -> 0).explosionResistance(0f)));

	public static final RegistryObject<Item> CARPET_2_ITEM = ITEMS.register("generated/wool/carpet_2",
    		() -> new BlockItem(CARPET_2.get(), itemBuilder()));

	public static final RegistryObject<Block> TILES_1 = BLOCKS.register("generated/stone/tiles_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TILES_1_ITEM = ITEMS.register("generated/stone/tiles_1",
    		() -> new BlockItem(TILES_1.get(), itemBuilder()));

	public static final RegistryObject<Block> TILES_2 = BLOCKS.register("generated/stone/tiles_2_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TILES_2_ITEM = ITEMS.register("generated/stone/tiles_2",
    		() -> new BlockItem(TILES_2.get(), itemBuilder()));

	public static final RegistryObject<Block> TILES_3 = BLOCKS.register("generated/stone/tiles_3_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TILES_3_ITEM = ITEMS.register("generated/stone/tiles_3",
    		() -> new BlockItem(TILES_3.get(), itemBuilder()));

	public static final RegistryObject<Block> TILES_4 = BLOCKS.register("generated/stone/tiles_4_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TILES_4_ITEM = ITEMS.register("generated/stone/tiles_4",
    		() -> new BlockItem(TILES_4.get(), itemBuilder()));

	public static final RegistryObject<Block> TILES_5 = BLOCKS.register("generated/stone/tiles_5_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
					.strength(8f, 8f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TILES_5_ITEM = ITEMS.register("generated/stone/tiles_5",
    		() -> new BlockItem(TILES_5.get(), itemBuilder()));

	public static final RegistryObject<Block> WALLPAPER_1 = BLOCKS.register("generated/wood/wallpaper_1_block",
 			() -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)
					.strength(6f, 6f).lightLevel(state -> 0).explosionResistance(4f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> WALLPAPER_1_ITEM = ITEMS.register("generated/wood/wallpaper_1",
    		() -> new BlockItem(WALLPAPER_1.get(), itemBuilder()));

	/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
    
    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {
    }
}
