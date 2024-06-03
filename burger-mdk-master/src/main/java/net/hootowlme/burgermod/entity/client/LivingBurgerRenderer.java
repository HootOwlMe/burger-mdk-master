package net.hootowlme.burgermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LivingBurgerRenderer extends MobRenderer<LivingBurgerEntity, LivingBurgerModel<LivingBurgerEntity>> {

    public LivingBurgerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LivingBurgerModel<>(pContext.bakeLayer(ModModelLayers.LIVING_BURGER_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingBurgerEntity pEntity) {
        return new ResourceLocation(BurgerMod.MOD_ID, "textures/entity/living_burger/living_burger.png");
    }

    @Override
    public void render(LivingBurgerEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()){
            pMatrixStack.scale(0.25f,0.25f,0.25f);
        }

        if(!pEntity.isBaby()){
            pMatrixStack.scale(2.25f,2.25f,2.25f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}



