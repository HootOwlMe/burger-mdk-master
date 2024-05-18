package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {


    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {



        super(output, lookupProvider, BurgerMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.BURGER_DETECTOR_VALUABLES).add(ModBlocks.BURGER_BLOCK.get()).addTags(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BURGER_ORE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BURGER_BLOCK.get())
                .add(ModBlocks.RESTAURANT_LOW.get())
                .add(ModBlocks.RED_STAIRS.get())
                .add(ModBlocks.RESTAURANT_TOP.get())
                .add(ModBlocks.BLACK_BLOCK.get())
                .add(ModBlocks.BLACK_FENCE.get())
                .add(ModBlocks.RESTAURANT_CEILING.get())
                .add(ModBlocks.WHITE_BLOCK.get())
                .add(ModBlocks.FLOOR_BLOCK.get())
                .add(ModBlocks.WHITE_PRESSURE_PLATE.get())
        ;

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(ModTags.Blocks.NEEDS_BURGER_TOOL)
                .add(ModBlocks.BURGER_SOUND.get());

        //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
        //        .add(ModBlocks.___.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BURGER_ORE.get(),
                        ModBlocks.BURGER_SOUND.get(),
                        ModBlocks.RESTAURANT_LOW.get(),
                        ModBlocks.RESTAURANT_CEILING.get(),
                        ModBlocks.RED_STAIRS.get(),
                        ModBlocks.BLACK_BLOCK.get(),
                        ModBlocks.BLACK_FENCE.get(),
                        ModBlocks.WHITE_BLOCK.get(),
                        ModBlocks.FLOOR_BLOCK.get(),
                        ModBlocks.WHITE_PRESSURE_PLATE.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.BURGER_BLOCK.get()

                );

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.BURGER_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BURGER_FENCE_GATE.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.BURGER_WALL.get());


    }
}
