package net.hootowlme.burgermod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.item.ModItems;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BurgerMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){

        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.BREAD,2),
                    new ItemStack(ModItems.BURGER.get(),2),
                    15,100, 0.02f));

        }

        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(ModItems.BURGER.get(),1),
                    new ItemStack(Items.DIAMOND,5),
                    10,8, 0.02f));

        }

        if(event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack unbreakingLvl5 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.UNBREAKING,5));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,12),
                    unbreakingLvl5,
                    5,20, 0.02f));
        }

    }

/*
    @SubscribeEvent
    public static void

 */

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){

        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
           new ItemStack(Items.EMERALD, 5),
           new ItemStack(ModItems.FRIES.get(), 16),
                10, 50, 0.2f));

    }


}
