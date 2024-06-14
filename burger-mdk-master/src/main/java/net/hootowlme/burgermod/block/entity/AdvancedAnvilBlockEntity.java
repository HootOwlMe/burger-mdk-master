package net.hootowlme.burgermod.block.entity;

import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.hootowlme.burgermod.event.ModEvents;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.recipe.AdvancedAnvilRecipe;
import net.hootowlme.burgermod.screen.AdvancedAnvilMenu;
import net.minecraft.client.player.Input;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;

public class AdvancedAnvilBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static int LEFT_INPUT_SLOT = 0;
    private static int RIGHT_INPUT_SLOT = 1;
    private static int OUTPUT_SLOT = 2;

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
        ItemStack result = new ItemStack(this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT).getItem(),1);
        ItemStack leftInputItem = this.itemHandler.getStackInSlot(LEFT_INPUT_SLOT);
        ItemStack rightInputItem = this.itemHandler.getStackInSlot(RIGHT_INPUT_SLOT);
        Map<Enchantment,Integer> initialEnchantmentsMap = leftInputItem.getAllEnchantments();
        Set<Enchantment> initialEnchantmentsSet = initialEnchantmentsMap.keySet();


        //mending 3
        if((rightInputItem.getEnchantmentLevel(Enchantments.MENDING) >= 3) || isEnchantedBook(rightInputItem, Enchantments.MENDING,3)){
            if(!(result.getEnchantmentLevel(Enchantments.MENDING) >= 3)){
                initialEnchantmentsMap.remove(Enchantments.MENDING);
                result.enchant(Enchantments.MENDING,3);
            }
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result);
            this.itemHandler.extractItem(LEFT_INPUT_SLOT,1,false);
            this.itemHandler.extractItem(RIGHT_INPUT_SLOT,1,false);
        }
        //sharp 10
        if((rightInputItem.getEnchantmentLevel(Enchantments.SHARPNESS) >= 10) || isEnchantedBook(rightInputItem, Enchantments.SHARPNESS,10)){
            if(!(result.getEnchantmentLevel(Enchantments.SHARPNESS) >= 10)){
                initialEnchantmentsMap.remove(Enchantments.SHARPNESS);
                result.enchant(Enchantments.SHARPNESS,10);
            }
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result);
            this.itemHandler.extractItem(LEFT_INPUT_SLOT,1,false);
            this.itemHandler.extractItem(RIGHT_INPUT_SLOT,1,false);
        }
        //prot 10
        if((rightInputItem.getEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION) >= 10) || isEnchantedBook(rightInputItem,Enchantments.ALL_DAMAGE_PROTECTION,10)){
            if(!(result.getEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION) >= 10)){
                initialEnchantmentsMap.remove(Enchantments.ALL_DAMAGE_PROTECTION);
                result.enchant(Enchantments.ALL_DAMAGE_PROTECTION,10);
            }
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result);
            this.itemHandler.extractItem(LEFT_INPUT_SLOT,1,false);
            this.itemHandler.extractItem(RIGHT_INPUT_SLOT,1,false);
        }

        //adds previous enchants
        Iterator<Enchantment> iterator = initialEnchantmentsSet.iterator();
        for (int i = 0; i < initialEnchantmentsSet.size(); i++){
            Enchantment ench = iterator.next();
            int enchLvl = leftInputItem.getEnchantmentLevel(ench);
            result.enchant(ench,enchLvl);
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

    private boolean isEnchantedBook(ItemStack item, Enchantment enchantment, int level){
        boolean isBook = false;
        if(item.areShareTagsEqual(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment,level)))){
            isBook = true;
        }
        return isBook;
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
