package com.corru_architect.datagen.book_gen;

import com.corru_architect.CorruArchitect;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookCommandModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.datagen.book.demo.ConditionalCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.FeaturesCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.FormattingCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.IndexModeCategory;
import net.minecraft.util.Identifier;

public class ArchiveBook extends SingleBookSubProvider {
    public static final String ID = CorruArchitect.BOOK_ID;

    public ArchiveBook(String modid, ModonomiconLanguageProvider lang) {
        super(ID, modid, lang);
    }



    protected BookModel additionalSetup(BookModel book) {
        BookCommandModel commandEntryCommand = BookCommandModel.create(this.modLoc("test_command"), "/give @s minecraft:apple 1").withPermissionLevel(2).withSuccessMessage("modonomicon.command.test_command.success");
        this.add(commandEntryCommand.getSuccessMessage(), "You got an apple, because reading is cool!");
        BookCommandModel commandEntryLinkCommand = BookCommandModel.create(this.modLoc("test_command2"), "/give @s minecraft:wheat 1").withPermissionLevel(2).withSuccessMessage("modonomicon.command.test_command2.success");
        this.add(commandEntryLinkCommand.getSuccessMessage(), "You got wheat, because clicking is cool!");
        return book.withModel(Identifier.of("corru_architect:portable_archive")).withBookTextOffsetX(5).withBookTextOffsetY(0).withBookTextOffsetWidth(-5).withCommand(commandEntryCommand).withCommand(commandEntryLinkCommand).withAllowOpenBooksWithInvalidLinks(true);
    }

    protected void registerDefaultMacros() {
    }

    protected void generateCategories() {
        this.add((new FeaturesCategory(this)).generate());
        this.add((new FormattingCategory(this)).generate());
        BookCategoryModel conditionalCategory = this.add((new ConditionalCategory(this)).generate()).withCondition(this.condition().entryRead(this.modLoc(new String[]{"features", "condition_root"})));
        this.add((new IndexModeCategory(this)).generate());
    }

    protected String bookName() {
        return "Archive Corrucyst";
    }

    protected String bookTooltip() {
        return "A book to showcase & test Modonomicon features.";
    }
}
