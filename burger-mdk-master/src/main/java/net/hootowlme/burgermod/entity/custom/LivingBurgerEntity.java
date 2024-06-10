package net.hootowlme.burgermod.entity.custom;

import net.hootowlme.burgermod.entity.ModEntities;
import net.hootowlme.burgermod.entity.ai.LivingBurgerAttackGoal;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.HTML;
import java.lang.annotation.Target;
import java.util.Collection;

public class LivingBurgerEntity extends Animal {

    public LivingBurgerEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()){
            setupAnimationStates();
        }

    }

    private void setupAnimationStates(){

        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 15; //in ticks, how long attack animation is.
            attackAnimationState.start(this.tickCount);
        }else{
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
        }


    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6F,1f);
        }else{
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(LivingBurgerEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));

        this.goalSelector.addGoal(1, new BreedGoal(this, 1D));

        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(ModItems.BURGER.get()), false));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class,true));
        //this.goalSelector.addGoal(4, new LivingBurgerAttackGoal(this,1D,false));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1D, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this,1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 2f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        //this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class,true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING,false);
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking(){

        return this.entityData.get(ATTACKING);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR, 0.5f).add(Attributes.ATTACK_DAMAGE,2.5f).add(Attributes.FOLLOW_RANGE,15D)
                .add(Attributes.ATTACK_KNOCKBACK,0.5D);

    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.LIVING_BURGER.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.BURGER.get());
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BURGER_BLOCK_STEP.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.BURGER_BLOCK_HIT.get();
    }



    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BURGER_BLOCK_BREAK.get();
    }
}
