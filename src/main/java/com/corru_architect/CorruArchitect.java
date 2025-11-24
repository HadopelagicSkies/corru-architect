package com.corru_architect;

import com.corru_architect.screenhandlers.ArchiveScreenHandler;
import net.fabricmc.api.ModInitializer;

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

	public static final ScreenHandlerType<ArchiveScreenHandler> ARCHIVE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CorruArchitect.MOD_ID, "archive_screen"), new ScreenHandlerType<>(ArchiveScreenHandler::new, FeatureSet.empty()));

	@Override
	public void onInitialize() {

		CorruArchitectItems.initialize();
		CorruArchitectBlocks.initialize();
		CorruArchitectBlockEntities.initialize();
		CorruArchitectComponents.initialize();
		CorruArchitectEntities.initialize();

		LOGGER.info("Corru.Architect Loaded");
	}
}