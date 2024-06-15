package net.hootowlme.burgermod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.ModBlocks;
import net.hootowlme.burgermod.enchantment.ModEnchantments;
import net.hootowlme.burgermod.entity.ModEntities;
import net.hootowlme.burgermod.entity.custom.SlendermanEntity;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.util.ModTags;
import net.hootowlme.burgermod.villager.ModVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.print.Book;
import java.util.List;
import java.util.function.Predicate;

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

        if(event.getType() == ModVillagers.BURGER_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack mending3 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING,3));
            //mending3.enchant(Enchantments.MENDING,3);


            ItemStack sharp10 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.SHARPNESS,10));
            //sharp10.enchant(Enchantments.SHARPNESS,10);


            ItemStack prot10 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION,10));
            //prot10.enchant(Enchantments.ALL_DAMAGE_PROTECTION,10);


            ItemStack burger = new ItemStack(ModItems.BURGER.get(),2);
            ItemStack dirt = new ItemStack(Items.DIRT,2);

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BURGER.get(),64),
                    mending3,
                    5,20, 0.02f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BURGER.get(),64),
                    sharp10,
                    5,20, 0.02f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BURGER.get(),64),
                    prot10,
                    5,20, 0.02f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BURGER.get(),64),
                    dirt,
                    5,20, 0.02f));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BURGER.get(),1),
                    burger, 99999999,20, 0.02f));
        }
    }


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
