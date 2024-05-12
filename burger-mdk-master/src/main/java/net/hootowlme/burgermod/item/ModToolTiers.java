package net.hootowlme.burgermod.item;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {

    public static final Tier BURGER = TierSortingRegistry.registerTier(
            new ForgeTier(5,6969,5f,4f,100, ModTags.Blocks.NEEDS_BURGER_TOOL, () -> Ingredient.of(ModItems.BURGER.get())),
            new ResourceLocation(BurgerMod.MOD_ID, "burger"), List.of(Tiers.NETHERITE), List.of());

}
