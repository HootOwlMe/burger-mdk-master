package net.hootowlme.burgermod.block.entity;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BurgerMod.MOD_ID);


    public static final RegistryObject<BlockEntityType<AdvancedAnvilBlockEntity>> ADVANCED_ANVIL_BE = BLOCK_ENTITIES.register("advanced_anvil_be", () ->
            BlockEntityType.Builder.of(AdvancedAnvilBlockEntity::new, ModBlocks.ADVANCED_ANVIL.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
