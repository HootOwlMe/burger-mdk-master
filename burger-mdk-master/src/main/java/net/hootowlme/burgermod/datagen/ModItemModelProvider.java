package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {


    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static{

        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);

    }


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, BurgerMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.BURGER);
        simpleItem(ModItems.BURGER_DETECTOR);
        simpleItem(ModItems.GREASE);
        simpleItem(ModItems.FRIES);
        simpleItem(ModItems.BURGER_SEEDS);
        simpleItem(ModItems.TALL_BURGER_SEEDS);
        //simpleItem(ModItems.THROWABLE_BURGER);

        handheldItem(ModItems.BURGER_AXE);
        handheldItem(ModItems.BURGER_PICKAXE);
        handheldItem(ModItems.BURGER_HOE);
        handheldItem(ModItems.BURGER_SHOVEL);


        fenceItem(ModBlocks.BLACK_FENCE, ModBlocks.BLACK_BLOCK);

        simpleItem(ModItems.BLOOPIN_DISC);

        simpleBlockItem(ModBlocks.BURGER_DOOR);
        fenceItem(ModBlocks.BURGER_FENCE, ModBlocks.BURGER_BLOCK);
        buttonItem(ModBlocks.BURGER_BUTTON, ModBlocks.BURGER_BLOCK);
        wallItem(ModBlocks.BURGER_WALL, ModBlocks.BURGER_BLOCK);

        simplerBlockItem(ModBlocks.BURGER_STAIRS);
        simplerBlockItem(ModBlocks.RESTAURANT_TOP);
        simplerBlockItem(ModBlocks.RED_STAIRS);
        simplerBlockItem(ModBlocks.BURGER_SLAB);
        simplerBlockItem(ModBlocks.BURGER_PRESSURE_PLATE);
        simplerBlockItem(ModBlocks.BURGER_FENCE_GATE);

        simplerBlockItem(ModBlocks.WHITE_PRESSURE_PLATE);


        trapdoorItem(ModBlocks.BURGER_TRAPDOOR);

        trimmedArmorItem(ModItems.BURGER_BOOTS);
        trimmedArmorItem(ModItems.BURGER_CHESTPLATE);
        trimmedArmorItem(ModItems.BURGER_LEGGINGS);
        trimmedArmorItem(ModItems.BURGER_HELMET);

        simpleBlockItemBlockTexture(ModBlocks.BURGER_FLOWER);

        withExistingParent(ModItems.LIVING_BURGER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));


    }


    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {

        final String MOD_ID = BurgerMod.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem){

            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()){

                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";

                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath);
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png","textures");

                getBuilder(currentTrimName).parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                            mcLoc("item/generated")).override().model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                "item/" + itemRegistryObject.getId().getPath()));

            });

        }


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

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){

        return withExistingParent(item.getId().getPath(),
        new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(BurgerMod.MOD_ID,"item/" + item.getId().getPath()));

    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(BurgerMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }


    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item){

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(BurgerMod.MOD_ID,"block/" + item.getId().getPath()));

    }


    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item){

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(BurgerMod.MOD_ID,"item/" + item.getId().getPath()));

    }

}
