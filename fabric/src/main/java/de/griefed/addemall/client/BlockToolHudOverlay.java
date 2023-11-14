package de.griefed.addemall.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

public class BlockToolHudOverlay extends BlockToolHud implements HudRenderCallback {
    @Override
    public void onHudRender(PoseStack matrixStack, float tickDelta) {
        int x, y;
        Minecraft instance = Minecraft.getInstance();
        int width = instance.getWindow().getGuiScaledWidth();
        int height = instance.getWindow().getGuiScaledHeight();

        x = width / 2;
        y = height;

        renderToolSettings(matrixStack, x, y);
    }
}
