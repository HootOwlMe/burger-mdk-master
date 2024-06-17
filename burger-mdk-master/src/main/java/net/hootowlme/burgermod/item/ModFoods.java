package net.hootowlme.burgermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BURGER = new FoodProperties.Builder().alwaysEat().meat().nutrition(5)
            .saturationMod(2).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200),1f).build();

    public static final FoodProperties BURGER_VEGETARIAN = new FoodProperties.Builder().alwaysEat().meat().nutrition(6)
            .saturationMod(2).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 360,6),1f).build();

    public static final FoodProperties BURGER_METAL = new FoodProperties.Builder().alwaysEat().meat().nutrition(2)
            .saturationMod(1).effect(() -> new MobEffectInstance(MobEffects.HARM, 1),1f).build();

    public static final FoodProperties BURGER_HEALTHY = new FoodProperties.Builder().alwaysEat().meat().nutrition(12)
            .saturationMod(10).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 360,6),1f).build();

    public static final FoodProperties BURGER_OP = new FoodProperties.Builder().alwaysEat().meat().nutrition(20)
            .saturationMod(20)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100000,5),1f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 100000,3),1f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 100000,15),1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100000,5),1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100000,10),1f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 100000),1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100000,6),1f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 100000),1f)
            .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100000,4),1f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100000),1f)
            .build();

    public static final FoodProperties FRIES = new FoodProperties.Builder().alwaysEat().meat().nutrition(3)
            .saturationMod(1).effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 0),1f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 100),1f).build();

}
