package net.hootowlme.burgermod.util;

import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks{

        public static final TagKey<Block> BURGER_DETECTOR_VALUABLES = tag("burger_detector_valuables");
        public static final TagKey<Block> BURGER_DETECTOR = tag("burger_detector");
        public static final TagKey<Block> NEEDS_BURGER_TOOL = tag("needs_burger_tool");



        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(BurgerMod.MOD_ID, name));
        }
    }


    public static class Items{


        public static final TagKey<Item> IS_MENDING3 = tag("isMending3");
        public static final TagKey<Item> IS_PROT10 = tag("isProt10");
        public static final TagKey<Item> IS_SHARP10 = tag("isSharp10");



        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(BurgerMod.MOD_ID, name));
        }

    }


}
