package de.griefed.addemall.item;

import de.griefed.addemall.platform.Services;
import de.griefed.addemall.registry.RegistryObject;
import net.minecraft.world.item.*;

import static de.griefed.addemall.CommonClass.ITEMS;

public class ModItems {

    public static final RegistryObject<Item> BLOCK_TOOL = ITEMS.register("block_tool", BlockToolItem::new);

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
