package org.thinkingstudio.tweakerge;

import fi.dy.masa.tweakeroo.Reference;
import fi.dy.masa.tweakeroo.Tweakeroo;
import fi.dy.masa.tweakeroo.gui.GuiConfigs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import org.thinkingstudio.mafglib.util.NeoUtils;

@Mod(value = Reference.MOD_ID, dist = Dist.CLIENT)
public class Tweakerge {
    public Tweakerge(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            Tweakeroo.onInitialize();

            NeoUtils.getInstance().registerConfigScreen(modContainer, (screen) -> {
                GuiConfigs gui = new GuiConfigs();
                gui.setParent(screen);
                return gui;
            });
        }
    }
}
