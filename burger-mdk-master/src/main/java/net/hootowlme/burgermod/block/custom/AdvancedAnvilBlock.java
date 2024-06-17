package net.hootowlme.burgermod.block.custom;

import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.entity.AdvancedAnvilBlockEntity;
import net.hootowlme.burgermod.block.entity.ModBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvancedAnvilBlock extends BaseEntityBlock {

    public static final VoxelShape SHAPE = Block.box(1,0,3,15,7.25,13);


    public AdvancedAnvilBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.literal("Transfers Enchantments!").withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    @Override
    public float defaultDestroyTime() {
        return super.defaultDestroyTime();
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {

        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

            if(blockEntity instanceof AdvancedAnvilBlockEntity){
                ((AdvancedAnvilBlockEntity) blockEntity).drops();
            }

        }
        super.onRemove(pState,pLevel,pPos,pNewState,pIsMoving);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof AdvancedAnvilBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer) pPlayer),(AdvancedAnvilBlockEntity) entity, pPos);
            }else{
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        if (pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.ADVANCED_ANVIL_BE.get(),(level, blockPos, blockState, advancedAnvilBlockEntity) -> advancedAnvilBlockEntity.tick(level, blockPos, blockState));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AdvancedAnvilBlockEntity(blockPos, blockState);
    }
}
