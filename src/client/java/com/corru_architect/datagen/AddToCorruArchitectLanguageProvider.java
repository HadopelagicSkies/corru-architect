package com.corru_architect.datagen;

import com.klikli_dev.modonomicon.api.datagen.AbstractModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.minecraft.data.DataOutput;
import org.jetbrains.annotations.NotNull;

public class AddToCorruArchitectLanguageProvider extends AbstractModonomiconLanguageProvider {
    public AddToCorruArchitectLanguageProvider(DataOutput output, String targetModId, String locale, ModonomiconLanguageProvider cachedProvider) {
        super(output, targetModId, locale, cachedProvider);
    }
    public AddToCorruArchitectLanguageProvider(DataOutput output, String targetModId, String locale) {
        super(output, targetModId, locale);
    }
    protected void addTranslations() {
    }

    public @NotNull String getName() {
        return "Languages: " + this.locale + " for " + this.modId;
    }
}
