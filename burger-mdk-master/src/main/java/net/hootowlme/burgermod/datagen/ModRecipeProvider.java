package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.io.output.AppendableWriter;
import org.spongepowered.asm.util.IConsumer;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> BURGER_SMELTTABLES = List.of(ModBlocks.BURGER_BLOCK.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pRecipeOutput) {

        oreBlasting(pRecipeOutput,BURGER_SMELTTABLES, RecipeCategory.MISC, ModItems.GREASE.get(),1.1f, 200,"burger");
        oreSmelting(pRecipeOutput,BURGER_SMELTTABLES, RecipeCategory.MISC, ModItems.GREASE.get(),1.1f,200,"burger");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_STAIRS.get().asItem(),4)
                .pattern("b  ")
                .pattern("bb ")
                .pattern("bbb")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_WALL.get().asItem(),6)
                .pattern("bbb")
                .pattern("bbb")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_TRAPDOOR.get().asItem(),2)
                .pattern("bbb")
                .pattern("bbb")
                .define('b',ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_DOOR.get().asItem(),3)
                .pattern("bb")
                .pattern("bb")
                .pattern("bb")
                .define('b', ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_FLOWER.get(),3)
                .pattern(" b ")
                .pattern(" b ")
                .pattern("bbb")
                .define('b', ModItems.BURGER_SEEDS.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BURGER_BUTTON.get().asItem())
                .requires(ModItems.BURGER_SEEDS.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_PRESSURE_PLATE.get().asItem())
                .pattern("bb")
                .define('b',ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_SLAB.get().asItem(),3)
                .pattern("bbb")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_FENCE_GATE.get().asItem())
                .pattern("sbs")
                .pattern("sbs")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .define('s',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_FENCE.get().asItem(),3)
                .pattern("bsb")
                .pattern("bsb")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .define('s',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BURGER_BLOCK.get())
                .pattern("bbb")
                .pattern("bbb")
                .pattern("bbb")
                .define('b',ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BURGER_DETECTOR.get())
                .pattern("bbb")
                .pattern("bnb")
                .pattern("bbb")
                .define('b',Items.IRON_INGOT)
                .define('n',ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BURGER_SWORD.get())
                .pattern(" b ")
                .pattern(" b ")
                .pattern(" n ")
                .define('b',ModItems.BURGER.get())
                .define('n',ModItems.BURGER_DETECTOR.get())
                .unlockedBy(getHasName(ModItems.BURGER_DETECTOR.get()),has(ModItems.BURGER_DETECTOR.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.BURGER_SOUND.get())
                .pattern("bbb")
                .pattern("bnb")
                .pattern("bbb")
                .define('b',ModItems.BURGER.get())
                .define('n',ModBlocks.BURGER_BLOCK.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_AXE.get())
                .pattern(" bb")
                .pattern(" nb")
                .pattern(" n ")
                .define('b',ModItems.BURGER.get())
                .define('n',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_SHOVEL.get())
                .pattern(" b ")
                .pattern(" n ")
                .pattern(" n ")
                .define('b', ModItems.BURGER.get())
                .define('n',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ADVANCED_ANVIL.get().asItem())
                .pattern(" b ")
                .pattern("bab")
                .pattern(" b ")
                .define('b',ModBlocks.BURGER_BLOCK.get().asItem())
                .define('a',Items.ANVIL)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_HELMET.get())
                .pattern("bbb")
                .pattern("b b")
                .pattern("   ")
                .define('b', ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_LEGGINGS.get())
                .pattern("bbb")
                .pattern("b b")
                .pattern("b b")
                .define('b', ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_CHESTPLATE.get())
                .pattern("b b")
                .pattern("bbb")
                .pattern("bbb")
                .define('b', ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_BOOTS.get())
                .pattern("   ")
                .pattern("b b")
                .pattern("b b")
                .define('b', ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_PICKAXE.get())
                .pattern("bbb")
                .pattern(" n ")
                .pattern(" n ")
                .define('b', ModItems.BURGER.get())
                .define('n',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BURGER_HOE.get())
                .pattern(" bb")
                .pattern(" n ")
                .pattern(" n ")
                .define('b', ModItems.BURGER.get())
                .define('n',Items.STICK)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_BLOCK.get().asItem(),4)
                .pattern("nn")
                .pattern("nn")
                .define('n',Items.WHITE_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_PRESSURE_PLATE.get().asItem())
                .pattern("nn")
                .define('n',Items.WHITE_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_BLOCK.get().asItem(),4)
                .pattern("nn")
                .pattern("nn")
                .define('n',Items.BLACK_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_FENCE.get().asItem(),3)
                .pattern("nnn")
                .pattern("nnn")
                .define('n',Items.BLACK_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RED_STAIRS.get().asItem(),4)
                .pattern("b  ")
                .pattern("bb ")
                .pattern("bbb")
                .define('b',Items.RED_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRIES.get(),1)
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .unlockedBy(getHasName(ModBlocks.BURGER_BLOCK.get()), has(ModBlocks.BURGER_BLOCK.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BURGER.get(),9)
               .requires(ModBlocks.BURGER_BLOCK.get())
               .unlockedBy(getHasName(ModBlocks.BURGER_BLOCK.get()), has(ModBlocks.BURGER_BLOCK.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BURGER_SEEDS.get(),3)
                .requires(ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TALL_BURGER_SEEDS.get(),3)
                .requires(ModItems.BURGER.get())
                .requires(ModItems.BURGER.get())
                .requires(ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.RESTAURANT_TOP.get(),1)
                .requires(ModBlocks.RED_STAIRS.get().asItem())
                .requires(Items.WHITE_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.RESTAURANT_LOW.get(),1)
                .requires(ModBlocks.WHITE_BLOCK.get().asItem())
                .requires(Items.RED_DYE)
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RESTAURANT_CEILING.get().asItem(),4)
                .requires(ModBlocks.BLACK_BLOCK.get().asItem())
                .requires(ModBlocks.BLACK_BLOCK.get().asItem())
                .requires(Items.STONE)
                .requires(Items.STONE)
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLOOR_BLOCK.get().asItem(),4)
                .requires(ModBlocks.BLACK_BLOCK.get().asItem())
                .requires(ModBlocks.BLACK_BLOCK.get().asItem())
                .requires(ModBlocks.WHITE_BLOCK.get().asItem())
                .requires(ModBlocks.WHITE_BLOCK.get().asItem())
                .unlockedBy(getHasName(ModItems.BURGER.get()), has(ModItems.BURGER.get())).save(pRecipeOutput);

 /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.THROWABLE_BURGER.get())
                .pattern(" b ")
                .pattern(" n ")
                .pattern(" b ")
                .define('b',Items.DIRT)
                .define('n',ModItems.BURGER.get())
                .unlockedBy(getHasName(ModItems.BURGER.get()),has(ModItems.BURGER.get()))
                .save(pRecipeOutput);

  */


    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience,
                    pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike),
                    has(itemlike))
                    .save(pFinishedRecipeConsumer, BurgerMod.MOD_ID+ ":" +
                    getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

}
