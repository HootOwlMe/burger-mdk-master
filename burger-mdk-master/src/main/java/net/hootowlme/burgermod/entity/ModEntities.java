package net.hootowlme.burgermod.entity;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BurgerMod.MOD_ID);


    public static final RegistryObject<EntityType<LivingBurgerEntity>> LIVING_BURGER =
            ENTITY_TYPES.register("living_burger", () -> EntityType.Builder.of(LivingBurgerEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("living_burger"));



    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
