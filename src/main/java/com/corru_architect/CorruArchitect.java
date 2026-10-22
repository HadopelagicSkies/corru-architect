package com.corru_architect;

import com.corru_architect.base_manager.CorruBaseResources;
import com.corru_architect.packet_payloads.GrantAdvancementPayload;
import com.corru_architect.screenhandlers.PuzzleScreenHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorruArchitect implements ModInitializer {
	public static final String MOD_ID = "corru_architect";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ScreenHandlerType<PuzzleScreenHandler> PUZZLE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CorruArchitect.MOD_ID, "puzzle_screen"), new ScreenHandlerType<>(PuzzleScreenHandler::new, FeatureSet.empty()));
	public static final String BOOK_ID = "archive_book";
	public static final Identifier FONT_ID = Identifier.of(CorruArchitect.MOD_ID, "font/include/default.json");


	@Override
	public void onInitialize() {



		CorruArchitectItems.initialize();
		CorruArchitectBlocks.initialize();
		CorruArchitectBlockEntities.initialize();
		CorruArchitectComponents.initialize();
		CorruArchitectEntities.initialize();

		CorruBaseResources.initialize();

		PayloadTypeRegistry.playC2S().register(GrantAdvancementPayload.ID, GrantAdvancementPayload.GRANT_ADVANCEMENT_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(GrantAdvancementPayload.ID, (payload, context) -> {
			RecipeUnlockMapping.grantAdvancement(context.player(),payload.puzzleName());
		});



		LOGGER.info("Corru.Architect Loaded");
	}
}