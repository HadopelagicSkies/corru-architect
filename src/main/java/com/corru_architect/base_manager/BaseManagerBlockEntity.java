package com.corru_architect.base_manager;

import com.corru_architect.CorruArchitect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.*;

public class BaseManagerBlockEntity extends BlockEntity{
    public static BlockEntityTicker<BaseManagerBlockEntity> baseManagerBlockEntityTicker = new BaseManagerTicker<>();

    public List<BlockPos> linkedBlocks = new ArrayList<>();
    public Map<BaseResourceType,Float> resourceMeters = new HashMap<>();
    public Map<BaseResourceType,Float> resourceCapacity = new HashMap<>();
    public Map<BaseResourceType,Float> resourceMultiplier = new HashMap<>();

    public BaseManagerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        for(RegistryKey<BaseResourceType> resourceKey :BaseResourceType.BASE_RESOURCE_REGISTRY.getKeys()) {
            BaseResourceType resourceType = BaseResourceType.BASE_RESOURCE_REGISTRY.get(resourceKey);
            if(resourceType!= null && resourceType.getManagerBlock().equals(state.getBlock())){
                resourceMeters.put(resourceType, 0F);
                resourceCapacity.put(resourceType, resourceType.getInitCapacity());
            }
        }
    }

    public void addLinkedBlock(BlockPos blockPos){
        if (!linkedBlocks.contains(blockPos)){
            linkedBlocks.add(blockPos);
        }
    }

    public void removeLinkedBlock(BlockPos blockPos){
        linkedBlocks.remove(blockPos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        RegistryOps<NbtElement> ops = registries.getOps(NbtOps.INSTANCE);
        Map<RegistryEntry<BaseResourceType>, Float> meterEntryMap = new HashMap<>();
        Map<RegistryEntry<BaseResourceType>, Float> capacityEntryMap = new HashMap<>();
        Map<RegistryEntry<BaseResourceType>, Float> multEntryMap = new HashMap<>();
        this.resourceMeters.forEach((type, value) -> meterEntryMap.put(type.getRegistryEntry(),value));
        this.resourceCapacity.forEach((type, value) -> capacityEntryMap.put(type.getRegistryEntry(),value));
        this.resourceMultiplier.forEach((type, value) -> multEntryMap.put(type.getRegistryEntry(),value));
        nbt.put("resource_meters", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(),Codec.FLOAT).encodeStart(ops,meterEntryMap).getOrThrow(RuntimeException::new));
        nbt.put("resource_capacity", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(), Codec.FLOAT).encodeStart(ops,capacityEntryMap).getOrThrow(RuntimeException::new));
        nbt.put("resource_mult", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(), Codec.FLOAT).encodeStart(ops,multEntryMap).getOrThrow(RuntimeException::new));
        List<Vector3f> linkedBlocksAsVector = new ArrayList<>(List.of());
        this.linkedBlocks.forEach(blockPos -> linkedBlocksAsVector.add(new Vector3f(blockPos.getX(),blockPos.getY(),blockPos.getZ())));
        nbt.put("linked_blocks", Codec.list(Codecs.VECTOR_3F).encodeStart(ops,linkedBlocksAsVector).getOrThrow(RuntimeException::new));
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        RegistryOps<NbtElement> ops = registries.getOps(NbtOps.INSTANCE);
        DataResult<Map<RegistryEntry<BaseResourceType>, Float>> resourceMetersResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(),Codec.FLOAT).parse(ops, nbt.getCompound("resource_meters"));
        DataResult<Map<RegistryEntry<BaseResourceType>, Float>> resourceCapacityResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(),Codec.FLOAT).parse(ops, nbt.getCompound("resource_capacity"));
        DataResult<Map<RegistryEntry<BaseResourceType>, Float>> resourceMultResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(),Codec.FLOAT).parse(ops, nbt.getCompound("resource_mult"));
        DataResult<List<Vector3f>> linkedBlocksResult = Codec.list(Codecs.VECTOR_3F).parse(ops, nbt.getCompound("linked_blocks"));

        if (resourceMetersResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Resource Meters from NBT: {}", resourceMetersResult.error().get());
        } else {
            Map<BaseResourceType, Float> meterEntryMap = new HashMap<>();
            resourceMetersResult.result().orElseThrow().forEach((type, value) -> meterEntryMap.put(type.value(),value));
            this.resourceMeters = meterEntryMap;
        }

        if (resourceCapacityResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Resource Capacities from NBT: {}", resourceCapacityResult.error().get());
        } else {
            Map<BaseResourceType, Float> capacityEntryMap = new HashMap<>();
            resourceCapacityResult.result().orElseThrow().forEach((type, value) -> capacityEntryMap.put(type.value(),value));
            this.resourceCapacity = capacityEntryMap;
        }

        if (resourceMultResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Resource Multipliers from NBT: {}", resourceCapacityResult.error().get());
        } else {
            Map<BaseResourceType, Float> multEntryMap = new HashMap<>();
            resourceMultResult.result().orElseThrow().forEach((type, value) -> multEntryMap.put(type.value(),value));
            this.resourceMultiplier = multEntryMap;
        }

        if (linkedBlocksResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Manager Linked Blocks from NBT: {}", linkedBlocksResult.error().get());
            this.linkedBlocks = new ArrayList<>();
        } else {
            this.linkedBlocks = new ArrayList<>();
            linkedBlocksResult.getOrThrow().forEach(vecPos -> this.linkedBlocks.add(new BlockPos((int) vecPos.x(), (int) vecPos.y(), (int) vecPos.z())));
        }
    }

    public static class BaseManagerTicker<V extends BaseManagerBlockEntity> implements BlockEntityTicker<V>{
        @Override
        public void tick(World world, BlockPos pos, BlockState state, V blockEntity) {
            blockEntity.resourceMeters.put(CorruBaseResources.soilResource,5F);
            for (int i = 0; i < blockEntity.linkedBlocks.size(); i++) {
                world.getBlockEntity(blockEntity.linkedBlocks.get(i));
            }
        }
    }

}
