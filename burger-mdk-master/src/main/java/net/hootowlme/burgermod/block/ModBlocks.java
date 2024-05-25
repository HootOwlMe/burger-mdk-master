package net.hootowlme.burgermod.block;

import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.block.custom.BurgerCropBlock;
import net.hootowlme.burgermod.block.custom.BurgerSoundBlock;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.item.custom.FuelItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    //YOU NEED TO FILL IN ALL:
    // datagen -> loot -> ModBlockLootTables
    // ModBlockStateProvider
    // ModCreativeModeTabs
    // lang -> en_us
    // textures -> block
    // ModBlockTagGenerator
    // ModItemModelProvider (if needed)

    public static  final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BurgerMod.MOD_ID);

    public static final RegistryObject<Block> BURGER_BLOCK = registerBlock("burger_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> WHITE_BLOCK = registerBlock("white_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));

    public static final RegistryObject<Block> FLOOR_BLOCK = registerBlock("restaurant_floor",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));

    public static final RegistryObject<Block> BURGER_SOUND = registerBlock("burger_sound",
            () -> new BurgerSoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> BURGER_ORE = registerBlock("burger_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(1, 9999)));

    public static final RegistryObject<Block> RESTAURANT_LOW = registerBlock("restaurant_low",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));


    public static final RegistryObject<Block> BLACK_BLOCK = registerBlock("black_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.STONE)));

    public static final RegistryObject<Block> BLACK_FENCE = registerBlock("black_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.STONE)));



    public static final RegistryObject<Block> BURGER_CROP = BLOCKS.register("burger_crop",
            () -> new BurgerCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.STONE).noOcclusion().noCollission()));



    public static final RegistryObject<Block> RESTAURANT_CEILING = registerBlock("restaurant_ceiling",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));

    public static final RegistryObject<Block> RESTAURANT_TOP = registerBlock("restaurant_top",
            () -> new StairBlock(() -> ModBlocks.RESTAURANT_LOW.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.QUARTZ_STAIRS)));

    public static final RegistryObject<Block> BURGER_STAIRS = registerBlock("burger_stairs",
           () -> new StairBlock(() -> ModBlocks.BURGER_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> RED_STAIRS = registerBlock("red_stairs",
            () -> new StairBlock(() -> ModBlocks.RESTAURANT_LOW.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    public static final RegistryObject<Block> BURGER_BUTTON = registerBlock("burger_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.SLIME_BLOCK),
                    BlockSetType.OAK, 20, true));

    public static final RegistryObject<Block> BURGER_SLAB = registerBlock("burger_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> BURGER_WALL = registerBlock("burger_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> BURGER_PRESSURE_PLATE = registerBlock("burger_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK),
                    BlockSetType.OAK));

    public static final RegistryObject<Block> WHITE_PRESSURE_PLATE = registerBlock("white_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK),
                    BlockSetType.GOLD));

    public static final RegistryObject<Block> BURGER_FENCE = registerBlock("burger_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> BURGER_FENCE_GATE = registerBlock("burger_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK), SoundEvents.GHAST_SCREAM, SoundEvents.LLAMA_SPIT));

    public static final RegistryObject<Block>  BURGER_DOOR = registerBlock("burger_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK).noOcclusion(), BlockSetType.OAK));

    public static final RegistryObject<Block> BURGER_TRAPDOOR = registerBlock("burger_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK).noOcclusion(), BlockSetType.OAK));



    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private  static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;

    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }




}
