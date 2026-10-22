package com.corru_architect.datagen.book_gen;

import com.corru_architect.CorruArchitect;
import com.klikli_dev.modonomicon.api.datagen.AddToBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.datagen.book.addtodemo.AddToGettingStartedCategory;
import com.klikli_dev.modonomicon.datagen.book.addtodemo.NewCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class AddToArchiveBook extends AddToBookSubProvider {
    public static final Identifier TARGET_BOOK_ID = Identifier.of(CorruArchitect.MOD_ID,CorruArchitect.BOOK_ID);

    public AddToArchiveBook(ModonomiconLanguageProvider lang) {
        super(TARGET_BOOK_ID, lang);
    }

    protected void registerDefaultMacros() {
    }

    protected void generateCategories() {
        this.book.withDontGenerateJson(false);
        this.add((new AddToGettingStartedCategory(this)).generate());
        this.currentSortIndex(50);
        this.add((new NewCategory(this)).generate());

    }

    @Override
    public void generate(BiConsumer<Identifier, BookModel> consumer, RegistryWrapper.WrapperLookup registries) {
        super.generate(consumer, registries);
    }
}
