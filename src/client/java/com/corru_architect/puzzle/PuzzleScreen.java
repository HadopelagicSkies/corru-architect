package com.corru_architect.puzzle;

import com.corru_architect.CorruArchitect;
import com.corru_architect.screenhandlers.PuzzleScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class PuzzleScreen extends HandledScreen<PuzzleScreenHandler> {

    private static final Identifier BACKGROUND_FRAME = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/archive_screen.png");
    private static final Identifier TEST_BACKGROUND_FILL = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/test_screen_fill.png");
    private static final Identifier PUZZLE_PRESSED = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/puzzle_pressed.png");
    private static final Identifier PUZZLE_UNPRESSED = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/puzzle_unpressed.png");
    private static final Identifier PUZZLE_X = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/puzzle_x.png");
    private static final Identifier PUZZLE_BACKGROUND = Identifier.of(CorruArchitect.MOD_ID, "assets/corru_architect/textures/gui/puzzle_background.png");

    private static final int fillAreaSize = 253;
    private static final int puzzleButtonSize =9;

    private String activePuzzle = "";
    private Integer[][] activePuzzleDetails;
    private PuzzleCellButton[][] puzzleCells;

    public PuzzleScreen(PuzzleScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    public void drawPuzzle(DrawContext context, int i, int j){
        context.drawTexture(RenderLayer::getGuiTextured,PUZZLE_BACKGROUND,8 + i + ((fillAreaSize-150)/2) ,8 + j + ((fillAreaSize-150)/2) , 0, 0, 150, 150, 150, 150);
        //drawing tiles
        for (int r = 0; r < puzzleCells.length; r++) {
            for (int c = 0; c < puzzleCells[0].length; c++) {
                if(puzzleCells[r][c].state ==0){
                    context.drawTexture(RenderLayer::getGuiTextured,PUZZLE_UNPRESSED, puzzleCells[r][c].getX(), puzzleCells[r][c].getY(), 0, 0, 10, 10, 10, 10);
                } else if(puzzleCells[r][c].state ==1){
                    context.drawTexture(RenderLayer::getGuiTextured,PUZZLE_PRESSED, puzzleCells[r][c].getX(), puzzleCells[r][c].getY(), 0, 0, 10, 10, 10, 10);
                } else {
                    context.drawTexture(RenderLayer::getGuiTextured,PUZZLE_X, puzzleCells[r][c].getX(), puzzleCells[r][c].getY(), 0, 0, 10, 10, 10, 10);
                }
            }
        }
        //drawing row numbers
        int currentNum =0;
        int currentNumStack=0;
        for (int r = activePuzzleDetails.length -1; r >= 0; r--) {
            for (int c = activePuzzleDetails[0].length -1; c >= 0; c--) {
                if(activePuzzleDetails[r][c] == 1){
                    currentNum++;
                }
                if ((currentNum > 0 && activePuzzleDetails[r][c] == 0)){
                    currentNumStack++;
                    context.drawText(textRenderer, String.valueOf(currentNum),puzzleCells[r][0].getX()-8*currentNumStack,puzzleCells[r][0].getY()+1, Colors.WHITE,true);
                    currentNum =0;
                }
            }
            currentNumStack++;
            if (currentNum > 0)
                context.drawText(textRenderer, String.valueOf(currentNum),puzzleCells[r][0].getX()-8*currentNumStack,puzzleCells[r][0].getY()+1, Colors.WHITE,true);
            currentNumStack=0;
            currentNum=0;
        }
        //drawing column numbers
        for (int c = activePuzzleDetails[0].length -1; c >= 0; c--) {
            for (int r = activePuzzleDetails.length -1; r >= 0; r--) {
                if(activePuzzleDetails[r][c] == 1){
                    currentNum++;
                }
                if ((currentNum > 0 && activePuzzleDetails[r][c] == 0)){
                    currentNumStack++;
                    context.drawText(textRenderer, String.valueOf(currentNum),puzzleCells[0][c].getX()+2,puzzleCells[0][c].getY()-1-9*currentNumStack, Colors.WHITE,true);
                    currentNum =0;
                }
            }
            currentNumStack++;
            if (currentNum > 0)
                context.drawText(textRenderer, String.valueOf(currentNum),puzzleCells[0][c].getX()+2,puzzleCells[0][c].getY()-1-9*currentNumStack, Colors.WHITE,true);
            currentNumStack=0;
            currentNum=0;
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
    }

    @Environment(EnvType.CLIENT)
    public static class PuzzleCellButton extends ButtonWidget {
        final int index;
        private int state;

        public PuzzleCellButton(final int x, final int y, final int index, final PressAction onPress) {
            super(x, y, puzzleButtonSize, puzzleButtonSize, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.state = 0;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
        public int getState() {
            return this.state;
        }
        public void advanceState() {
            this.state++;
            if(this.state >= 3){
                this.state =0;
            }
        }
    }
}
