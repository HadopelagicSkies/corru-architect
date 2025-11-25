package com.corru_architect.datagen;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectBlocks;
import com.corru_architect.CorruArchitectItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CorruArchitectModAdvancementProvider extends FabricAdvancementProvider {
    protected CorruArchitectModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        CorruArchitectBlocks.ARCHIVE_CORRUCYST, // The display icon
                        Text.literal("A Mysterious... Marble?"), // The title
                        Text.literal("Find a crash-landed Archive Corrucyst"), // The description
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("archive_found",
                        InventoryChangedCriterion.Conditions.items(CorruArchitectBlocks.ARCHIVE_CORRUCYST))
                .build(consumer, CorruArchitect.MOD_ID + "/root");

        AdvancementEntry mindspikeAdvancement = Advancement.Builder.create()
                .display(
                        CorruArchitectItems.MINDSPIKE, // The display icon
                        Text.literal("ATTENTION::'mindspike logging active'"), // The title
                        Text.literal("Construct a Mindspike and Mindspike Helmet, and attempt connection to the Archive Corrucyst"), // The description
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("mindspike_held",
                        InventoryChangedCriterion.Conditions.items(CorruArchitectItems.MINDSPIKE,CorruArchitectItems.MINDSPIKE_HELMET))
                .criterion("mindspike_connect",ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(wrapperLookup.getOrThrow(RegistryKeys.BLOCK),CorruArchitectBlocks.ARCHIVE_CORRUCYST)),
                        ItemPredicate.Builder.create().items(wrapperLookup.getOrThrow(RegistryKeys.ITEM),CorruArchitectItems.MINDSPIKE)))
                .parent(rootAdvancement)
                .build(consumer, CorruArchitect.MOD_ID + "/mindspike_connect");

        AdvancementEntry pedestalAdvancement = Advancement.Builder.create()
                .display(
                        CorruArchitectBlocks.ARCHIVE_CORRUCYST, // The display icon
                        Text.literal("Schematic Recovery"), // The title
                        Text.literal("Repair the schematic for the Pedestal"), // The description
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
                        AdvancementFrame.CHALLENGE , // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("pedestal_unlocked",
                        RecipeUnlockedCriterion.create(RegistryKey.of(RegistryKeys.RECIPE,Identifier.of(CorruArchitect.MOD_ID, "pedestal"))))
                .parent(mindspikeAdvancement)
                .build(consumer, CorruArchitect.MOD_ID + "/pedestal_schematic");
    }
}
