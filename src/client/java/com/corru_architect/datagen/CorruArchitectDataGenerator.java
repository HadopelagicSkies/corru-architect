package com.corru_architect.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CorruArchitectDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(CorruArchitectModRecipeProvider::new);
		pack.addProvider(CorruArchitectModBlockLootTableProvider::new);
		pack.addProvider(CorruArchitectModModelProvider::new);
		pack.addProvider(CorruArchitectModBlockTagProvider::new);
		pack.addProvider(CorruArchitectModAdvancementProvider::new);
	}
}
