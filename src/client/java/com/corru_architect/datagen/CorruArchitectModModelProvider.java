package com.corru_architect.datagen;

import com.corru_architect.CorruArchitectItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureMap;

public class CorruArchitectModModelProvider extends FabricModelProvider {
    public CorruArchitectModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //blockStateModelGenerator.registerSimpleCubeAll();
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(CorruArchitectItems.MINDSPIKE, Models.GENERATED);
        itemModelGenerator.register(CorruArchitectItems.MINDSPIKE_HELMET, Models.GENERATED);
        itemModelGenerator.register(CorruArchitectItems.WILD_CORRU_CHUNK, Models.GENERATED);
        itemModelGenerator.register(CorruArchitectItems.SFER_CUBE, Models.GENERATED);
        itemModelGenerator.register(CorruArchitectItems.ECHO_CORRUCYST, Models.GENERATED);

    }

    @Override
    public String getName() {
        return "corru.architect Mod Model Provider";
    }

    public void registerStairs(BlockStateModelGenerator blockStateModelGenerator, Block block, Block baseBlock){
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(block,
                Models.INNER_STAIRS.upload(block, TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.STAIRS.upload(block,TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.OUTER_STAIRS.upload(block, TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector)));
    }
    public void registerWall(BlockStateModelGenerator blockStateModelGenerator, Block block, Block baseBlock){
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(block,
                Models.TEMPLATE_WALL_POST.upload(block, TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.TEMPLATE_WALL_SIDE.upload(block,TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.TEMPLATE_WALL_SIDE_TALL.upload(block, TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector)));
        Models.WALL_INVENTORY.uploadWithoutVariant(block,"", TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector);
    }
    public void registerSlab(BlockStateModelGenerator blockStateModelGenerator, Block block, Block baseBlock){
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(block,
                Models.SLAB.upload(block,TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.SLAB_TOP.upload(block,TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector),
                Models.CUBE_ALL.upload(block,"_full",TextureMap.all(baseBlock),blockStateModelGenerator.modelCollector)));
    }
}
