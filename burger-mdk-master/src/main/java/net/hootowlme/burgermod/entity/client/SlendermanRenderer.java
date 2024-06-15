package net.hootowlme.burgermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hootowlme.burgermod.BurgerMod;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.hootowlme.burgermod.entity.custom.SlendermanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class SlendermanRenderer extends MobRenderer<SlendermanEntity, SlendermanModel<SlendermanEntity>> {

    public SlendermanRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SlendermanModel<>(pContext.bakeLayer(ModModelLayers.SLENDERMAN_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SlendermanEntity pEntity) {
        return new ResourceLocation(BurgerMod.MOD_ID, "textures/entity/slenderman/slenderman_texture.png");
    }

    @Override
    public void render(SlendermanEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {



        if(!pEntity.isBaby()){
            pMatrixStack.scale(1.1f,1.1f,1.1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }



}
