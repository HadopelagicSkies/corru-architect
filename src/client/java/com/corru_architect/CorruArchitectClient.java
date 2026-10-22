package com.corru_architect;

import com.corru_architect.entity.CorruGolemEntityModel;
import com.corru_architect.entity.CorruGolemEntityRenderer;
import com.corru_architect.puzzle.BookPuzzlePageRenderer;
import com.corru_architect.puzzle.PuzzleScreen;
import com.klikli_dev.modonomicon.client.render.page.PageRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.*;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CorruArchitectClient implements ClientModInitializer {

	public static final EntityModelLayer CORRU_GOLEM_MODEL_LAYER = new EntityModelLayer(Identifier.of(CorruArchitect.MOD_ID, "corru_golem"), "corru_golem");

	@Override
	public void onInitializeClient() {


		HandledScreens.register(CorruArchitect.PUZZLE_SCREEN_HANDLER, PuzzleScreen::new);

		EntityRendererRegistry.register(CorruArchitectEntities.CORRU_GOLEM, (context) -> new CorruGolemEntityRenderer(context) {
		});
		EntityModelLayerRegistry.registerModelLayer(CORRU_GOLEM_MODEL_LAYER, CorruGolemEntityModel::getTexturedModelData);

		PageRendererRegistry.registerPageRenderer(Identifier.of(CorruArchitect.MOD_ID, "puzzle_page"), new BookPuzzlePageRenderer.BookPuzzlePageRendererFactory());
	}
}