package de.griefed.addemall;

import de.griefed.addemall.block.GeneratedModBlocks;
import de.griefed.addemall.client.BlockToolHud;
import de.griefed.addemall.client.BlockToolHudOverlay;
import de.griefed.addemall.event.FabricKeyInputHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class AddEmAllFabric implements ModInitializer {
    public static final String MOD_ID = "addemall";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final CreativeModeTab TAB_ADDEMALL = FabricItemGroupBuilder.build(
            new ResourceLocation(Constants.MOD_ID, "tab"),
            () -> new ItemStack(GeneratedModBlocks.METAL_PLATING_2_ITEM.get())
    );

    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello world!");
        LOGGER.info("Hello Fabric World! I'm gonna AddEmAll!");
        CommonClass.init();
        FabricKeyInputHandler.register();

        ItemTooltipCallback.EVENT.register(CommonClass::onItemTooltip);
        HudRenderCallback.EVENT.register(new BlockToolHudOverlay());
    }
}
