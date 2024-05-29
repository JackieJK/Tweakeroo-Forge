package org.thinkingstudio.tweakerge;

import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.tweakeroo.InitHandler;
import fi.dy.masa.tweakeroo.Reference;
import fi.dy.masa.tweakeroo.gui.GuiConfigs;
import fi.dy.masa.tweakeroo.tweaks.PlacementTweaks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.ForgePlatformUtils;

@Mod(Reference.MOD_ID)
public class Tweakerge {
    public Tweakerge() {
        if (FMLLoader.getDist().isClient()) {
            ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
            InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
            ForgePlatformUtils.getInstance().registerModConfigScreen(Reference.MOD_ID, (screen) -> {
                GuiConfigs gui = new GuiConfigs();
                gui.setParent(screen);
                return gui;
            });

            MinecraftForge.EVENT_BUS.<PlayerDestroyItemEvent>addListener(event -> {
                PlacementTweaks.onProcessRightClickPost(event.getEntity(), event.getHand());
            });
        }
    }
}
