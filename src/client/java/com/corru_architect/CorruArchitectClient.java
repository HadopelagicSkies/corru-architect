package com.corru_architect;

import com.corru_architect.screen.ArchiveScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CorruArchitectClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(CorruArchitect.ARCHIVE_SCREEN_HANDLER, ArchiveScreen::new);
	}
}