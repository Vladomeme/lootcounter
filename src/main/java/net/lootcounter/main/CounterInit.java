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
	public static int floorCounter;
	public static String maxchests;
	public static final Logger LOGGER = LoggerFactory.getLogger("lootcounter");
	static PlayerEntity player;
	public static String currentDimension = "none";

	@Override
	public void onInitialize() {
		LOGGER.info("Loot Counter enabled!");
		ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("chests")
			.executes(CounterInit::Chests)
		);
	}
	
	public static void ResetCounter (){
		counter = 0;
		LOGGER.info("Counter set to 0");
	}
	
	public static void ResetFloorCounter (){
		floorCounter = 0;
		LOGGER.info("Floor counter set to 0");
	}
	
	static public int Chests(CommandContext<FabricClientCommandSource> context) {
		PlayerEntity player = MinecraftClient.getInstance().player;
		if(MinecraftClient.getInstance().player != null) {
			switch (currentDimension) {
				case "sanctum":{
					player.sendMessage(new LiteralText ("§7Current Strike : §aForsworn Sanctum"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "verdant":{
					player.sendMessage(new LiteralText ("§7Current Strike : §2Verdant Remnants"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "mist":{
					player.sendMessage(new LiteralText ("§7Current Strike : §1The Black Mist"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}
				break;
				case "remorse":{
					player.sendMessage(new LiteralText ("§7Current Strike : §cSealed Remorse"), false);
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests), false);
				}	
				break;
				case "corridors":{
					if(maxchests.equals("∞")) {
						player.sendMessage(new LiteralText ("§7Current Dungeon : §6Ephemeral Corridors (Endless)"), false);
					}
					if(maxchests.equals("21")) {
						player.sendMessage(new LiteralText ("§7Current Dungeon : §6Ephemeral Corridors"), false);
					}
					player.sendMessage(new LiteralText ("§7Chests : §6"+CounterInit.counter+"/"+CounterInit.maxchests+" ("+CounterInit.floorCounter+"/7 on this floor)"), false);
					
				}
				break;
				case "none":{
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
}
