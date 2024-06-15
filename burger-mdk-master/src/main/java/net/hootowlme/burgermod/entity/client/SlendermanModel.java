package net.hootowlme.burgermod.entity.client;// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hootowlme.burgermod.entity.animations.ModAnimationDefinitions;
import net.hootowlme.burgermod.entity.custom.LivingBurgerEntity;
import net.hootowlme.burgermod.entity.custom.SlendermanEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SlendermanModel <T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "slenderman"), "main");
	private final ModelPart wholebody;
	private final ModelPart arm2;
	private final ModelPart arm1;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart neck;

	public SlendermanModel(ModelPart root) {
		this.wholebody = root.getChild("wholebody");
		this.arm2 = wholebody.getChild("arm2");
		this.arm1 = wholebody.getChild("arm1");
		this.torso = wholebody.getChild("torso");
		this.head = wholebody.getChild("head");
		this.leg1 = wholebody.getChild("leg1");
		this.leg2 = wholebody.getChild("leg2");
		this.neck = wholebody.getChild("neck");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wholebody = partdefinition.addOrReplaceChild("wholebody", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm2 = wholebody.addOrReplaceChild("arm2", CubeListBuilder.create(), PartPose.offset(0.0F, -29.0F, 11.0F));

		PartDefinition cube_r1 = arm2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 23.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2F, -22.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = arm2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 6.5F, -1.0F, 2.0F, 0.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.05F, -13.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition arm1 = wholebody.addOrReplaceChild("arm1", CubeListBuilder.create(), PartPose.offset(0.0F, -29.0F, 11.0F));

		PartDefinition cube_r3 = arm1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 23.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r4 = arm1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, 6.5F, -1.0F, 2.0F, 0.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.05F, -8.75F, 0.3927F, 0.0F, 0.0F));

		PartDefinition torso = wholebody.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-2.25F, -16.0F, -4.1F, 4.5F, 19.5F, 8.2F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

		PartDefinition head = wholebody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(39, 43).addBox(-4.75F, -4.55F, 2.7F, 4.5F, 5.05F, 4.6F, new CubeDeformation(0.0F))
		.texOffs(41, 45).addBox(-3.75F, -4.5F, 3.7F, 2.5F, 0.0F, 2.6F, new CubeDeformation(0.0F))
		.texOffs(26, 9).addBox(-4.75F, -4.35F, 2.5F, 4.5F, 4.55F, 0.2F, new CubeDeformation(0.0F))
		.texOffs(26, 9).addBox(-4.75F, -4.35F, 7.3F, 4.5F, 4.55F, 0.2F, new CubeDeformation(0.0F))
		.texOffs(44, 30).addBox(-4.75F, -4.15F, 2.4F, 4.5F, 4.15F, 0.2F, new CubeDeformation(0.0F))
		.texOffs(26, 14).addBox(-4.75F, -4.15F, 7.4F, 4.5F, 4.15F, 0.2F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -45.6F, -5.0F));

		PartDefinition leg1 = wholebody.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(8, 28).addBox(-3.5F, -14.5F, 6.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 7).addBox(-3.5F, -12.0F, 5.6F, 2.0F, 0.8F, 0.4F, new CubeDeformation(0.0F))
		.texOffs(28, 1).addBox(-3.5F, -11.25F, 5.75F, 2.0F, 1.0F, 0.25F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -10.25F, 5.95F, 2.0F, 0.4F, 0.05F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -13.0F, 4.9F, 2.0F, 0.1F, 0.2F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -13.0F, 5.1F, 2.0F, 0.25F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-3.5F, -13.0F, 5.4F, 2.0F, 1.0F, 0.6F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -11.5F, -4.0F));

		PartDefinition leg2 = wholebody.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(26, 18).addBox(-3.5F, -13.0F, 2.0F, 2.0F, 1.0F, 0.6F, new CubeDeformation(0.0F))
		.texOffs(28, 0).addBox(-3.5F, -11.25F, 2.0F, 2.0F, 1.0F, 0.25F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -10.25F, 2.0F, 2.0F, 0.4F, 0.05F, new CubeDeformation(0.0F))
		.texOffs(18, 7).addBox(-3.5F, -12.0F, 2.0F, 2.0F, 0.8F, 0.4F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -13.0F, 2.6F, 2.0F, 0.25F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -13.0F, 2.9F, 2.0F, 0.1F, 0.2F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-3.5F, -14.5F, 0.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -11.5F, -4.0F));

		PartDefinition neck = wholebody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(44, 25).addBox(-1.6F, -45.1F, -1.6F, 3.2F, 2.1F, 3.2F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimationDefinitions.MOVE_ANIMATION, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((SlendermanEntity) entity).idleAnimationState, ModAnimationDefinitions.SLENDER_IDLE, ageInTicks, 1f);
	}


	@Override
	public ModelPart root() {
		return wholebody;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		wholebody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}