package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.loot.AddBlockModifier;
import net.hootowlme.burgermod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, BurgerMod.MOD_ID);
    }

    @Override
    protected void start() {

        /*
        add("burger_block_from_Netherite_Block", new AddBlockModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.NETHERITE_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()}
                , ModBlocks.BURGER_BLOCK.get()));

         */


        add("burger_block_from_netherite_block", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.NETHERITE_BLOCK).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()}
                , ModBlocks.BURGER_BLOCK.get().asItem()));


        add("burger_from_villager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/villager")).build()},
                ModItems.BURGER.get()));

        add("burger_sword_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()},
                ModItems.BURGER_SWORD.get()));

    }

}
