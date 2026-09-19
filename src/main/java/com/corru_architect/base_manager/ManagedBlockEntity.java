package com.corru_architect.base_manager;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ManagedBlockEntity extends BlockEntity {
    public static BlockEntityTicker<ManagedBlockEntity> ManagedBlockEntityTicker = new ManagedBlockEntity.ManagedBlockTicker<>();

    private final boolean toggleable;
    private boolean active;
    private final BlockPos managerBlock;

    public ManagedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, boolean toggleable, BlockPos managerBlock) {
        super(type, pos, state);
        this.toggleable = toggleable;
        this.managerBlock = managerBlock;
        this.active = !toggleable; //initial state, always on if not toggleable, initially inactive if toggleable
    }

    public BlockPos getManagerBlock() {
        return managerBlock;
    }

    public boolean getActivity(){
        return active;
    }

    public boolean getToggleable(){
        return toggleable;
    }

    public void toggleActivity(){
        if(this.toggleable)
            this.active=!this.active;
    }



    private static class ManagedBlockTicker<V extends ManagedBlockEntity> implements BlockEntityTicker<V> {
        @Override
        public void tick(World world, BlockPos pos, BlockState state, V blockEntity) {

        }
    }
}
