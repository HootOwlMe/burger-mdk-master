package net.hootowlme.burgermod.item;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.custom.BurgerSoundBlock;
import net.hootowlme.burgermod.block.entity.ModBlockEntities;
import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BurgerMod.MOD_ID);


    public static  final RegistryObject<CreativeModeTab> BURGER_TAB = CREATIVE_MODE_TABS.register("burger_tab",() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BURGER.get()))
            .title(Component.translatable("creativetab.burger_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.BURGER.get());
                pOutput.accept(ModItems.FRIES.get());
                pOutput.accept(Items.HONEY_BOTTLE);
                pOutput.accept(ModBlocks.BURGER_BLOCK.get());
                pOutput.accept(ModItems.GREASE.get());
                pOutput.accept(ModBlocks.BURGER_ORE.get());
                pOutput.accept(ModItems.BURGER_DETECTOR.get());
                pOutput.accept(ModBlocks.BURGER_SOUND.get());

                pOutput.accept(ModBlocks.BURGER_STAIRS.get());
                pOutput.accept(ModBlocks.BURGER_DOOR.get());
                pOutput.accept(ModBlocks.BURGER_SLAB.get());
                pOutput.accept(ModBlocks.BURGER_TRAPDOOR.get());
                pOutput.accept(ModBlocks.BURGER_FENCE.get());
                pOutput.accept(ModBlocks.BURGER_FENCE_GATE.get());
                pOutput.accept(ModBlocks.BURGER_WALL.get());
                pOutput.accept(ModBlocks.BURGER_BUTTON.get());
                pOutput.accept(ModBlocks.BURGER_PRESSURE_PLATE.get());
                pOutput.accept(ModItems.BURGER_SWORD.get());
                pOutput.accept(ModItems.BURGER_AXE.get());
                pOutput.accept(ModBlocks.BLACK_BLOCK.get());
                pOutput.accept(ModBlocks.BLACK_FENCE.get());
                pOutput.accept(ModItems.BURGER_HOE.get());
                pOutput.accept(ModBlocks.AIR_WALK_BLOCK.get());
                pOutput.accept(ModItems.BURGER_PICKAXE.get());
                pOutput.accept(ModItems.BURGER_SHOVEL.get());
                pOutput.accept(ModItems.SCHEMING_WEASEL_DISC.get());
                pOutput.accept(ModBlocks.ADVANCED_ANVIL.get());
                pOutput.accept(ModItems.LIVING_BURGER_SPAWN_EGG.get());
                pOutput.accept(ModItems.SLENDERMAN_SPAWN_EGG.get());

                pOutput.accept(ModBlocks.BURGER_FLOWER.get());
                //pOutput.accept(ModBlocks.POTTED_BURGER_FLOWER.get());

                pOutput.accept(ModItems.BURGER_SEEDS.get());
                pOutput.accept(ModItems.TALL_BURGER_SEEDS.get());

                pOutput.accept(ModItems.BURGER_HELMET.get());
                pOutput.accept(ModItems.BURGER_CHESTPLATE.get());
                pOutput.accept(ModItems.BURGER_BOOTS.get());
                pOutput.accept(ModItems.BURGER_LEGGINGS.get());
                //pOutput.accept(ModItems.THROWABLE_BURGER.get());

                pOutput.accept(ModBlocks.RESTAURANT_LOW.get());
                pOutput.accept(ModBlocks.RESTAURANT_TOP.get());
                pOutput.accept(ModBlocks.RED_STAIRS.get());
                pOutput.accept(ModBlocks.RESTAURANT_CEILING.get());
                pOutput.accept(ModBlocks.WHITE_BLOCK.get());
                pOutput.accept(ModBlocks.WHITE_PRESSURE_PLATE.get());
                pOutput.accept(ModBlocks.FLOOR_BLOCK.get());

            })
            .build());

    public static void register(IEventBus eventBus) {

        CREATIVE_MODE_TABS.register(eventBus);

    }


}
