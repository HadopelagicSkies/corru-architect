package com.corru_architect;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.recipe.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RecipeUnlockMapping {
    public static Map<Item, String> advancementNameMap = new HashMap<>();

    public static void putAdvancementName(Item item, String name){
        advancementNameMap.put(item,name);
    }
    public static void putAdvancementName(Block block, String name){
        advancementNameMap.put(block.asItem(),name);
    }

    public static String getUnlockName(Item item){
        return advancementNameMap.get(item);
    }
    public static String getUnlockName(Block block){
        return advancementNameMap.get(block.asItem());
    }

    public static void grantAdvancement(ServerPlayerEntity player, String puzzleName){
        Item puzzleItem = null;
        for(Map.Entry<Item, String> entry: RecipeUnlockMapping.advancementNameMap.entrySet()){
            if(entry.getValue().equals(puzzleName)){
                puzzleItem = entry.getKey();
                break;
            }
        }

        for(RecipeEntry<CraftingRecipe> recipe :player.server.getRecipeManager().getAllOfType(RecipeType.CRAFTING)){
            if (recipe.value() instanceof ShapelessRecipe shapelessRecipe){
                if(shapelessRecipe.result.isOf(puzzleItem)){
                    player.getRecipeBook().unlock(recipe.id());
                    CorruArchitect.LOGGER.info("unlocked recipe" + recipe.id());
                    CorruArchitect.LOGGER.info("unlocked advancement " + Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName));
                    player.getAdvancementTracker().grantCriterion(player.getServer().getAdvancementLoader().get(Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName)), puzzleName);
                    break;
                }
            } else if (recipe.value() instanceof ShapedRecipe shapedRecipe){
                if(shapedRecipe.result.isOf(puzzleItem)){
                    player.getRecipeBook().unlock(recipe.id());
                    CorruArchitect.LOGGER.info("unlocked recipe " + recipe.id());
                    CorruArchitect.LOGGER.info("unlocked advancement " + Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName));
                    player.getAdvancementTracker().grantCriterion(player.getServer().getAdvancementLoader().get(Identifier.of(CorruArchitect.MOD_ID +"/"+ puzzleName)), puzzleName);
                    break;
                }
            }
        }
    }
}
