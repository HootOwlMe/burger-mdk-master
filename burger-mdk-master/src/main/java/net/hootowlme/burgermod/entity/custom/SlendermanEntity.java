package net.hootowlme.burgermod.entity.custom;


import net.hootowlme.burgermod.entity.ModEntities;
import net.hootowlme.burgermod.item.ModItems;
import net.hootowlme.burgermod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class SlendermanEntity extends PathfinderMob {

    public SlendermanEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super (pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if(!this.hasEffect(MobEffects.BLINDNESS)){
            this.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,999));
        }

        if(this.level().isDay()){
            this.remove(RemovalReason.DISCARDED);
        }

        if(this.getHealth() < this.getMaxHealth()-100){
            //this.remove(RemovalReason.valueOf("t h o u g h t  y o u  c o u l d  k i l l  m e ?"));

            List<? extends Player> list = this.level().players();
            for(int i = 0; i < list.size(); i++){
                list.get(i).sendSystemMessage(Component.literal("t h o u g h t  y o u  c o u l d  k i l l  m e ?").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
            }

            this.remove(RemovalReason.DISCARDED);
        }

        if(this.level().isClientSide()){
            setupAnimationStates();
        }

        Vec3 firstVec = new Vec3(this.getX(),this.getY(),this.getZ());
        if(this.level().getEntitiesOfClass(SlendermanEntity.class,AABB.ofSize(firstVec,400,150,400)).size() > 1){
            this.remove(RemovalReason.DISCARDED);
        }

    }



    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {

        if((pSource.type().equals(damageSources().playerAttack(lastHurtByPlayer).type()))){
            return super.hurt(pSource, pAmount);
        }else{
            return false;
        }

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class,false));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1D, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 100f));
        //this.goalSelector.addGoal(2, new TemptGoal(this,0.7d,Ingredient.of(ModItems.BURGER_SWORD.get()),false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 1000).add(Attributes.MOVEMENT_SPEED, 1.1D)
                .add(Attributes.ARMOR, 50f).add(Attributes.ATTACK_DAMAGE,75f).add(Attributes.FOLLOW_RANGE,20D)
                .add(Attributes.ATTACK_KNOCKBACK,3.5D);
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


    @Override
    public int getAmbientSoundInterval() {
        return 5;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.WHITE_NOISE.get();
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private void setupAnimationStates(){

        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }

    }


}
