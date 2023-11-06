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
	public static final RegistryObject<Block> TUTORIAL_STONE = BLOCKS.register("tutorial_stone_block", () -> new Block(
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(12f, 10f)
					.lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Item> TUTORIAL_STONE_ITEM = ITEMS.register("tutorial_stone", () -> new BlockItem(TUTORIAL_STONE.get(), itemBuilder()));

	public static final RegistryObject<Block> TUTORIAL_WOOL = BLOCKS.register("tutorial_wool_block", () -> new Block(
			BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.STONE).strength(8f, 6f)
					.lightLevel(state -> 5).explosionResistance(6f)
					));

	public static final RegistryObject<Item> TUTORIAL_WOOL_ITEM = ITEMS.register("tutorial_wool", () -> new BlockItem(TUTORIAL_WOOL.get(), itemBuilder()));

	public static final RegistryObject<Block> TUTORIAL_SAND = BLOCKS.register("tutorial_sand_block", () -> new Block(
			BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.STONE).strength(4f, 2f)
					.lightLevel(state -> 12).explosionResistance(2f)
					.instabreak()));

	public static final RegistryObject<Item> TUTORIAL_SAND_ITEM = ITEMS.register("tutorial_sand", () -> new BlockItem(TUTORIAL_SAND.get(), itemBuilder()));
	/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {
    }
}
