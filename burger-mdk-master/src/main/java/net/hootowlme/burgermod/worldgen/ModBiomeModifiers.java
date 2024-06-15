package net.hootowlme.burgermod.worldgen;

import io.netty.bootstrap.Bootstrap;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.Weight;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_BURGER_ORE = registerKey("add_burger_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_BURGER_ORE = registerKey("add_end_burger_ore");
    public static final ResourceKey<BiomeModifier> ADD_LIVING_BURGER_SPAWN = registerKey("add_living_burger_spawn");
    public static final ResourceKey<BiomeModifier> ADD_LIVING_BURGER_SPAWN1 = registerKey("add_living_burger_spawn_one");
    public static final ResourceKey<BiomeModifier> ADD_LIVING_BURGER_SPAWN2 = registerKey("add_living_burger_spawn_two");

    public static final ResourceKey<BiomeModifier> ADD_SLENDERMAN = registerKey("add_slenderman");



    public static void bootstrap(BootstapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        var biomes = context.lookup(Registries.BIOME);


        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        MobSpawnSettings.SpawnerData livingBurgerSpawnData = new MobSpawnSettings.SpawnerData(ModEntities.LIVING_BURGER.get(),10,1,3);
        MobSpawnSettings.SpawnerData slendermanSpawnData = new MobSpawnSettings.SpawnerData(ModEntities.SLENDERMAN.get(),1,1,1);




        context.register(ADD_LIVING_BURGER_SPAWN1, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST), List.of(livingBurgerSpawnData)));



        context.register(ADD_LIVING_BURGER_SPAWN2, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_HILL), List.of(livingBurgerSpawnData)));

        context.register(ADD_LIVING_BURGER_SPAWN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_VILLAGE_PLAINS), List.of(livingBurgerSpawnData)));


        context.register(ADD_SLENDERMAN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST), List.of(slendermanSpawnData)));


        context.register(ADD_BURGER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.BURGER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_BURGER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.END_BURGER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(BurgerMod.MOD_ID, name));
    }

}
