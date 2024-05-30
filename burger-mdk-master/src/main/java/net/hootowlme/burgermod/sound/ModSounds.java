package net.hootowlme.burgermod.sound;

import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BurgerMod.MOD_ID);




    public static final RegistryObject<SoundEvent> BURGER_DETECTOR_FOUND = registerSoundEvents("burger_detector_found");

    public static final RegistryObject<SoundEvent> BURGER_BLOCK_BREAK = registerSoundEvents("burger_block_break");
    public static final RegistryObject<SoundEvent> BURGER_BLOCK_STEP = registerSoundEvents("burger_block_step");
    public static final RegistryObject<SoundEvent> BURGER_BLOCK_FALL = registerSoundEvents("burger_block_fall");
    public static final RegistryObject<SoundEvent> BURGER_SOUND_PLACE = registerSoundEvents("burger_block_place");
    public static final RegistryObject<SoundEvent> BURGER_BLOCK_HIT = registerSoundEvents("burger_block_hit");

    public static final RegistryObject<SoundEvent> BLOOPIN_DISC = registerSoundEvents("bloopin_disc");


    public static final ForgeSoundType BURGER_BLOCK_SOUNDS = new ForgeSoundType(1.25f,1.25f,
            ModSounds.BURGER_BLOCK_BREAK, ModSounds.BURGER_BLOCK_STEP, ModSounds.BURGER_SOUND_PLACE, ModSounds.BURGER_BLOCK_HIT, ModSounds.BURGER_BLOCK_FALL);


    public static RegistryObject<SoundEvent> registerSoundEvents (String name){
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BurgerMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }

}
