package de.griefed.addemall.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import static de.griefed.addemall.event.KeyInputHandler.*;

public class FabricKeyInputHandler {

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toolBehaviourKey.consumeClick()) {
                KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE = !KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE;
            }
            if (toolShovelHoeKey.consumeClick()) {
                KeyInputHandler.BLOCKTOOL_SHOVELMODE = !KeyInputHandler.BLOCKTOOL_SHOVELMODE;
            }
        });
    }

    public static void register() {
        KeyInputHandler.toolBehaviourKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_CHANGE_TOOL_BEHAVIOUR,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                KEY_CATEGORY_ADDEMALL));
        KeyInputHandler.toolShovelHoeKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_SWITCH_TOOL_SHOVEL_HOE,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_ADDEMALL));
        registerKeyInputs();
    }
}
