package net.lootcounter.main.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(PlayerEntity.class)
public class PlayerEntityMixin{

	@Redirect(method = "tickMovement()V", at = @At(value="INVOKE", target="Lnet/minecraft/entity/player/HungerManager;setFoodLevel(I)V"))
	public void tickMovement(HungerManager instance, int foodLevel) {
		PlayerEntity player = MinecraftClient.getInstance().player;
		if(!player.world.getRegistryKey().getValue().toString().startsWith("monumenta")){
			player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + 1);
		}
	}
}
