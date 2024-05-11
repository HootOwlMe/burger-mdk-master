package net.hootowlme.burgermod.datagen;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(BurgerMod.MOD_ID,"item/" + item.getId().getPath()));

    }


}
