package com.corru_architect.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;


public class CorruGolemEntityModel extends BipedEntityModel<CorruGolemEntityRenderState> {
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone5;
    protected CorruGolemEntityModel(ModelPart root) {
        super(root);
        this.bone3 = this.body.getChild("bone3");
        this.bone4 = this.body.getChild("bone4");
        this.bone = this.body.getChild("bone");
        this.bone2 = this.body.getChild("bone2");
        this.bone5 = this.body.getChild("bone5");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(40, 30).cuboid(-1.0F, -1.1F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.0F, -14.5F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData bone3 = body.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.5F, 0.0F));

        ModelPartData cube_r2 = bone3.addChild("cube_r2", ModelPartBuilder.create().uv(24, 39).cuboid(-2.0F, 3.0F, -3.75F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 14).cuboid(-3.0F, -1.0F, -3.75F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2182F, 0.7854F, 0.0F));

        ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(36, 33).cuboid(-2.0F, 3.0F, -3.75F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 25).cuboid(-3.0F, -1.0F, -3.75F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2182F, -0.7854F, 0.0F));

        ModelPartData bone4 = body.addChild("bone4", ModelPartBuilder.create(), ModelTransform.of(0.0F, -15.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(38, 24).cuboid(-2.0F, 3.0F, -3.75F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 9).cuboid(-3.0F, -1.0F, -3.75F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2182F, -0.7854F, 0.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(3.9F, -15.0F, 0.0F));

        ModelPartData cube_r5 = bone.addChild("cube_r5", ModelPartBuilder.create().uv(8, 18).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3054F));

        ModelPartData cube_r6 = bone.addChild("cube_r6", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.8F, 0.0F, 0.0F, 0.0F, -0.7854F, -0.3054F));

        ModelPartData bone2 = body.addChild("bone2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -15.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r7 = bone2.addChild("cube_r7", ModelPartBuilder.create().uv(16, 25).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.9F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3054F));

        ModelPartData cube_r8 = bone2.addChild("cube_r8", ModelPartBuilder.create().uv(18, 9).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.9F, 0.0F, 0.0F, 0.0F, -0.7854F, -0.3054F));

        ModelPartData bone5 = body.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(0.0F, -15.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r9 = bone5.addChild("cube_r9", ModelPartBuilder.create().uv(34, 39).cuboid(-2.0F, 3.0F, -3.75F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 19).cuboid(-3.0F, -1.0F, -3.75F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2182F, 0.7854F, 0.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(0, 34).cuboid(-4.0F, -5.0F, 0.0F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 41).cuboid(-3.0F, -4.0F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 20).cuboid(-3.0F, -4.0F, 4.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 33).cuboid(-4.0F, -5.0F, 3.0F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 9).cuboid(-5.0F, -6.0F, 1.0F, 7.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -8.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(0, 40).cuboid(4.5F, -10.3F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(40, 9).cuboid(-7.5F, -10.3F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.5F, 0.0F));

        ModelPartData cube_r11 = hat.addChild("cube_r11", ModelPartBuilder.create().uv(32, 6).cuboid(-5.85F, 0.375F, 0.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.9F, -9.8F, -1.0F, 0.0F, 0.0F, 0.1309F));

        ModelPartData cube_r12 = hat.addChild("cube_r12", ModelPartBuilder.create().uv(32, 3).cuboid(-5.85F, -1.4F, 0.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.9F, -9.8F, -1.0F, 0.0F, 0.0F, -0.1309F));

        ModelPartData cube_r13 = hat.addChild("cube_r13", ModelPartBuilder.create().uv(44, 39).cuboid(-2.75F, -0.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -8.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r14 = hat.addChild("cube_r14", ModelPartBuilder.create().uv(32, 0).cuboid(-0.15F, 0.375F, 0.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.9F, -9.8F, -1.0F, 0.0F, 0.0F, -0.1309F));

        ModelPartData cube_r15 = hat.addChild("cube_r15", ModelPartBuilder.create().uv(24, 30).cuboid(-0.15F, -1.4F, 0.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.9F, -9.8F, -1.0F, 0.0F, 0.0F, 0.1309F));

        ModelPartData cube_r16 = hat.addChild("cube_r16", ModelPartBuilder.create().uv(0, 43).cuboid(0.75F, -0.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -8.75F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(12, 34).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, 10.0F, 0.0F));

        ModelPartData cube_r17 = left_arm.addChild("cube_r17", ModelPartBuilder.create().uv(18, 41).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 6.8F, 0.025F, -0.3927F, 0.0F, 0.0F));

        ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(5.4F, 9.7F, 0.0F));

        ModelPartData cube_r18 = left_leg.addChild("cube_r18", ModelPartBuilder.create().uv(40, 12).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.3F, 0.0F, -0.7854F, 0.0F, 0.3229F));

        ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(44, 43).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 10.0F, 0.0F));

        ModelPartData cube_r19 = right_arm.addChild("cube_r19", ModelPartBuilder.create().uv(8, 45).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 6.8F, 0.025F, -0.3927F, 0.0F, 0.0F));

        ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-5.4F, 9.7F, 0.0F));

        ModelPartData cube_r20 = right_leg.addChild("cube_r20", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.3F, 0.0F, -0.7854F, 0.0F, -0.3229F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
