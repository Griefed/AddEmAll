package de.griefed.addemall.item;

import de.griefed.addemall.Constants;
import de.griefed.addemall.platform.Services;
import de.griefed.addemall.registry.RegistrationProvider;
import de.griefed.addemall.registry.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class GeneratedModItems {
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);

    /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

	/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeTab());
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}

