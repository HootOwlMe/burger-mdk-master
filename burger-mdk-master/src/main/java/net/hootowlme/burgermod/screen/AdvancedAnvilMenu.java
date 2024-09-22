package net.hootowlme.burgermod.screen;

import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.entity.AdvancedAnvilBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class AdvancedAnvilMenu extends AbstractContainerMenu {

    public final AdvancedAnvilBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public AdvancedAnvilMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){
        this(pContainerId,inv,inv.player.level().getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(4));
    }

    public AdvancedAnvilMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.ADVANCED_ANVIL_MENU.get(),pContainerId);
        checkContainerSize(inv,4);
        blockEntity = ((AdvancedAnvilBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,0,27,47));
            this.addSlot(new SlotItemHandler(iItemHandler,1,76,47));
            this.addSlot(new SlotItemHandler(iItemHandler,2,134,47));
            this.addSlot(new SlotItemHandler(iItemHandler,3,134,18));

        });

        addDataSlots(data);

    }

    public boolean isCrafting(){
        return data.get(0) > 0;
    }

    public int getScaledProgress(){
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize /maxProgress : 0;
    }


    private void addPlayerInventory(Inventory playerInventory){
        for(int i = 0; i < 3; ++i){
            for(int v = 0; v < 9; ++v){
                this.addSlot(new Slot(playerInventory,v + i * 9 + 9,8 + v * 18,84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for (int i = 0; i < 9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT + PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 4; //my slots
    @Override
    public ItemStack quickMoveStack(Player player, int pIndex) {

        Slot sourceSlot = slots.get(pIndex);
        if(sourceSlot == null || !sourceSlot.hasItem()){
            return ItemStack.EMPTY;
        }
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if(pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT){

            if(!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)){
                return ItemStack.EMPTY;
            }

        }else if(pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT){
            if(!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)){
                return ItemStack.EMPTY;
            }
        }else{
            System.out.println("Invalid slotIndex " + pIndex);
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0){
            sourceSlot.set(ItemStack.EMPTY);
        }else{
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player,sourceStack);
        return copyOfSourceStack;

    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),player, ModBlocks.ADVANCED_ANVIL.get());
    }
}
