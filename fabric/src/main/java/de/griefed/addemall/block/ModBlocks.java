package de.griefed.addemall.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import de.griefed.addemall.AddEmAllFabric;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.block.Block;

public class ModBlocks {

    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
	public static final Block TUTORIAL_STONE_BLOCK = registerBlock("tutorial_stone_block",
			new Block(FabricBlockSettings.of(Material.STONE).strength(6f).luminance(0)
					.hardness(8).requiresTool()
					), ItemGroups.BUILDING_BLOCKS);

	public static final Block TUTORIAL_WOOL_BLOCK = registerBlock("tutorial_wool_block",
			new Block(FabricBlockSettings.of(Material.WOOL).strength(4f).luminance(5)
					.hardness(6)
					), ItemGroups.BUILDING_BLOCKS);

	public static final Block TUTORIAL_SAND_BLOCK = registerBlock("tutorial_sand_block",
			new Block(FabricBlockSettings.of(Material.SAND).strength(2f).luminance(12)
					.hardness(2).breakInstantly()
					), ItemGroups.BUILDING_BLOCKS);
	/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(AddEmAllFabric.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(AddEmAllFabric.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registry.ITEM, new Identifier(AddEmAllFabric.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        AddEmAllFabric.LOGGER.debug("Registering ModBlocks for " + AddEmAllFabric.MOD_ID);
    }
}
