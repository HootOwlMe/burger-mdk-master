package net.hootowlme.burgermod.enchantment;

import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BurgerMod.MOD_ID);

    public static RegistryObject<Enchantment> LIGHTNING_STRIKER = ENCHANTMENTS.register("lightning_striker", () -> new LightningStrikerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static RegistryObject<Enchantment> AIR_WALKER = ENCHANTMENTS.register("air_walker", () -> new AirWalkerEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET));

    public static void register(IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }

}
