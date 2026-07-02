package com.corru_architect;

import com.corru_architect.entity.CorruGolemEntityModel;
import com.corru_architect.entity.CorruGolemEntityRenderer;
import com.corru_architect.screen.ArchiveScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class CorruArchitectClient implements ClientModInitializer {

	public static final EntityModelLayer CORRU_GOLEM_MODEL_LAYER = new EntityModelLayer(Identifier.of(CorruArchitect.MOD_ID,"corru_golem"), "corru_golem");

	@Override
	public void onInitializeClient() {
		HandledScreens.register(CorruArchitect.ARCHIVE_SCREEN_HANDLER, ArchiveScreen::new);

		EntityRendererRegistry.register(CorruArchitectEntities.CORRU_GOLEM,(context) -> new CorruGolemEntityRenderer(context) {});
		EntityModelLayerRegistry.registerModelLayer(CORRU_GOLEM_MODEL_LAYER, CorruGolemEntityModel::getTexturedModelData);
	}
}