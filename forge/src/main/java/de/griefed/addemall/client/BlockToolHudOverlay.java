package de.griefed.addemall.client;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockToolHudOverlay extends BlockToolHud {
    public static final IGuiOverlay TOOL_SETTINGS = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        renderToolSettings(poseStack, x, height);
    });

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("tool.settings", BlockToolHudOverlay.TOOL_SETTINGS);
    }
}
