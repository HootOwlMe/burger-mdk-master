package net.hootowlme.burgermod;

import com.mojang.logging.LogUtils;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.block.entity.ModBlockEntities;
import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.hootowlme.burgermod.entity.ModEntities;
import net.hootowlme.burgermod.entity.client.LivingBurgerRenderer;
import net.hootowlme.burgermod.entity.client.SlendermanRenderer;
import net.hootowlme.burgermod.item.ModCreativeModeTabs;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.loot.ModLootModifiers;
import net.hootowlme.burgermod.recipe.ModRecipes;
import net.hootowlme.burgermod.screen.AdvancedAnvilScreen;
import net.hootowlme.burgermod.screen.ModMenuTypes;
import net.hootowlme.burgermod.sound.ModSounds;
import net.hootowlme.burgermod.villager.ModVillagers;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.functions.SetAttributesFunction;
import net.minecraft.world.ticks.TickPriority;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BurgerMod.MOD_ID)
public class BurgerMod {

    public static final String MOD_ID = "burgermod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BurgerMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModVillagers.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        //ModRecipes.register(modEventBus);

        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void PlayerTickEvent(TickEvent.PlayerTickEvent event){

        Player player = event.player;
        AttributeModifier blockReachDown = new AttributeModifier(player.getName().getString(), -150, AttributeModifier.Operation.ADDITION);
        AttributeModifier blockReachUp = new AttributeModifier(player.getName().getString(), 150, AttributeModifier.Operation.ADDITION);

        if (!event.player.level().isClientSide()){
            boolean holdingBurgerSword = (player.getMainHandItem().getItem() == ModItems.BURGER_SWORD.get());
            if(!holdingBurgerSword){
                if (player.getAttribute(ForgeMod.BLOCK_REACH.get()).getValue() > 5){
                    player.getAttribute(ForgeMod.BLOCK_REACH.get()).addTransientModifier(blockReachDown);
                }
            }else{
                if (player.getAttribute(ForgeMod.BLOCK_REACH.get()).getValue() < 5){
                    player.getAttribute(ForgeMod.BLOCK_REACH.get()).addTransientModifier(blockReachUp);
                }
            }
            AttributeModifier burgerGravityDown = new AttributeModifier(player.getName().getString(), -0.01, AttributeModifier.Operation.ADDITION);
            AttributeModifier burgerGravityUp = new AttributeModifier(player.getName().getString(), 0.01, AttributeModifier.Operation.ADDITION);
            BlockState blockStateUnderPlayer = player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()));
            Block blockUnderPlayer = blockStateUnderPlayer.getBlock();
            boolean wearingBurgerSet = (
                    (player.getInventory().getArmor(2).getItem() == ModItems.BURGER_CHESTPLATE.get())
                            && (player.getInventory().getArmor(1).getItem() == ModItems.BURGER_LEGGINGS.get())
                            && (player.getInventory().getArmor(0).getItem() == ModItems.BURGER_BOOTS.get())
                            && (player.getInventory().getArmor(3).getItem() == ModItems.BURGER_HELMET.get()));

            if(wearingBurgerSet){
                if (player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() > 0.04) {
                    player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(burgerGravityDown);
                }
                if (player.fallDistance >= 3){
                    if(!((blockUnderPlayer == Blocks.AIR) || (blockUnderPlayer == Blocks.CAVE_AIR))){
                        player.fallDistance = player.fallDistance - 2;
                    }
                }
            }else{
                if (player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() < 0.08){
                    player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(burgerGravityUp);
                }
                if (player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() > 0.08){
                    player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(burgerGravityDown);
                }
            }



        }

        BlockState blockStateUnderPlayer = player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()));
        BlockState blockState2UnderPlayer = player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-2,(int)player.getZ()));
        BlockState blockState3UnderPlayer = player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-3,(int)player.getZ()));
        Block blockUnderPlayer = blockStateUnderPlayer.getBlock();
        Block block2UnderPlayer = blockState2UnderPlayer.getBlock();
        Block block3UnderPlayer = blockState2UnderPlayer.getBlock();
        BlockPos location = player.blockPosition();
        if(player.getInventory().getArmor(0).getEnchantmentLevel(ModEnchantments.AIR_WALKER.get()) > 0){

            boolean airUnderPlayer = (blockUnderPlayer == Blocks.AIR) || (blockUnderPlayer == Blocks.CAVE_AIR) || (blockUnderPlayer == Blocks.VOID_AIR);
            boolean air2UnderPlayer = (block2UnderPlayer == Blocks.AIR) || (block2UnderPlayer == Blocks.CAVE_AIR) || (block2UnderPlayer == Blocks.VOID_AIR);
            boolean airWalkUnderPlayer = blockUnderPlayer == ModBlocks.AIR_WALK_BLOCK.get();
            boolean airWalk2UnderPlayer = (block2UnderPlayer == ModBlocks.AIR_WALK_BLOCK.get());
            boolean airAroundPlayer1 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ())).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.VOID_AIR));

            boolean airAroundPlayer2 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ())).is(ModBlocks.AIR_WALK_BLOCK.get())
                    || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.AIR)
                    || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.CAVE_AIR)
                    || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ())).is(Blocks.VOID_AIR));

            boolean airAroundPlayer3 =
                    (player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()+1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                    || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.AIR)
                    || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.VOID_AIR)
                    || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.CAVE_AIR));

            boolean airAroundPlayer4 =
                    (player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()-1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX(),(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.VOID_AIR));

            boolean airAroundPlayer5 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()-1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.VOID_AIR));

            boolean airAroundPlayer6 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()+1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.VOID_AIR));

            boolean airAroundPlayer7 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()+1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.VOID_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()-1,(int)player.getY()-1,(int)player.getZ()+1)).is(Blocks.AIR));

            boolean airAroundPlayer8 =
                    (player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()-1)).is(ModBlocks.AIR_WALK_BLOCK.get())
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.CAVE_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.VOID_AIR)
                            || player.level().getBlockState(new BlockPos((int)player.getX()+1,(int)player.getY()-1,(int)player.getZ()-1)).is(Blocks.AIR));

            boolean noBlocksAroundPlayer = airAroundPlayer1 && airAroundPlayer2 && airAroundPlayer3 && airAroundPlayer4 && airAroundPlayer5 && airAroundPlayer6 && airAroundPlayer8 && airAroundPlayer7;

            if(player.isCrouching()){
                if(!player.isFallFlying()){

                    if((airUnderPlayer && air2UnderPlayer) || (airWalkUnderPlayer && air2UnderPlayer) || (airWalkUnderPlayer && airWalk2UnderPlayer)){
                        if(player.getInventory().getArmor(0).getEnchantmentLevel(ModEnchantments.AIR_WALKER.get()) > 1){
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,2,5, true,false,false));
                        }

                        if(noBlocksAroundPlayer){
                            player.level().setBlock(location.below(), ModBlocks.AIR_WALK_BLOCK.get().defaultBlockState(),1);
                        }


                        //player.level().scheduleTick(location.below(), ModBlocks.AIR_WALK_BLOCK.get(), 70, TickPriority.HIGH);
                    }


                }


            }

        }

    }





    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BURGER_FLOWER.getId(), ModBlocks.POTTED_BURGER_FLOWER);
        });

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BURGER);
            event.accept(ModItems.FRIES);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntities.LIVING_BURGER.get(), LivingBurgerRenderer::new);
            EntityRenderers.register(ModEntities.SLENDERMAN.get(), SlendermanRenderer::new);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.AIR_WALK_BLOCK.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.ADVANCED_ANVIL_MENU.get(), AdvancedAnvilScreen::new);
        }

    }
}
