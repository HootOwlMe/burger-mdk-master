package net.hootowlme.burgermod.block.custom;


import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.item.ModFoods;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BurgerSoundBlock extends Block {

    public BurgerSoundBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        SoundEvent burp = SoundEvents.PLAYER_BURP;
        pLevel.playSound(pPlayer, pPos, burp, SoundSource.BLOCKS,1f,1f);
        pPlayer.getFoodData().setFoodLevel(pPlayer.getFoodData().getFoodLevel() + 2);

        return InteractionResult.SUCCESS;
    }
}
