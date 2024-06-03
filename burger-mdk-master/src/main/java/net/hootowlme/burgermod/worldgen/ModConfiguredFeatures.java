package net.hootowlme.burgermod.worldgen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BURGER_ORE_KEY = registerKey("burger_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_BURGER_ORE_KEY = registerKey("end_burger_ore");

    public static void boostrap(BootstapContext<ConfiguredFeature<?, ?>> context){

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overWorldBurgerOres = List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.BURGER_ORE.get().defaultBlockState()),OreConfiguration.target(deepSlateReplaceables, ModBlocks.BURGER_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_BURGER_ORE_KEY, Feature.ORE, new OreConfiguration(overWorldBurgerOres, 4));
        register(context, END_BURGER_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables, ModBlocks.BURGER_ORE.get().defaultBlockState(), 12));



    }


    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BurgerMod.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
        ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
