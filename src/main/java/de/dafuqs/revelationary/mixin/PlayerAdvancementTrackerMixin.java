package de.dafuqs.revelationary.mixin;

import de.dafuqs.revelationary.api.advancements.AdvancementCriteria;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementTrackerMixin {
	@Shadow
	private ServerPlayer player;
	
	@Inject(method = "award", at = @At("RETURN"))
	public void revelationary$triggerAdvancementCriteria(AdvancementHolder advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
		AdvancementProgress advancementProgress = ((PlayerAdvancements) (Object) this).getOrStartProgress(advancement);
		if (advancementProgress.isDone()) {
			AdvancementCriteria.ADVANCEMENT_GOTTEN.trigger(player, advancement);
			AdvancementCriteria.ADVANCEMENT_COUNT.trigger(player);
		}
	}
}
