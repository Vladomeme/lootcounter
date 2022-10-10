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
		String chatmessage = message.getString();
		if(chatmessage.startsWith("Transferring you to")) {
			CounterInit.ResetCounter();
			CounterInit.currentDimension= "none";
			CounterInit.ResetFloorCounter();
			switch(chatmessage) {
			case "Transferring you to verdant":{
				CounterInit.currentDimension="verdant";
				CounterInit.maxchests="42";
			}
			break;
			case "Transferring you to sanctum":{
				CounterInit.currentDimension="sanctum";
				CounterInit.maxchests="65";
			}
			break;
			case "Transferring you to corridors":{
				CounterInit.currentDimension="corridors";
			}
			break;
			case "Transferring you to mist":{
				CounterInit.currentDimension="mist";
				CounterInit.maxchests="40";
			}
			break;
			case "Transferring you to remorse":{
				CounterInit.currentDimension="remorse";
				CounterInit.maxchests="62";
			}
			break;
			}
		}
		switch(chatmessage) {
		case "[-_-] Is this the run that never ends? Yes it goes on and on my friend.": {
			CounterInit.maxchests="∞";
		}
		break;
		case "[-_-] Getting saved is standard now? Adventurers these days...": {
			CounterInit.maxchests="21";
		}
		break;
		case "[-_-] Practice makes perfect. Take all the time you need.": {
			CounterInit.maxchests="21";
		}
		break;
		}
		if(chatmessage.startsWith("[Corridors] You have advanced")) {
			CounterInit.ResetFloorCounter();
		}
	}
}
