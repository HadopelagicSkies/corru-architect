package com.corru_architect.mixin;

import com.corru_architect.CorruArchitect;
import com.corru_architect.RecipeUnlockMapping;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {
	@WrapOperation(method = "updateResult",at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/CraftingResultInventory;shouldCraftRecipe(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/recipe/RecipeEntry;)Z"))
	private static boolean restrictLockedCrafts(CraftingResultInventory instance, ServerPlayerEntity serverPlayerEntity, RecipeEntry recipeEntry, Operation<Boolean> original) {
		if(recipeEntry.value() instanceof ShapedRecipe shaped){
			if(RecipeUnlockMapping.advancementNameMap.containsKey(shaped.result.getItem())){
				String puzzleName = RecipeUnlockMapping.advancementNameMap.get(shaped.result.getItem());
				return  serverPlayerEntity.getAdvancementTracker().getProgress(serverPlayerEntity.getServer().getAdvancementLoader().get(Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName))).isDone();
			}
		} else if(recipeEntry.value()instanceof ShapelessRecipe shapeless){
			if(RecipeUnlockMapping.advancementNameMap.containsKey(shapeless.result.getItem())){
				String puzzleName = RecipeUnlockMapping.advancementNameMap.get(shapeless.result.getItem());
				return  serverPlayerEntity.getAdvancementTracker().getProgress(serverPlayerEntity.getServer().getAdvancementLoader().get(Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName))).isDone();
			}
		}
		return original.call(instance,serverPlayerEntity,recipeEntry);
	}
}