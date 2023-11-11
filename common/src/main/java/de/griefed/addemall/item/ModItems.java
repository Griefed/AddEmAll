package de.griefed.addemall.item;

import de.griefed.addemall.Constants;
import de.griefed.addemall.platform.Services;
import de.griefed.addemall.registry.RegistrationProvider;
import de.griefed.addemall.registry.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;

public class ModItems {
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);

    public static final RegistryObject<Item> BLOCK_TOOL = ITEMS.register("block_tool", BlockTool::new);

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
