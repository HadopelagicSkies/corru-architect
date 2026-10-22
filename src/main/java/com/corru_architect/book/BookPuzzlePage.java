package com.corru_architect.book;

import com.corru_architect.CorruArchitect;
import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.conditions.BookCondition;
import com.klikli_dev.modonomicon.book.page.BookPage;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BookPuzzlePage extends BookPage {
    private BookTextHolder title;

    public BookPuzzlePage(String anchor, BookCondition condition) {
        super(anchor, condition);
    }

    @Override
    public Identifier getType() {
        return Identifier.of(CorruArchitect.MOD_ID,"puzzle_page");
    }

    @Override
    public boolean matchesQuery(String query, World world) {
        return this.title.getString().toLowerCase().contains(query);
    }
}
