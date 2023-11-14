package de.griefed.addemall.event;

import com.mojang.blaze3d.platform.InputConstants;
import de.griefed.addemall.Constants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static de.griefed.addemall.event.KeyInputHandler.*;

public class ForgeKeyInputHandler {

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyInputHandler.toolBehaviourKey.consumeClick()) {
                KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE = !KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE;
            }
            if (KeyInputHandler.toolShovelHoeKey.consumeClick()) {
                KeyInputHandler.BLOCKTOOL_SHOVELMODE = !KeyInputHandler.BLOCKTOOL_SHOVELMODE;
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyInputHandler.toolBehaviourKey);
            event.register(KeyInputHandler.toolShovelHoeKey);
        }
    }

    public static void register() {
        KeyInputHandler.toolBehaviourKey = new KeyMapping(
                KEY_CHANGE_TOOL_BEHAVIOUR,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                KEY_CATEGORY_ADDEMALL);
        KeyInputHandler.toolShovelHoeKey = new KeyMapping(
                KEY_SWITCH_TOOL_SHOVEL_HOE,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_ADDEMALL);
    }
}
