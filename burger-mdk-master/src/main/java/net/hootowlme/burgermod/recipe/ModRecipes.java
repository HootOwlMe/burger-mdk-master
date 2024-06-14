package net.hootowlme.burgermod.recipe;

import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BurgerMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AdvancedAnvilRecipe>> ADVANCED_ANVIL_SERIALIZER =
            SERIALIZER.register("advanced_anvil_enchanting", () -> AdvancedAnvilRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus){
        SERIALIZER.register(eventBus);
    }

}
