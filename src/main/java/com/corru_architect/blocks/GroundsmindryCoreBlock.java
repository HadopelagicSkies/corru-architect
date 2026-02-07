package com.corru_architect.blocks;

import com.corru_architect.CorruArchitectBlockEntities;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GroundsmindryCoreBlock extends SimpleTooltipBlock implements BlockEntityProvider {
    public GroundsmindryCoreBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(player.getActiveItem().isOf(Items.AIR)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GroundmindsryCoreEntity groundmindsryCoreEntity){
                if(!groundmindsryCoreEntity.getGrowing()) {
                    groundmindsryCoreEntity.setGrowing(true);
                    groundmindsryCoreEntity.setMaxHeight(groundmindsryCoreEntity.getMaxHeight() + 5);
                    groundmindsryCoreEntity.setRadius(0);
                    groundmindsryCoreEntity.setHeight(-1);
                }
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GroundmindsryCoreEntity(pos,state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type,GroundmindsryCoreEntity.groundmindsryCoreEntityTicker);
    }

    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> validateTicker(BlockEntityType<A> givenType, BlockEntityTicker<? super E> ticker){
        return CorruArchitectBlockEntities.GROUNDSMINDRY_CORE_BLOCK_ENTITY == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

}
