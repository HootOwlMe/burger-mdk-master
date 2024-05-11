package net.hootowlme.burgermod.item;

import com.google.common.util.concurrent.ClosingFuture;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.item.custom.BurgerDetectorItem;
import net.hootowlme.burgermod.item.custom.BurgerSwordItem;
import net.hootowlme.burgermod.item.custom.FuelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurgerMod.MOD_ID);

    public static final RegistryObject<Item> BURGER = ITEMS.register("burger",() -> new Item(new Item.Properties().food(ModFoods.BURGER)));

    public static final RegistryObject<Item> BURGER_DETECTOR = ITEMS.register("burger_detector",() -> new BurgerDetectorItem(new Item.Properties().durability(6969)));

    public static final RegistryObject<Item> GREASE = ITEMS.register("grease",()-> new FuelItem(new Item.Properties(),800));

    public static final RegistryObject<Item> FRIES = ITEMS.register("fries",() -> new Item(new Item.Properties().food(ModFoods.FRIES)));

    public static final RegistryObject<Item> BURGER_SWORD = ITEMS.register("burger_sword",() -> new BurgerSwordItem(Tiers.NETHERITE,14,2.5F,new Item.Properties().durability(420)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
