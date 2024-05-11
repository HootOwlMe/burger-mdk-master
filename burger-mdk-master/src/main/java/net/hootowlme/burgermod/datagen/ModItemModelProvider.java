package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {


        super(output, BurgerMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.BURGER);
        simpleItem(ModItems.BURGER_DETECTOR);
        simpleItem(ModItems.GREASE);
        simpleItem(ModItems.FRIES);


        simpleBlockItem(ModBlocks.BURGER_DOOR);
        fenceItem(ModBlocks.BURGER_FENCE, ModBlocks.BURGER_BLOCK);
        buttonItem(ModBlocks.BURGER_BUTTON, ModBlocks.BURGER_BLOCK);
        wallItem(ModBlocks.BURGER_WALL, ModBlocks.BURGER_BLOCK);

        simplerBlockItem(ModBlocks.BURGER_STAIRS);
        simplerBlockItem(ModBlocks.BURGER_SLAB);
        simplerBlockItem(ModBlocks.BURGER_PRESSURE_PLATE);
        simplerBlockItem(ModBlocks.BURGER_FENCE_GATE);


        trapdoorItem(ModBlocks.BURGER_TRAPDOOR);

    }


    public void simplerBlockItem(RegistryObject<Block> block){

        this.withExistingParent(BurgerMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));

    }

    public void trapdoorItem(RegistryObject<Block> block){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(BurgerMod.MOD_ID,"item/" + item.getId().getPath()));

    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(BurgerMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(BurgerMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(BurgerMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item){

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(BurgerMod.MOD_ID,"item/" + item.getId().getPath()));

    }

}
