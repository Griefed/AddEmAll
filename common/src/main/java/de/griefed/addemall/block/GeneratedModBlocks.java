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

	/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
    
    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {
    }
}
