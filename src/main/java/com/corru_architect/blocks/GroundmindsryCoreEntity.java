package com.corru_architect.blocks;

import com.corru_architect.CorruArchitectBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GroundmindsryCoreEntity extends BlockEntity {
    public static BlockEntityTicker<GroundmindsryCoreEntity> groundmindsryCoreEntityTicker = new GroundsmindryCoreEntityTicker();

    private boolean growing;
    private int age;
    private float radius;
    private int height;
    private int maxHeight;
    private int maxRadius;

    public GroundmindsryCoreEntity(BlockPos pos, BlockState state) {
        super(CorruArchitectBlockEntities.GROUNDSMINDRY_CORE_BLOCK_ENTITY, pos, state);
        age = 0;
        radius = 0;
        height = -1;
        maxHeight = 0;
        maxRadius = 0;
        growing = false;
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    public boolean getGrowing() {
        return this.growing;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        this.maxRadius = maxHeight / 2;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public static class GroundsmindryCoreEntityTicker implements BlockEntityTicker<GroundmindsryCoreEntity> {
        @Override
        public void tick(World world, BlockPos pos, BlockState state, GroundmindsryCoreEntity blockEntity) {
            if (!world.isClient) {
                tickGrowth(world, pos, state, blockEntity);
            }
        }

        public void tickGrowth(World world, BlockPos pos, BlockState state, GroundmindsryCoreEntity blockEntity){
            if (blockEntity.growing) {
                blockEntity.age++;
                if (blockEntity.age % 2 == 0) {
                    List<BlockPos> layerBlocks = new ArrayList<>();
                    for (int i = (int) (-1 * blockEntity.radius); i < blockEntity.radius; i++) {
                        for (int j = (int) (-1 * blockEntity.radius); j < blockEntity.radius; j++) {
                            BlockPos placingPos = new BlockPos(pos.getX() + i, pos.getY() + blockEntity.height, pos.getZ() + j);
                            if (world.getBlockState(placingPos).isAir() && placingPos.isWithinDistance(pos.add(0,blockEntity.height,0), blockEntity.radius)) { // switch to interpolate max per layer
                                if (!(placingPos.getX() <= pos.getX() + 1 &&
                                        placingPos.getX() >= pos.getX() - 1 &&
                                        placingPos.getY() <= pos.getY() + 2 &&
                                        placingPos.getY() > pos.getY() - 1 &&
                                        placingPos.getZ() <= pos.getZ() + 1)) //this stuff is for sectioning off a tunnel, will do better later
                                    layerBlocks.add(placingPos);
                            }
                        }
                    }
                    double lowestDist = Double.MAX_VALUE;
                    BlockPos closestPos = new BlockPos(0, 0, 0);
                    for (BlockPos checkingPos : layerBlocks) {
                        if (checkingPos.getSquaredDistance(pos) <= lowestDist) {
                            lowestDist = checkingPos.getSquaredDistance(pos);
                            closestPos = checkingPos;
                        }
                    }
                    if (layerBlocks.isEmpty()) {
                        blockEntity.radius += 0.5F;
                        if (blockEntity.radius >= blockEntity.maxRadius) { // switch to interpolate max per layer
                            blockEntity.radius = blockEntity.maxRadius;

                            if (blockEntity.height >= blockEntity.maxHeight) {
                                blockEntity.height = blockEntity.maxHeight;
                                blockEntity.setGrowing(false);
                            } else {
                                blockEntity.height++;
                                blockEntity.radius = 0;
                            }
                        }
                    }
                    world.setBlockState(closestPos, Blocks.DIAMOND_BLOCK.getDefaultState());
                }
            }
        }
    }
}