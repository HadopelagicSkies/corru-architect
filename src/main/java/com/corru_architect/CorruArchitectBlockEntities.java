package com.corru_architect;

import com.corru_architect.blocks.GroundsmindryCore.GroundmindsryCoreEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CorruArchitectBlockEntities {

    public static void initialize() {
    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CorruArchitect.MOD_ID, path), blockEntityType);
    }

    public static BlockEntityType<GroundmindsryCoreEntity> GROUNDSMINDRY_CORE_BLOCK_ENTITY = register("groundsmindry_core", FabricBlockEntityTypeBuilder.create(GroundmindsryCoreEntity::new,CorruArchitectBlocks.GROUNDSMINDRY_CORE).build());


}
