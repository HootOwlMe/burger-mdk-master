package net.hootowlme.burgermod.entity.client;// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hootowlme.burgermod.entity.animations.ModAnimationDefinitions;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class LivingBurgerModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart burger;
	private final ModelPart body;
	private final ModelPart top_bun;
	private final ModelPart bottom_bun;
	private final ModelPart middle_section;
	private final ModelPart patty;
	private final ModelPart tomato;
	private final ModelPart lettuce;

	public LivingBurgerModel(ModelPart root) {
		this.burger = root.getChild("burger");
		this.body = burger.getChild("body");
		this.top_bun = body.getChild("top_bun");
		this.bottom_bun = body.getChild("bottom_bun");
		this.middle_section = body.getChild("middle_section");
		this.patty = middle_section.getChild("patty");
		this.tomato = middle_section.getChild("tomato");
		this.lettuce = middle_section.getChild("lettuce");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition burger = partdefinition.addOrReplaceChild("burger", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition body = burger.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition top_bun = body.addOrReplaceChild("top_bun", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -6.0F, -6.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(18, 36).addBox(-5.0F, -6.0F, -1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-1.0F, -4.0F, -6.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-1.0F, -4.0F, 3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 42).addBox(-1.0F, -4.0F, -4.5F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(59, 20).addBox(1.0F, -4.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 32).addBox(-6.0F, -4.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 30).addBox(1.0F, -6.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(15, 22).addBox(3.0F, -7.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-4.0F, -7.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-3.0F, -7.0F, -3.5F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(44, 32).addBox(-5.0F, -4.0F, 0.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 44).addBox(-5.0F, -4.0F, -5.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(60, 35).addBox(1.0F, -4.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 60).addBox(-4.0F, -4.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 59).addBox(1.0F, -4.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 56).addBox(-4.0F, -4.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(43, 17).addBox(1.0F, -4.0F, -5.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(43, 11).addBox(1.0F, -4.0F, 0.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(56, 15).addBox(-4.0F, -6.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 43).addBox(-5.0F, -6.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-5.0F, -6.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(4.0F, -6.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 24).addBox(4.0F, -6.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 56).addBox(1.0F, -6.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(55, 10).addBox(-4.0F, -6.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 56).addBox(1.0F, -6.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 56).addBox(-3.0F, -6.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 39).addBox(1.0F, -6.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 28).addBox(-3.0F, -6.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = top_bun.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(43, 0).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = top_bun.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(43, 3).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = top_bun.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 44).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -2.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = top_bun.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 50).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -2.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = top_bun.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 1.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r6 = top_bun.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 5).addBox(-6.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 8.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bottom_bun = body.addOrReplaceChild("bottom_bun", CubeListBuilder.create().texOffs(29, 32).addBox(-1.0F, -5.0F, -6.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(28, 12).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(18, 39).addBox(-5.0F, -5.0F, -1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(31, 5).addBox(-1.0F, -3.0F, -6.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(29, 17).addBox(-1.0F, -3.0F, 3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(43, 0).addBox(-1.0F, -3.0F, -4.5F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(59, 26).addBox(1.0F, -3.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(59, 23).addBox(-6.0F, -3.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(55, 56).addBox(1.0F, -5.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 50).addBox(-5.0F, -3.0F, 0.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 50).addBox(-5.0F, -3.0F, -5.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(60, 61).addBox(1.0F, -3.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 61).addBox(-4.0F, -3.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 61).addBox(1.0F, -3.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 61).addBox(-4.0F, -3.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(1.0F, -3.0F, -5.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(44, 44).addBox(1.0F, -3.0F, 0.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(43, 56).addBox(-4.0F, -5.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(62, 2).addBox(-5.0F, -5.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 61).addBox(-5.0F, -5.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 54).addBox(4.0F, -5.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(51, 24).addBox(4.0F, -5.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 42).addBox(1.0F, -5.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(31, 56).addBox(-4.0F, -5.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(1.0F, -5.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 63).addBox(-3.0F, -5.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 38).addBox(1.0F, -5.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 62).addBox(-3.0F, -5.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r7 = bottom_bun.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 47).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = bottom_bun.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 50).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r9 = bottom_bun.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 61).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -1.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = bottom_bun.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 61).addBox(-5.0F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -1.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r11 = bottom_bun.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(14, 23).addBox(-1.0F, -1.0F, -9.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(31, 0).addBox(-1.0F, -2.0F, -8.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition middle_section = body.addOrReplaceChild("middle_section", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition patty = middle_section.addOrReplaceChild("patty", CubeListBuilder.create().texOffs(44, 40).addBox(-4.0F, -2.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 38).addBox(-4.0F, -2.0F, 4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition patty1_r1 = patty.addOrReplaceChild("patty1_r1", CubeListBuilder.create().texOffs(18, 52).addBox(-4.0F, -2.0F, -6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition patty1_r2 = patty.addOrReplaceChild("patty1_r2", CubeListBuilder.create().texOffs(55, 0).addBox(-4.0F, -2.0F, -6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition tomato = middle_section.addOrReplaceChild("tomato", CubeListBuilder.create().texOffs(55, 2).addBox(4.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(19, 54).addBox(-5.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(28, 24).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition tomato1_r1 = tomato.addOrReplaceChild("tomato1_r1", CubeListBuilder.create().texOffs(51, 23).addBox(-4.0F, -2.0F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 7.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition tomato1_r2 = tomato.addOrReplaceChild("tomato1_r2", CubeListBuilder.create().texOffs(10, 52).addBox(-4.0F, -2.0F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition lettuce = middle_section.addOrReplaceChild("lettuce", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -6.0F, 10.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(ModAnimationDefinitions.BURGER_MOVE, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((LivingBurgerEntity) entity).idleAnimationState, ModAnimationDefinitions.BURGER_IDLE, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks){
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F,30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F,45.0F);

		this.top_bun.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.top_bun.xRot = pHeadPitch * ((float) Math.PI / 180F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		burger.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return burger;
	}
}