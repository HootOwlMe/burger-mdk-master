package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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



    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
