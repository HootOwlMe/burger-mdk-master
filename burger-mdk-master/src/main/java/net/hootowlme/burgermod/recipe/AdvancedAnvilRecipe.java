package net.hootowlme.burgermod.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AdvancedAnvilRecipe implements Recipe<SimpleContainer> {

        private final NonNullList<Ingredient> inputItems;
        private final ItemStack output;
        private final ResourceLocation id;

    public AdvancedAnvilRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {

        if(level.isClientSide()) {
            return false;
        }

        return (inputItems.get(0).test(simpleContainer.getItem(0))) && (inputItems.get(1).test(simpleContainer.getItem(1))) ;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AdvancedAnvilRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "advanced_anvil_enchanting";
    }

    public static class Serializer implements RecipeSerializer<AdvancedAnvilRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(BurgerMod.MOD_ID,"advanced_anvil_enchanting");
        @Override
        public AdvancedAnvilRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2,Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }

            return new AdvancedAnvilRecipe(inputs,output,resourceLocation);
        }

        @Override
        public @Nullable AdvancedAnvilRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(),Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i,Ingredient.fromNetwork(friendlyByteBuf));
            }
            ItemStack output = friendlyByteBuf.readItem();
            return new AdvancedAnvilRecipe(inputs,output,resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, AdvancedAnvilRecipe advancedAnvilRecipe) {

            friendlyByteBuf.writeInt(advancedAnvilRecipe.inputItems.size());
            for (Ingredient ingredient : advancedAnvilRecipe.getIngredients()){
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItemStack(advancedAnvilRecipe.getResultItem(null),false);

        }
    }

}
