package de.griefed.addemall.block;

import de.griefed.addemall.AddEmAllForge;
import de.griefed.addemall.item.ModItems;
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
            DeferredRegister.create(ForgeRegistries.BLOCKS, AddEmAllForge.MOD_ID);

    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
	public static final RegistryObject<Block> TUTORIAL_STONE_BLOCK = registerBlock("tutorial_stone_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
					.strength(6f).lightLevel(state -> 0).explosionResistance(8f)
					.requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> TUTORIAL_WOOL_BLOCK = registerBlock("tutorial_wool_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.WOOL)
					.strength(4f).lightLevel(state -> 5).explosionResistance(6f)
					), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> TUTORIAL_SAND_BLOCK = registerBlock("tutorial_sand_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.SAND)
					.strength(2f).lightLevel(state -> 12).explosionResistance(2f)
					.instabreak()), CreativeModeTab.TAB_BUILDING_BLOCKS);
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
