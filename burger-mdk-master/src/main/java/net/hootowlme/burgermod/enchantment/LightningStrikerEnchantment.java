package net.hootowlme.burgermod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;


public class LightningStrikerEnchantment extends Enchantment {

    @Override
    public int getMinCost(int pLevel) {
        return pLevel * 2;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 2;
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {

        if(!pAttacker.level().isClientSide()){
            ServerLevel world = ((ServerLevel) pAttacker.level());
            BlockPos pos = pTarget.blockPosition();

            if(pLevel == 1){
                EntityType.LIGHTNING_BOLT.spawn(world, pos,
                        MobSpawnType.TRIGGERED);
            }

            if(pLevel == 2){
                EntityType.LIGHTNING_BOLT.spawn(world, pos,
                        MobSpawnType.TRIGGERED);
                EntityType.LIGHTNING_BOLT.spawn(world, pos,
                        MobSpawnType.TRIGGERED);
            }

        }


        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    public boolean isTreasureOnly() {
        return false;
    }

    public boolean checkCompatibility(Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != Enchantments.AQUA_AFFINITY;
    }

    public LightningStrikerEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_HEAD, pApplicableSlots);
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }
}
