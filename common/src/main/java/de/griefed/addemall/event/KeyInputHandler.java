package de.griefed.addemall.event;

import net.minecraft.client.KeyMapping;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_ADDEMALL = "key.category.addemall.tool";
    public static final String KEY_CHANGE_TOOL_BEHAVIOUR = "key.addemall.tool.change_behaviour";
    public static final String KEY_SWITCH_TOOL_SHOVEL_HOE = "key.addemall.tool.shov_hoe";

    public static KeyMapping toolBehaviourKey;
    public static KeyMapping toolShovelHoeKey;

    public static boolean BLOCKTOOL_MULTI_ACTIVE = false;
    public static boolean BLOCKTOOL_SHOVELMODE = false;

    public static void loadClass() {}
}
