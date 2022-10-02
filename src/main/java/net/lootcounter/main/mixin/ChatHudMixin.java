package net.lootcounter.main.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.lootcounter.main.CounterInit;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;

@Mixin(ChatHud.class)
public class ChatHudMixin {
	@Inject(method = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
	void addMessage(Text message, CallbackInfo ci) {
		switch(message.getString()) {
		case "[-_-] Is this the run that never ends? Yes it goes on and on my friend.": {
			CounterInit.maxchests="∞";
			CounterInit.ResetCounter();
		}
		break;
		case "[-_-] Getting saved is standard now? Adventurers these days...": {
			CounterInit.maxchests="21";
			CounterInit.ResetCounter();
		}
		break;
		case "[-_-] Practice makes perfect. Take all the time you need.": {
			CounterInit.maxchests="21";
			CounterInit.ResetCounter();
		}
		break;
		}
		if(message.getString().startsWith("Transferring you to")) {
			CounterInit.ResetCounter();
			CounterInit.ResetFloorCounter();
		}
		if(message.getString().startsWith("[Corridors] You have advanced")) {
			CounterInit.ResetFloorCounter();
		}
	}
}
