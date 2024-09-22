package net.hootowlme.burgermod.block.entity;

import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.hootowlme.burgermod.recipe.AdvancedAnvilRecipe;
import net.hootowlme.burgermod.screen.AdvancedAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AdvancedAnvilBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static int LEFT_INPUT_SLOT = 0;
    private static int RIGHT_INPUT_SLOT = 1;
    private static int OUTPUT_SLOT = 2;
    private static int COST_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1;

    public AdvancedAnvilBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_ANVIL_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                  case 0 -> AdvancedAnvilBlockEntity.this.progress;
                  case 1 -> AdvancedAnvilBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0 -> AdvancedAnvilBlockEntity.this.progress = i1;
                    case 1 -> AdvancedAnvilBlockEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("block.burgermod.advanced_anvil");

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AdvancedAnvilMenu(i,inventory,this,this.data);
    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("advanced_anvil_progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("advanced_anvil_progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if(hasRecipe()){
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);
            if(progresssFinished()){
                craftItem();
                resetProgress();
            }
        }else{
            resetProgress();
        }

    }

    private void resetProgress() {
        progress = 0;
    }



    private void craftItem() {


        ItemStack leftInputItem = this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT);
        ItemStack rightInputItem = this.itemHandler.getStackInSlot(RIGHT_INPUT_SLOT);
        ItemStack result = new ItemStack(this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT).getItem(),1);

        if(leftInputItem.getDamageValue() > 0){
            result.setDamageValue(leftInputItem.getDamageValue());
        }

        if (leftInputItem.hasTag() && !leftInputItem.is(Items.ENCHANTED_BOOK)){
            if(leftInputItem.getTagElement("Trim") != null){
                CompoundTag trimTag1 = leftInputItem.getTagElement("Trim");
                result.addTagElement("Trim", trimTag1);
            }
            CompoundTag newTag = leftInputItem.getTag().copy();
            result.setTag(newTag);
            result.removeTagKey("Enchantments");
        }

        Map<Enchantment,Integer> leftBookEnchants = new HashMap<>();
        if (leftInputItem.is(Items.ENCHANTED_BOOK)){
             leftBookEnchants = EnchantmentHelper.getEnchantments(leftInputItem);
        }

        Map<Enchantment,Integer> rightBookEnchants = new HashMap<>();
        if (rightInputItem.is(Items.ENCHANTED_BOOK)){
            rightBookEnchants = EnchantmentHelper.getEnchantments(rightInputItem);
        }

        Map<Enchantment,Integer> leftEnchants = leftInputItem.getAllEnchantments();
        Map<Enchantment,Integer> rightEnchants = rightInputItem.getAllEnchantments();
        Map<Enchantment, Integer> combinedMap1 = combineMaps(leftEnchants, rightEnchants);

        if (!leftBookEnchants.isEmpty()){
            combinedMap1 = combineMaps(combinedMap1,leftBookEnchants);
        }

        if (!rightBookEnchants.isEmpty()){
            combinedMap1 = combineMaps(combinedMap1,rightBookEnchants);
        }
        combinedMap1.forEach(result::enchant);

        if((!this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT).isEmpty()) && (!this.itemHandler.getStackInSlot(RIGHT_INPUT_SLOT).isEmpty()) && (this.itemHandler.getStackInSlot(COST_SLOT).is(Items.EMERALD))){
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result);
            this.itemHandler.extractItem(LEFT_INPUT_SLOT,1,false);
            this.itemHandler.extractItem(RIGHT_INPUT_SLOT,1,false);
            this.itemHandler.extractItem(COST_SLOT,1,false);
        }


        //RECIPE CODE - NOT USED FOR THIS BlockEntity
        //Optional<AdvancedAnvilRecipe> recipe = getCurrentRecipe();
        //ItemStack result1 = recipe.get().getResultItem(getLevel().registryAccess());
        //ItemStack result = new ItemStack(ModItems.BURGER_SWORD.get(),1);
        //this.itemHandler.extractItem(LEFT_INPUT_SLOT,1,false);
        //this.itemHandler.extractItem(RIGHT_INPUT_SLOT,1,false);
        //this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result1.getItem(),
               // this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result1.getCount()));

    }


    public static Map<Enchantment, Integer> combineMaps(Map<Enchantment, Integer> map1, Map<Enchantment, Integer> map2) {
        HashMap<Enchantment, Integer> result = new HashMap<>();


        for (Map.Entry<Enchantment, Integer> entry : map1.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }


        for (Map.Entry<Enchantment, Integer> entry : map2.entrySet()) {
            Enchantment key = entry.getKey();
            Integer value = entry.getValue();


            if (result.containsKey(key)) {
                Integer existingValue = result.get(key);

                if (existingValue.equals(value)) {

                    result.put(key, existingValue + 1);
                } else {

                    result.put(key, Math.max(existingValue, value));
                }
            } else {
                result.put(key, value);
            }
        }

        return result;
    }

    private boolean isEnchantedBook(ItemStack item, Enchantment enchantment, int level){
        return item.areShareTagsEqual(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment,level)));
    }

    private boolean progresssFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }


    private boolean hasRecipe() {


        //boolean hasCraftingItem = this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT).getItem() == ModItems.BURGER_SWORD.get();
        boolean hasCraftingItem = !this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT).isEmpty();

        //ItemStack result = new ItemStack(ModItems.BURGER_SWORD.get());
        ItemStack result = this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT);

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());

        /*
        Optional<AdvancedAnvilRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());

         */
    }

    private Optional<AdvancedAnvilRecipe> getCurrentRecipe(){
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i,this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AdvancedAnvilRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
