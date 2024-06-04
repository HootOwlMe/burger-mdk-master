package net.hootowlme.burgermod.item.custom;

import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BurgerSwordItem extends SwordItem {

    public BurgerSwordItem(Tier tier, int damage, float speed, Properties pProperties) {

        super(tier,damage,speed,pProperties);
    }



    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Player player = pContext.getPlayer();
        BlockPos positionClicked = pContext.getClickedPos();
        BlockState stateUnderPlayer = pContext.getLevel().getBlockState(player.blockPosition().below());
        BlockState stateWhereClicked = pContext.getLevel().getBlockState(positionClicked);
        BlockState stateAboveWhereClicked = pContext.getLevel().getBlockState(positionClicked.above());
        BlockState state2AboveWhereClicked = pContext.getLevel().getBlockState(positionClicked.above().above());

        int xPos = positionClicked.getX();
        int yPos = positionClicked.getY();
        int zPos = positionClicked.getZ();
        boolean teleportedSuccessfully = false;

        if(!pContext.getLevel().isClientSide()){

            pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,3));
            pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.JUMP,100,2));


            if (!player.isCrouching()){

                if(!(stateWhereClicked.is(Blocks.BEDROCK) || stateAboveWhereClicked.is(Blocks.BEDROCK) || state2AboveWhereClicked.is(Blocks.BEDROCK))){
                    pContext.getPlayer().teleportTo(xPos + 0.5, yPos + 1, zPos + 0.5);

                    teleportedSuccessfully = true;
                }

            }else{

                //works for sizes one less than the value that i <=
                for(int i = 1; i <= 11; i++){

                    if(pContext.getLevel().getBlockState(player.blockPosition().below(i)).is(Blocks.AIR) || pContext.getLevel().getBlockState(player.blockPosition().below(i)).is(Blocks.CAVE_AIR)){
                        pContext.getPlayer().teleportTo(player.getX(), player.blockPosition().below(i).getY()-0.5, player.getZ());
                        teleportedSuccessfully = true;

                        break;
                    }

                }

            }

        }


        pContext.getPlayer().playSound(SoundEvents.BEACON_ACTIVATE);

        if(teleportedSuccessfully){
            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
            teleportedSuccessfully = false;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("the power of the burger is held within this sword").withStyle(ChatFormatting.getById(5)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



}