package com.corru_architect.entity;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectClient;
import com.corru_architect.entities.CorruGolemEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class CorruGolemEntityRenderer extends BipedEntityRenderer<CorruGolemEntity,CorruGolemEntityRenderState,CorruGolemEntityModel> {
    public CorruGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CorruGolemEntityModel(context.getPart(CorruArchitectClient.CORRU_GOLEM_MODEL_LAYER)), 0.25F);
    }

    @Override
    public Identifier getTexture(CorruGolemEntityRenderState state) {
        return Identifier.of(CorruArchitect.MOD_ID, "textures/entity/corru_golem.png");
    }

    @Override
    public CorruGolemEntityRenderState createRenderState() {
        return new CorruGolemEntityRenderState();
    }

    @Override
    public void updateRenderState(CorruGolemEntity mobEntity, CorruGolemEntityRenderState bipedEntityRenderState, float f) {
        super.updateRenderState(mobEntity, bipedEntityRenderState, f);
        bipedEntityRenderState.humor1=mobEntity.getHumor1();
        bipedEntityRenderState.humor2=mobEntity.getHumor2();
        bipedEntityRenderState.humor3=mobEntity.getHumor3();
    }
}
