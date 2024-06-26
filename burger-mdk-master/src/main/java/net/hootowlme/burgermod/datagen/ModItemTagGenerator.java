package net.hootowlme.burgermod.datagen;


import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {



        super(p_275343_, p_275729_, p_275322_, BurgerMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.BURGER_CHESTPLATE.get(),
                        ModItems.BURGER_LEGGINGS.get(),
                        ModItems.BURGER_BOOTS.get(),
                        ModItems.BURGER_HELMET.get()
                        );


        this.tag(ItemTags.MUSIC_DISCS).add(ModItems.SCHEMING_WEASEL_DISC.get());
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(ModItems.SCHEMING_WEASEL_DISC.get());


    }
}
