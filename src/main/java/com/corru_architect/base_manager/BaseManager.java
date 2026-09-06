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
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.*;

public class BaseManager extends BlockEntity{
    public static BlockEntityTicker<BaseManager> baseManagerBlockEntityTicker = new BaseManagerTicker<>();

    public List<BlockPos> linkedBlocks = new ArrayList<>();
    public Map<BaseResourceType,Integer> resourceMeters = HashMap.newHashMap(BaseResourceType.getResourceTypeList().size());
    public Map<BaseResourceType,Integer> resourceCapacity = HashMap.newHashMap(BaseResourceType.getResourceTypeList().size());

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
        nbt.put("resourceMeters", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).encode(this.resourceMeters, ops, nbt).getOrThrow(RuntimeException::new));
        nbt.put("resourceCapacity", Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).encode(this.resourceCapacity, ops, nbt).getOrThrow(RuntimeException::new));

        List<Vector3f> linkedBlocksAsVector = new ArrayList<>(List.of());
        this.linkedBlocks.forEach(blockPos -> linkedBlocksAsVector.add(new Vector3f(blockPos.getX(),blockPos.getY(),blockPos.getZ())));
        nbt.put("linkedBlocks", Codec.list(Codecs.VECTOR_3F).encode(linkedBlocksAsVector, ops, nbt).getOrThrow(RuntimeException::new));
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        RegistryOps<NbtElement> ops = registries.getOps(NbtOps.INSTANCE);
        DataResult<Map<BaseResourceType, Integer>> resourceMetersResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).parse(ops, nbt.getCompound("resourceMeters"));
        DataResult<Map<BaseResourceType, Integer>> resourceCapacityResult = Codec.unboundedMap(BaseResourceType.BASE_RESOURCE_TYPE_CODEC,Codec.INT).parse(ops, nbt.getCompound("resourceCapacity"));
        DataResult<List<Vector3f>> linkedBlocksResult = Codec.list(Codecs.VECTOR_3F).parse(ops, nbt.getCompound("linkedBlocks"));

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
            blockEntity.resourceMeters.put(CorruBaseResources.corruResource,5);
            for (int i = 0; i < blockEntity.linkedBlocks.size(); i++) {
                world.getBlockEntity(blockEntity.linkedBlocks.get(i));
            }
        }
    }

}
