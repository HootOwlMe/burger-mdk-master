package net.hootowlme.burgermod.item;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.item.custom.BurgerDetectorItem;
import net.hootowlme.burgermod.item.custom.BurgerSwordItem;
import net.hootowlme.burgermod.item.custom.FuelItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {


    /*

    You need to do stuff:
    - Here (ModItems)
    - ModItemModelProvider
    - lang -> en_us
    - textures -> item
    - ModCreativeModeTabs
    - ModItemTagGenerator (if desired)
    - ModRecipeProvider (if desired)
    - ModFood (if desired)
    - ModTags (if desired, rarely)


     */



    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurgerMod.MOD_ID);

    public static final RegistryObject<Item> BURGER = ITEMS.register("burger",() -> new Item(new Item.Properties().food(ModFoods.BURGER)));

    public static final RegistryObject<Item> BURGER_PICKAXE = ITEMS.register("burger_pickaxe",() -> new PickaxeItem(ModToolTiers.BURGER, 3, 1.2F, new Item.Properties()));
    public static final RegistryObject<Item> BURGER_SHOVEL = ITEMS.register("burger_shovel",() -> new ShovelItem(ModToolTiers.BURGER, 3, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> BURGER_AXE = ITEMS.register("burger_axe",() -> new AxeItem(ModToolTiers.BURGER, 10, 1.2F, new Item.Properties()));
    public static final RegistryObject<Item> BURGER_HOE = ITEMS.register("burger_hoe",() -> new HoeItem(ModToolTiers.BURGER, 5, 99F, new Item.Properties()));

    //public static final RegistryObject<Item> THROWABLE_BURGER = ITEMS.register("throwable_burger",() -> new ThrowableBurger(new Item.Properties()));

    public static final RegistryObject<Item> BURGER_DETECTOR = ITEMS.register("burger_detector",() -> new BurgerDetectorItem(new Item.Properties().durability(6969)));

    public static final RegistryObject<Item> GREASE = ITEMS.register("grease",()-> new FuelItem(new Item.Properties(),800));

    public static final RegistryObject<Item> FRIES = ITEMS.register("fries",() -> new Item(new Item.Properties().food(ModFoods.FRIES)));

    public static final RegistryObject<Item> BURGER_SWORD = ITEMS.register("burger_sword",() -> new BurgerSwordItem(Tiers.NETHERITE,14,2.5F,new Item.Properties().durability(420)));

    public static final RegistryObject<Item> BURGER_CHESTPLATE = ITEMS.register("burger_chestplate",() -> new ArmorItem(ModArmorMaterials.BURGER,ArmorItem.Type.CHESTPLATE,new Item.Properties().durability(4200)));
    public static final RegistryObject<Item> BURGER_HELMET = ITEMS.register("burger_helmet",() -> new ArmorItem(ModArmorMaterials.BURGER,ArmorItem.Type.HELMET,new Item.Properties().durability(4200)));
    public static final RegistryObject<Item> BURGER_BOOTS = ITEMS.register("burger_boots",() -> new ArmorItem(ModArmorMaterials.BURGER,ArmorItem.Type.BOOTS,new Item.Properties().durability(4200)));
    public static final RegistryObject<Item> BURGER_LEGGINGS = ITEMS.register("burger_leggings",() -> new ArmorItem(ModArmorMaterials.BURGER,ArmorItem.Type.LEGGINGS,new Item.Properties().durability(4200)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
