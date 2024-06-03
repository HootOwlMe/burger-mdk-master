package net.hootowlme.burgermod.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {





    public static List<PlacementModifier> orePlacement(PlacementModifier pMod1, PlacementModifier pMod2){
        return List.of(pMod1, InSquarePlacement.spread(), pMod2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange){
        return orePlacement(CountPlacement.of(pCount),pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange){
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance),pHeightRange);
    }


}
