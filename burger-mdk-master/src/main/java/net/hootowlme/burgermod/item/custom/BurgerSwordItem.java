package net.hootowlme.burgermod.item.custom;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class BurgerSwordItem extends SwordItem {

    public BurgerSwordItem(Tier tier, int damage, float speed, Properties pProperties) {
        super(tier,damage,speed,pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {


        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,2));

        pContext.getPlayer().playSound(SoundEvents.BEACON_ACTIVATE);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("the power of the burger is held within this sword").withStyle(ChatFormatting.getById(5)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



}