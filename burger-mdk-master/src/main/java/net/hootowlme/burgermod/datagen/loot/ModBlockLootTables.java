package net.hootowlme.burgermod.datagen.loot;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.BURGER_BLOCK.get());
        this.dropSelf(ModBlocks.RESTAURANT_LOW.get());
        this.dropSelf(ModBlocks.RESTAURANT_CEILING.get());
        this.dropSelf(ModBlocks.RESTAURANT_TOP.get());
        this.dropSelf(ModBlocks.RED_STAIRS.get());
        this.dropSelf(ModBlocks.WHITE_BLOCK.get());
        this.dropSelf(ModBlocks.BURGER_SOUND.get());
        this.dropSelf(ModBlocks.FLOOR_BLOCK.get());

        this.dropSelf(ModBlocks.WHITE_PRESSURE_PLATE.get());


        this.dropSelf(ModBlocks.BURGER_FENCE.get());
        this.dropSelf(ModBlocks.BURGER_STAIRS.get());
        this.dropSelf(ModBlocks.BURGER_BUTTON.get());
        this.dropSelf(ModBlocks.BURGER_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.BURGER_TRAPDOOR.get());
        this.dropSelf(ModBlocks.BURGER_WALL.get());
        this.dropSelf(ModBlocks.BURGER_FENCE_GATE.get());

        this.add(ModBlocks.BURGER_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BURGER_SLAB.get()));

        this.add(ModBlocks.BURGER_DOOR.get(),
                block -> createDoorTable(ModBlocks.BURGER_DOOR.get()));

        this.add(ModBlocks.BURGER_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.BURGER_ORE.get(), ModItems.BURGER.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock, (LootPoolEntryContainer.Builder)
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item).apply(SetItemCountFunction
                        .setCount(UniformGenerator.between(7.0F, 9.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
