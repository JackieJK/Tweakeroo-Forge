package fi.dy.masa.tweakeroo.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.server.command.CloneCommand;
import fi.dy.masa.tweakeroo.config.Configs;
import fi.dy.masa.tweakeroo.config.FeatureToggle;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CloneCommand.class, priority = 999)
public abstract class MixinCloneCommand
{
    @Redirect(method = "execute", require = 0,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules;getInt(Lnet/minecraft/world/GameRules$Key;)I"))
    private static int tweakeroo_overrideBlockLimit(GameRules instance, GameRules.Key<GameRules.IntRule> rule)
    {
        if (FeatureToggle.TWEAK_FILL_CLONE_LIMIT.getBooleanValue())
        {
            return Configs.Generic.FILL_CLONE_LIMIT.getIntegerValue();
        }

        return instance.getInt(rule);
    }
}
