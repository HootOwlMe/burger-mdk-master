package net.hootowlme.burgermod.entity.ai;

import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class LivingBurgerAttackGoal extends MeleeAttackGoal {

    private final LivingBurgerEntity entity;
    private int attackDelay = 12;
    private int ticksUntilNextAttack = 15;
    private boolean shouldCountTillNextAttack = false;

    public LivingBurgerAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((LivingBurgerEntity) pMob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 12;
        ticksUntilNextAttack = 15;
    }


    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if(isEnemyWithinAttackDistance(pEnemy,pDistToEnemySqr)){
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()){
                entity.setAttacking(true);
            }

            if(isTimeToAttack()){
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        }else{
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEntity, double pDistToEnemySqr){
        return pDistToEnemySqr <= this.getAttackReachSqr(pEntity);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay*2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation(){
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack){
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
