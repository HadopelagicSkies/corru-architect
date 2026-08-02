package com.corru_architect.base_manager;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseManager {

    //only intended for use on classes extending BlockEntity
    List<BlockPos> linkedBlocks = new ArrayList<>();

    Map<BaseResourceType,Integer> resourceMeters = HashMap.newHashMap(BaseResourceType.getResourceTypeList().size());
    Map<BaseResourceType,Integer> resourceCapacity = HashMap.newHashMap(BaseResourceType.getResourceTypeList().size());

    default void addLinkedBlock(BlockPos blockPos){
        if (!linkedBlocks.contains(blockPos)){
            linkedBlocks.add(blockPos);
        }
    }

    default void removeLinkedBlock(BlockPos blockPos){
        linkedBlocks.remove(blockPos);
    }


}
