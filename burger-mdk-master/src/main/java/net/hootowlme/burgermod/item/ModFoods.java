package net.hootowlme.burgermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BURGER = new FoodProperties.Builder().alwaysEat().meat().nutrition(4)
            .saturationMod(2).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400),0.9f).build();

    public static final FoodProperties FRIES = new FoodProperties.Builder().alwaysEat().meat().nutrition(2)
            .saturationMod(1).effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 0),1f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 100),1f).build();

}
