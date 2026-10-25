package com.corru_architect.datagen;

import com.corru_architect.CorruArchitect;
import com.corru_architect.datagen.book_gen.AddToArchiveBook;
import com.corru_architect.datagen.book_gen.ArchiveBook;
import com.corru_architect.datagen.book_gen.ArchiveMultiblockProvider;
import com.klikli_dev.modonomicon.api.datagen.*;
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

		LanguageProviderCache enUsCache = new LanguageProviderCache("en_us");
		LanguageProviderCache addToLangCache = new LanguageProviderCache("en_us");
		pack.addProvider(FabricBookProvider.of(new ArchiveBook(CorruArchitect.MOD_ID, enUsCache), new AddToArchiveBook(addToLangCache)));
		pack.addProvider((FabricDataGenerator.Pack.Factory<CorruArchitectEnUsProvider>) (output) -> new CorruArchitectEnUsProvider(output, enUsCache));
		pack.addProvider((FabricDataGenerator.Pack.Factory<AddToCorruArchitectLanguageProvider>) (output) -> new AddToCorruArchitectLanguageProvider(output, CorruArchitect.MOD_ID, "en_us", addToLangCache));
		pack.addProvider((FabricDataGenerator.Pack.Factory<ArchiveMultiblockProvider>) (output) -> new ArchiveMultiblockProvider(output, CorruArchitect.MOD_ID));
	}
}
