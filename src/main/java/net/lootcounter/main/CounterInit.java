package net.lootcounter.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.text.Text;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

public class CounterInit implements ModInitializer {
	
	public static int counter;
	public static String maxchests;
	public static final Logger LOGGER = LoggerFactory.getLogger("lootcounter");
	static PlayerEntity player;
	public static String currentDimension = null;
	
	@SuppressWarnings("resource")
	public static void SetCounter (){
		if(MinecraftClient.getInstance().player != null) {
			if(!getDimension().equals(currentDimension)) {
				ResetCounter();
			}
			currentDimension = getDimension();
			switch (currentDimension) {
				case "monumenta:sanctum":{
					maxchests = "65";
				}
				break;
				case "monumenta:verdant":{
					maxchests = "42";
				}
				break;
				case "monumenta:mist":{
					maxchests = "40";
				}
				break;
				case "monumenta:remorse":{
					maxchests = "62";
				}
				break;
			};
		}
	}
	public static void ResetCounter (){
		counter = 0;
		LOGGER.info("Counter set to 0");
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Loot Counter enabled!");
		ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("chests")
			.executes(CounterInit::Chests)
		);
	}
	static public int Chests(CommandContext<FabricClientCommandSource> context) {
		PlayerEntity player = MinecraftClient.getInstance().player;
		if(MinecraftClient.getInstance().player != null) {
			switch (CounterInit.getDimension()) {
				case "monumenta:sanctum":{
					player.sendMessage(new LiteralText ("§7Current Strike : §aForsworn Sanctum"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "monumenta:verdant":{
					player.sendMessage(new LiteralText ("§7Current Strike : §2Verdant Remnants"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "monumenta:mist":{
					player.sendMessage(new LiteralText ("§7Current Strike : §1The Black Mist"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "monumenta:remorse":{
					player.sendMessage(new LiteralText ("§7Current Strike : §cSealed Remorse"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}	
				break;
				case "monumenta:corridors":{
					if(maxchests.equals("∞")) {
						player.sendMessage(new LiteralText ("§7Current Dungeon : §6Ephemeral Corridors (Endless)"), false);
						player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
					}
					if(maxchests.equals("21")) {
						player.sendMessage(new LiteralText ("§7Current Dungeon : §6Ephemeral Corridors"), false);
						player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
					}
				}
				break;
				default:{
					player.sendMessage(new LiteralText ("§7Not currently in a Strike!"), false);
				}
				break;
			};	
		}
		return 1;
	
	}
	static public void setTitle(Text message) {
		player = MinecraftClient.getInstance().player;
		player.sendMessage(message, true);
	}
	static public String getDimension() {
		player = MinecraftClient.getInstance().player;
		return player.world.getRegistryKey().getValue().toString();
	}
}
