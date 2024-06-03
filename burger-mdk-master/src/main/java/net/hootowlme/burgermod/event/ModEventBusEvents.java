package net.hootowlme.burgermod.event;


import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.ModEntities;
import net.hootowlme.burgermod.entity.client.LivingBurgerModel;
import net.hootowlme.burgermod.entity.client.LivingBurgerRenderer;
import net.hootowlme.burgermod.entity.client.ModModelLayers;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BurgerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
   public static void registerAttributes(EntityAttributeCreationEvent event){
       event.put(ModEntities.LIVING_BURGER.get(), LivingBurgerEntity.createAttributes().build());
   }

}
