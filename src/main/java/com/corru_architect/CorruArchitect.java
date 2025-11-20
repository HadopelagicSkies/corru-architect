package com.corru_architect;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorruArchitect implements ModInitializer {
	public static final String MOD_ID = "corru_architect";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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