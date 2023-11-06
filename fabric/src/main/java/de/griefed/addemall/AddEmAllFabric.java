package de.griefed.addemall;

import net.fabricmc.api.ModInitializer;
import de.griefed.addemall.block.ModBlocks;
import de.griefed.addemall.item.ModItems;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class AddEmAllFabric implements ModInitializer {
    public static final String MOD_ID = "addemall";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	    Constants.LOG.info("Hello world!");
	    LOGGER.info("Hello Fabric World!");
        CommonClass.init();

        /*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		/*###GENERATED CODE - DO NOT EDIT - MANUALLY EDITED CODE WILL BE LOST###*/

		ItemTooltipCallback.EVENT.register(CommonClass::onItemTooltip);
	}
}
