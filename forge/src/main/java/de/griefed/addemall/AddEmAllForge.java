package de.griefed.addemall;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class AddEmAllForge {
    public static final String MOD_ID = "addemall";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab TAB_ADDEMALL = new CreativeModeTab(Constants.MOD_ID + ".tab") {
      @Override
      public ItemStack makeIcon() {
          return new ItemStack(Items.DIAMOND);
      }
    };

    // Very Important Comment
    public AddEmAllForge() {
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello world!");
        LOGGER.info("Hello Forge World!");
        CommonClass.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    private void onItemTooltip(ItemTooltipEvent event) {
        CommonClass.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }
}
