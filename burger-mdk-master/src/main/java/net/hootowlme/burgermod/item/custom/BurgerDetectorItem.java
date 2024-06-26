package net.hootowlme.burgermod.item.custom;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.sound.ModSounds;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BurgerDetectorItem extends Item {

    public BurgerDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(!pContext.getLevel().isClientSide()){

            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++){

                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(player.isCrouching()){

                    if(isValuableBlock(state)){

                        outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                        foundBlock = true;

                        pContext.getLevel().playSeededSound(null, positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                                ModSounds.BURGER_DETECTOR_FOUND.get(), SoundSource.BLOCKS, 1.5f, 1f, 0);

                        break;

                    }

                }else{
                    if(isBurgerBlock(state)){

                        outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                        foundBlock = true;

                        pContext.getLevel().playSeededSound(null, positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                                ModSounds.BURGER_DETECTOR_FOUND.get(), SoundSource.BLOCKS, 1.5f, 1f, 0);

                        break;

                    }
                }



            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No Burgers Found :("));
            }

        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Burger Ore Below You? This will tell you!").withStyle(ChatFormatting.getById(4)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {

        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
        "(" + blockPos.getX() + "," + blockPos.getY() + "," +blockPos.getZ() + ")"));

    }

    private boolean isValuableBlock(BlockState state) {

        return state.is(ModTags.Blocks.BURGER_DETECTOR_VALUABLES);

    }
    private boolean isBurgerBlock(BlockState state) {

        return state.is(ModTags.Blocks.BURGER_DETECTOR);

    }
}