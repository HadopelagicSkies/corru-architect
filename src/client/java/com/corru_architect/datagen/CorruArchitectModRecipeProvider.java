package com.corru_architect.datagen;

import com.corru_architect.CorruArchitectBlocks;
import com.corru_architect.CorruArchitectItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class CorruArchitectModRecipeProvider extends FabricRecipeProvider {

    public CorruArchitectModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup,recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.MISC, CorruArchitectItems.MINDSPIKE, 1)
                        .pattern("grg")
                        .pattern("ici")
                        .pattern("n n")
                        .input('i', Items.IRON_INGOT)
                        .input('n', Items.IRON_NUGGET)
                        .input('g', Items.GOLD_INGOT)
                        .input('r', Items.REDSTONE)
                        .input('c', CorruArchitectBlocks.WILD_CORRU)
                        .criterion(hasItem(CorruArchitectItems.MINDSPIKE), conditionsFromItem(CorruArchitectItems.MINDSPIKE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, CorruArchitectBlocks.PEDESTAL, 1)
                        .pattern("c c")
                        .pattern(" c ")
                        .pattern("ccc")
                        .input('c', CorruArchitectItems.WILD_CORRU_CHUNK)
                        .criterion(hasItem(CorruArchitectBlocks.PEDESTAL), conditionsFromItem(CorruArchitectBlocks.PEDESTAL))
                        .offerTo(exporter);
            }
        };
    }
    @Override
    public String getName() {
        return "corru.architect Mod Recipe Provider";
    }

}
