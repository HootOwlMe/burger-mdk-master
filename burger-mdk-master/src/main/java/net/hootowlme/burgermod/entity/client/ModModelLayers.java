package net.hootowlme.burgermod.entity.client;

import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation LIVING_BURGER_LAYER = new ModelLayerLocation(
            new ResourceLocation(BurgerMod.MOD_ID, "living_burger_layer"),"main");

    public static final ModelLayerLocation SLENDERMAN_LAYER = new ModelLayerLocation(
            new ResourceLocation(BurgerMod.MOD_ID, "slenderman_layer"), "main");

}
