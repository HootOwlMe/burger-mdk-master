package net.hootowlme.burgermod.event;


import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.client.LivingBurgerModel;
import net.hootowlme.burgermod.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BurgerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){

        event.registerLayerDefinition(ModModelLayers.LIVING_BURGER_LAYER, LivingBurgerModel::createBodyLayer);

    }

}
