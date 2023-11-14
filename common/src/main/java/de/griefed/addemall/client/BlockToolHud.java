package de.griefed.addemall.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.griefed.addemall.Constants;
import de.griefed.addemall.event.KeyInputHandler;
import de.griefed.addemall.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class BlockToolHud {

    protected static final ResourceLocation TOOL_MULTI_ACTIVATED = new ResourceLocation(Constants.MOD_ID, "textures/item/multi.png");
    protected static final ResourceLocation TOOL_MULTI_DEACTIVATED = new ResourceLocation(Constants.MOD_ID, "textures/item/multi_deactivated.png");
    protected static final ResourceLocation TOOL_SHOVEL = new ResourceLocation(Constants.MOD_ID, "textures/item/shovel.png");
    protected static final ResourceLocation TOOL_HOE = new ResourceLocation(Constants.MOD_ID, "textures/item/hoe.png");
    protected static final ResourceLocation TOOL_BAR = new ResourceLocation(Constants.MOD_ID, "textures/item/bar.png");

    protected static void renderToolSettings(PoseStack stack, int x, int height) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (player.getMainHandItem().is(ModItems.BLOCK_TOOL.get())) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            RenderSystem.setShaderTexture(0, TOOL_BAR);
            //                      X coord  Y coord      u  v  wid hei twi the
            GuiComponent.blit(stack, x + 96, height - 23, 0, 0, 65, 23, 65, 23);

            if (KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE) {
                RenderSystem.setShaderTexture(0, TOOL_MULTI_ACTIVATED);
                GuiComponent.blit(stack, x + 100, height - 19, 0, 0, 16, 16, 16, 16);
            } else {
                RenderSystem.setShaderTexture(0, TOOL_MULTI_DEACTIVATED);
                GuiComponent.blit(stack, x + 100, height - 19, 0, 0, 16, 16, 16, 16);
            }

            if (KeyInputHandler.BLOCKTOOL_SHOVELMODE) {
                RenderSystem.setShaderTexture(0, TOOL_SHOVEL);
                GuiComponent.blit(stack, x + 120, height - 19, 0, 0, 16, 16, 16, 16);
            } else {
                RenderSystem.setShaderTexture(0, TOOL_HOE);
                GuiComponent.blit(stack, x + 120, height - 19, 0, 0, 16, 16, 16, 16);
            }

            // TODO draw selected block
            // TODO draw amount available
            /*if (block selected) {
                RenderSystem.setShaderTexture(0, stack of selected block with amount of material available);
                GuiComponent.blit(stack, x + 140, height - 19, 0, 0, 16, 16, 16, 16);
            }*/
        }
    }
}
