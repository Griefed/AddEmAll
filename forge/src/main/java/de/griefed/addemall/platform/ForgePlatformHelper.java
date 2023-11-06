package de.griefed.addemall.platform;

import de.griefed.addemall.AddEmAllForge;
import de.griefed.addemall.platform.services.IPlatformHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public CreativeModeTab getCreativeTab() {
        return AddEmAllForge.TAB_ADDEMALL;
    }

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }
}