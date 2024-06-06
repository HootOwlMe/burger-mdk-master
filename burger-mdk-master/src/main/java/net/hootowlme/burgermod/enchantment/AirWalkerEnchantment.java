package net.hootowlme.burgermod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;


public class AirWalkerEnchantment extends Enchantment {

    @Override
    public int getMinCost(int pLevel) {
        return pLevel * 2;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 2;
    }

    public boolean isTreasureOnly() {
        return false;
    }

    public boolean checkCompatibility(Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != Enchantments.FROST_WALKER;
    }

    public AirWalkerEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_FEET, pApplicableSlots);
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }
}
