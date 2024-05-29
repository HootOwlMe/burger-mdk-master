package net.hootowlme.burgermod;

import com.mojang.logging.LogUtils;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.item.ModCreativeModeTabs;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.loot.ModLootModifiers;
import net.hootowlme.burgermod.sound.ModSounds;
import net.hootowlme.burgermod.villager.ModVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

                if (player.fallDistance >= 3 && player.fallDistance <= 8){

                    if (!(blockUnderPlayer == Blocks.AIR)){
                        player.resetFallDistance();
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

        }
    }
}
