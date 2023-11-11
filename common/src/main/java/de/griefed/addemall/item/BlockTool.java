package de.griefed.addemall.item;

import de.griefed.addemall.platform.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class BlockTool extends PickaxeItem {

    public BlockTool() {
        this(Tiers.NETHERITE, 12, 8f, new Item.Properties().tab(Services.PLATFORM.getCreativeTab()));
    }

    private BlockTool(Tier $$0, int $$1, float $$2, Properties $$3) {
        super($$0, $$1, $$2, $$3);
    }
}
