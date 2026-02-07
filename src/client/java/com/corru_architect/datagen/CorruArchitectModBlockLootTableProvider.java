package com.corru_architect.datagen;

import com.corru_architect.CorruArchitectBlocks;
import com.corru_architect.CorruArchitectItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class CorruArchitectModBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected CorruArchitectModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(CorruArchitectBlocks.WILD_CORRU, CorruArchitectItems.WILD_CORRU_CHUNK);
        addDropWithSilkTouch(CorruArchitectBlocks.WILD_CORRU);
        addDrop(CorruArchitectBlocks.ARCHIVE_CORRUCYST);
        addDrop(CorruArchitectBlocks.CYSTIC_COLUMN);
        addDrop(CorruArchitectBlocks.CORRU_SCULPTOR);
        addDrop(CorruArchitectBlocks.SFER_REFINERY);
        addDrop(CorruArchitectBlocks.SFER_BLOCK);



    }
}
