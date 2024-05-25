package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.custom.BurgerCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {



        super(output, BurgerMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.BURGER_BLOCK);
        blockWithItem(ModBlocks.RESTAURANT_LOW);
        blockWithItem(ModBlocks.BURGER_ORE);
        blockWithItem(ModBlocks.BURGER_SOUND);
        blockWithItem(ModBlocks.WHITE_BLOCK);
        blockWithItem(ModBlocks.FLOOR_BLOCK);
        blockWithItem(ModBlocks.RESTAURANT_CEILING);

        fenceBlock(((FenceBlock) ModBlocks.BLACK_FENCE.get()), blockTexture(ModBlocks.BLACK_BLOCK.get()));
        blockWithItem(ModBlocks.BLACK_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.RED_STAIRS.get()), blockTexture(ModBlocks.RED_STAIRS.get()));
        stairsBlockWithRenderType(((StairBlock) ModBlocks.RESTAURANT_TOP.get()), modLoc("block/red_texture"), modLoc("block/white_block"), modLoc("block/white_block"), "cutout");

        stairsBlock(((StairBlock) ModBlocks.BURGER_STAIRS.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.BURGER_SLAB.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.BURGER_BUTTON.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BURGER_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.BURGER_FENCE.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.BURGER_FENCE_GATE.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.BURGER_WALL.get()), blockTexture(ModBlocks.BURGER_BLOCK.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.WHITE_PRESSURE_PLATE.get()), blockTexture((ModBlocks.WHITE_BLOCK.get())));



        doorBlockWithRenderType(((DoorBlock) ModBlocks.BURGER_DOOR.get()), modLoc("block/burger_door_bottom"), modLoc("block/burger_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BURGER_TRAPDOOR.get()), modLoc("block/burger_trapdoor"),true, "cutout");

        makeBurgerCrop((CropBlock) ModBlocks.BURGER_CROP.get(), "burger_stage", "burger_stage");



    }

    public void makeBurgerCrop(CropBlock block, String modelName, String textureName){

        Function<BlockState, ConfiguredModel[]> function = blockState -> burgerStates(blockState, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }




    private ConfiguredModel[] burgerStates(BlockState state, CropBlock block, String modelName, String textureName){
        ConfiguredModel[] models = new ConfiguredModel[1];

        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BurgerCropBlock) block).getAgeProperty()),
                new ResourceLocation(BurgerMod.MOD_ID, "block/" + textureName + state.getValue(((BurgerCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
