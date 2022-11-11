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
			String dimension = CounterInit.getDimension();
			if(dimension.equals("monumenta:valley") || dimension.equals("monumenta:isles") || dimension.equals("monumenta:ring")) {
				CounterInit.ResetCounter();
				CounterInit.currentDimension= "0";
				CounterInit.ResetFloorCounter();
			}
			switch(chatmessage) {
				case "Transferring you to verdant":{
					CounterInit.currentDimension="1";
					CounterInit.maxchests="42";
				}
				break;
				case "Transferring you to sanctum":{
					CounterInit.currentDimension="2";
					CounterInit.maxchests="65";
				}
				break;
				case "Transferring you to corridors":{
					CounterInit.currentDimension="3";
				}
				break;
				case "Transferring you to mist":{
					CounterInit.currentDimension="4";
					CounterInit.maxchests="42";
				}
				break;
				case "Transferring you to remorse":{
					CounterInit.currentDimension="5";
					CounterInit.maxchests="62";
				}
				break;
			}
			if(chatmessage.startsWith("Transferring you to skt")) {
				CounterInit.currentDimension="6";
				CounterInit.maxchests="96";
			}
			if(chatmessage.startsWith("Transferring you to portal")) {
				CounterInit.currentDimension="7";
				CounterInit.maxchests="41";
			}
			if(chatmessage.startsWith("Transferring you to ruin")) {
				CounterInit.currentDimension="8";
				CounterInit.maxchests="99";
			}
			if(chatmessage.startsWith("Transferring you to valley")) {
				CounterInit.currentDimension= "0";
			}
			if(chatmessage.startsWith("Transferring you to isles")) {
				CounterInit.currentDimension= "0";
			}
			if(chatmessage.startsWith("Transferring you to ring")) {
				CounterInit.currentDimension= "0";
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
