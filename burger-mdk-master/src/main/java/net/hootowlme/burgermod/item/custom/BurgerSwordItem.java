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


        int xPos = positionClicked.getX();
        int yPos = positionClicked.getY();
        int zPos = positionClicked.getZ();

        /*
        AttributeModifier blockReachUp = new AttributeModifier(player.getName().getString(), 25D, AttributeModifier.Operation.ADDITION);
        AttributeModifier blockReachDown = new AttributeModifier(player.getName().getString(), 0.04D, AttributeModifier.Operation.MULTIPLY_BASE);
        */
        if(!pContext.getLevel().isClientSide()){

            /*
            if (player.getAttribute(ForgeMod.BLOCK_REACH.get()).getValue() < 5){
                player.getAttribute(ForgeMod.BLOCK_REACH.get()).addTransientModifier(blockReachUp);
            }
            */
            pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,3));
            pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.JUMP,100,2));
            pContext.getPlayer().teleportTo(xPos+0.5, yPos+1, zPos+0.5);

        }

        //player.getAttribute(Attributes.FOLLOW_RANGE).addTransientModifier(blockReach);
        pContext.getPlayer().playSound(SoundEvents.BEACON_ACTIVATE);
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("the power of the burger is held within this sword").withStyle(ChatFormatting.getById(5)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



}