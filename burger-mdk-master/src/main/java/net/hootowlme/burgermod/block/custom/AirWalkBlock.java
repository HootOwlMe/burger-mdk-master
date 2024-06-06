//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hootowlme.burgermod.block.custom;

import net.hootowlme.burgermod.block.ModBlocks;
import net.minecraft.core.BlockPos;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.Block;


import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;
import net.minecraftforge.fml.common.Mod;


public class AirWalkBlock extends GlassBlock {


    public AirWalkBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        //pLevel.removeBlock(pPos,pIsMoving);

        pLevel.scheduleTick(pPos, ModBlocks.AIR_WALK_BLOCK.get(), 60, TickPriority.HIGH);

    }


    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        if(pLevel.isClientSide()){
            if(pLevel.getBlockState(pPos).is(ModBlocks.AIR_WALK_BLOCK.get())){
                pLevel.scheduleTick(pPos, ModBlocks.AIR_WALK_BLOCK.get(),0, TickPriority.HIGH);
            }
        }

        pLevel.removeBlock(pPos,true);

    }

}
