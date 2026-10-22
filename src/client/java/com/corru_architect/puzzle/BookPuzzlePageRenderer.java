package com.corru_architect.puzzle;

import com.corru_architect.book.BookPuzzlePage;
import com.klikli_dev.modonomicon.book.page.BookPage;
import com.klikli_dev.modonomicon.client.render.page.BookPageRenderer;
import com.klikli_dev.modonomicon.client.render.page.PageRendererFactory;
import net.minecraft.client.gui.DrawContext;

public class BookPuzzlePageRenderer extends BookPageRenderer<BookPuzzlePage> {
    public BookPuzzlePageRenderer(BookPuzzlePage page) {
        super(page);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float ticks) {

    }

    public static class BookPuzzlePageRendererFactory implements PageRendererFactory{
        @Override
        public BookPageRenderer<?> create(BookPage bookPage) {
            return new BookPuzzlePageRenderer((BookPuzzlePage) bookPage);
        }
    }
}
