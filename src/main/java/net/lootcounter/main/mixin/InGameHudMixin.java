package net.lootcounter.main.mixin;

import net.lootcounter.main.CounterInit;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	Text newMessage;
	private Text overlayMessage;
	private int overlayRemaining;
	private boolean overlayTinted;

	@Inject(method = "Lnet/minecraft/client/gui/hud/InGameHud;setOverlayMessage(Lnet/minecraft/text/Text;Z)V", at = @At("HEAD"), cancellable = true)
	void setOverlayMessage(Text message, boolean tinted, CallbackInfo ci) {
		CounterInit.SetCounter();
		if(message.getString().equals("+1 Chest added to lootroom.")) {
			CounterInit.counter ++;
			newMessage = Text.of("§6+1 Chest "+"§cadded to lootroom."+" §6"+CounterInit.counter+"/"+CounterInit.maxchests);
			CounterInit.LOGGER.info("+1 Chest "+"added to lootroom. "+CounterInit.counter+"/"+CounterInit.maxchests);
			CounterInit.setTitle(newMessage);
			ci.cancel();
	        return;
		}
		else {
	        this.overlayMessage = message;
	        this.overlayRemaining = 60;
	        this.overlayTinted = tinted;
		};
		return;
	}
}
