package com.corru_architect.datagen;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectItems;
import com.klikli_dev.modonomicon.api.datagen.AbstractModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.minecraft.data.DataOutput;

public class CorruArchitectEnUsProvider extends AbstractModonomiconLanguageProvider {
    public CorruArchitectEnUsProvider(DataOutput packOutput, ModonomiconLanguageProvider cachedProvider) {
        super(packOutput, CorruArchitect.MOD_ID, "en_us", cachedProvider);
    }

    private void addMisc() {
        this.add("itemGroup.corru_architect", "Corru.Architect");

    }

    private void addItems() {
        this.addItem(() -> CorruArchitectItems.MINDSPIKE, "Mindspike");
    }

    private void addConfigurationTranslations() {
        this.addConfig("qol", "Quality of Life Settings");
        this.addConfig("enableSmoothZoom", "Enable Smooth Zoom");
        this.addConfig("storeLastOpenPageWhenClosingEntry", "Store Last Open Page When Closing Entry");
        this.addConfig("fontFallbackLocales", "Font Fallback Locales");
    }

    private void addConfig(String key, String name) {
        this.add("corru_architect.configuration." + key, name);
    }

    protected void addTranslations() {
        this.addMisc();
        this.addItems();
        this.addConfigurationTranslations();
    }
}
