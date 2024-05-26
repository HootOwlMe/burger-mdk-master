package net.hootowlme.burgermod.datagen.loot;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.custom.BurgerCropBlock;
import net.hootowlme.burgermod.block.custom.TallBurgerCropBlock;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
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
        this.dropSelf(ModBlocks.BLACK_BLOCK.get());
        this.dropSelf(ModBlocks.BLACK_FENCE.get());

        this.dropSelf(ModBlocks.WHITE_PRESSURE_PLATE.get());


        this.dropSelf(ModBlocks.BURGER_FENCE.get());
        this.dropSelf(ModBlocks.BURGER_FLOWER.get());
        this.dropSelf(ModBlocks.BURGER_STAIRS.get());
        this.dropSelf(ModBlocks.BURGER_BUTTON.get());
        this.dropSelf(ModBlocks.BURGER_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.BURGER_TRAPDOOR.get());
        this.dropSelf(ModBlocks.BURGER_WALL.get());
        this.dropSelf(ModBlocks.BURGER_FENCE_GATE.get());

        this.add(ModBlocks.POTTED_BURGER_FLOWER.get(), createPotFlowerItemTable(ModBlocks.BURGER_FLOWER.get()));

        this.add(ModBlocks.BURGER_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BURGER_SLAB.get()));

        this.add(ModBlocks.BURGER_DOOR.get(),
                block -> createDoorTable(ModBlocks.BURGER_DOOR.get()));

        this.add(ModBlocks.BURGER_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.BURGER_ORE.get(), ModItems.BURGER.get()));


        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BURGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BurgerCropBlock.AGE, 3));

        this.add(ModBlocks.BURGER_CROP.get(), createCropDrops(ModBlocks.BURGER_CROP.get(), ModItems.BURGER.get(), ModItems.BURGER_SEEDS.get(), lootitemcondition$builder));


        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TALL_BURGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallBurgerCropBlock.AGE, 3))
                .or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TALL_BURGER_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(TallBurgerCropBlock.AGE, 4)));

        this.add(ModBlocks.TALL_BURGER_CROP.get(), createCropDrops(ModBlocks.TALL_BURGER_CROP.get(), ModItems.BURGER.get(),
                ModItems.TALL_BURGER_SEEDS.get(), lootitemcondition$builder2));

        /*
        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TALL_BURGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallBurgerCropBlock.AGE, 4));

         */


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
