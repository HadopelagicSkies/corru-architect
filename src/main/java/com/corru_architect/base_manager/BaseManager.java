package com.corru_architect.base_manager;

import com.corru_architect.CorruArchitect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.*;

public class BaseManager extends BlockEntity{
    public static BlockEntityTicker<BaseManager> baseManagerBlockEntityTicker = new BaseManagerTicker<>();

    public List<BlockPos> linkedBlocks = new ArrayList<>();
    public Map<BaseResourceType,Integer> resourceMeters = new HashMap<>();
    public Map<BaseResourceType,Integer> resourceCapacity = new HashMap<>();

    public BaseManager(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        BaseResourceType.getResourceTypeList().forEach((baseResourceType)-> resourceMeters.put(baseResourceType, 0));
        BaseResourceType.getResourceTypeList().forEach((baseResourceType)-> resourceCapacity.put(baseResourceType, baseResourceType.getInitCapacity()));
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
        Map<RegistryEntry<BaseResourceType>, Integer> meterEntryMap = new HashMap<>();
        Map<RegistryEntry<BaseResourceType>, Integer> capacityEntryMap = new HashMap<>();
        this.resourceMeters.forEach((type, integer) -> meterEntryMap.put(type.getRegistryEntry(),integer));
        this.resourceCapacity.forEach((type, integer) -> capacityEntryMap.put(type.getRegistryEntry(),integer));
        nbt.put("resource_meters", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(),Codec.INT).encode(meterEntryMap, ops, nbt).getOrThrow(RuntimeException::new));
        nbt.put("resource_capacity", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_REGISTRY.getEntryCodec(), Codec.INT).encode(capacityEntryMap, ops, nbt).getOrThrow(RuntimeException::new));
        List<Vector3f> linkedBlocksAsVector = new ArrayList<>(List.of());
        this.linkedBlocks.forEach(blockPos -> linkedBlocksAsVector.add(new Vector3f(blockPos.getX(),blockPos.getY(),blockPos.getZ())));
        nbt.put("linked_blocks", Codec.list(Codecs.VECTOR_3F).encode(linkedBlocksAsVector, ops, nbt).getOrThrow(RuntimeException::new));
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        RegistryOps<NbtElement> ops = registries.getOps(NbtOps.INSTANCE);
        DataResult<Map<BaseResourceType, Integer>> resourceMetersResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).parse(ops, nbt.getCompound("resource_meters"));
        DataResult<Map<BaseResourceType, Integer>> resourceCapacityResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).parse(ops, nbt.getCompound("resource_capacity"));
        DataResult<List<Vector3f>> linkedBlocksResult = Codec.list(Codecs.VECTOR_3F).parse(ops, nbt.getCompound("linked_blocks"));

        if (resourceMetersResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Resource Meters from NBT: {}", resourceMetersResult.error().get());
            BaseResourceType.getResourceTypeList().forEach((baseResourceType)-> resourceMeters.put(baseResourceType, 0));
        } else {
            this.resourceMeters = resourceMetersResult.result().orElseThrow();
        }

        if (resourceCapacityResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Resource Capacities from NBT: {}", resourceCapacityResult.error().get());

        } else {
            this.resourceCapacity = resourceCapacityResult.result().orElseThrow();
        }

        if (linkedBlocksResult.error().isPresent()) {
            CorruArchitect.LOGGER.debug("Failed to load Base Manager Linked Blocks from NBT: {}", linkedBlocksResult.error().get());
            this.linkedBlocks = new ArrayList<>();
            Map<BaseResourceType,Integer> initCapacities = new HashMap<>();
            BaseResourceType.getResourceTypeList().forEach((baseResourceType)-> initCapacities.put(baseResourceType, baseResourceType.getInitCapacity()));
            this.resourceCapacity = initCapacities;
        } else {
            this.linkedBlocks = new ArrayList<>();
            linkedBlocksResult.getOrThrow().forEach(vecPos -> this.linkedBlocks.add(new BlockPos((int) vecPos.x(), (int) vecPos.y(), (int) vecPos.z())));
        }
    }

    public static class BaseManagerTicker<V extends BaseManager> implements BlockEntityTicker<V>{
        @Override
        public void tick(World world, BlockPos pos, BlockState state, V blockEntity) {
            blockEntity.resourceMeters.put(CorruBaseResources.soilResource,5);
            for (int i = 0; i < blockEntity.linkedBlocks.size(); i++) {
                world.getBlockEntity(blockEntity.linkedBlocks.get(i));
            }
        }
    }

}
