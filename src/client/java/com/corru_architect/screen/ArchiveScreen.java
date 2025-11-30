package com.corru_architect.screen;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectBlocks;
import com.corru_architect.RecipeUnlockMapping;
import com.corru_architect.packet_payloads.GrantAdvancementPayload;
import com.corru_architect.screenhandlers.ArchiveScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class ArchiveScreen extends HandledScreen<ArchiveScreenHandler> {

    private static final Identifier BACKGROUND_FRAME = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/archive_screen.png");
    private static final Identifier TEST_BACKGROUND_FILL = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/test_screen_fill.png");
    private static final Identifier PUZZLE_PRESSED = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/puzzle_pressed.png");
    private static final Identifier PUZZLE_UNPRESSED = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/puzzle_unpressed.png");
    private static final Identifier PUZZLE_X = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/puzzle_x.png");
    private static final Identifier PUZZLE_BACKGROUND = Identifier.of(CorruArchitect.MOD_ID, "textures/gui/puzzle_background.png");

    private static final int fillAreaSize = 253;
    private static final int backgroundFillSize = 760;
    private static final int puzzleButtonSize =9;


    private double mouseOldX = -1;
    private double mouseOldY = -1;

    private int backgroundFillOffsetX = 0;
    private int backgroundFillOffsetY = 0;


    private String activePuzzle = "";
    private Integer[][] activePuzzleDetails;
    private PuzzleCellButton[][] puzzleCells;
    private ArchiveLogButton[] archiveLogs = new ArchiveLogButton[64];


    public ArchiveScreen(ArchiveScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = 285;
        this.titleY = 7;
        this.playerInventoryTitleX = 10000;
        this.playerInventoryTitleY = 10000;
        this.backgroundWidth = 400;
        this.backgroundHeight = 270;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderLayer::getGuiTextured, TEST_BACKGROUND_FILL, x + 8, y + 8, backgroundFillOffsetX, backgroundFillOffsetY, fillAreaSize, fillAreaSize, backgroundFillSize, backgroundFillSize);
        context.drawTexture(RenderLayer::getGuiTextured, BACKGROUND_FRAME, x, y, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        super.render(context, mouseX, mouseY, delta);

        if(!activePuzzle.isEmpty())
            drawPuzzle(context, i, j);
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
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        if (mouseOldX == -1 || mouseOldY == -1) {
            mouseOldX = mouseX;
            mouseOldY = mouseY;
        } else if (activePuzzle.isEmpty()) {
            double moveDeltaX = mouseX - mouseOldX;
            double moveDeltaY = mouseY - mouseOldY;

            if (isPointWithinBounds(8,8,fillAreaSize,fillAreaSize,mouseX,mouseY)) {
                backgroundFillOffsetX -= Math.min(moveDeltaX,5);
                backgroundFillOffsetY -= Math.min(moveDeltaY,5);

                if (backgroundFillOffsetX < 0) {
                    backgroundFillOffsetX = 0;
                } else if (backgroundFillOffsetX + fillAreaSize > backgroundFillSize) {
                    backgroundFillOffsetX = backgroundFillSize - fillAreaSize;
                }


                if (backgroundFillOffsetY < 0) {
                    backgroundFillOffsetY = 0;
                } else if (backgroundFillOffsetY + fillAreaSize > backgroundFillSize) {
                    backgroundFillOffsetY = backgroundFillSize - fillAreaSize;
                }
                clearAndInit();
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        mouseOldX = -1;
        mouseOldY = -1;

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected void init() {
        super.init();

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        archiveLogs[0] = this.addDrawableChild(new ArchiveLogButton(x + 8 + 20 + backgroundFillOffsetX, y + 8 + 20 + backgroundFillOffsetY, 0, (button) -> {
            if (button instanceof ArchiveLogButton) {
                activePuzzle = ((ArchiveLogButton) button).puzzleName;
                activePuzzleDetails = this.handler.getPuzzleDetails(((ArchiveLogButton) button).puzzleName);
                int rows = activePuzzleDetails.length;
                int columns = activePuzzleDetails[0].length;
                puzzleCells = new PuzzleCellButton[rows][columns];
                clearAndInit();
            }
        }, RecipeUnlockMapping.getUnlockName(CorruArchitectBlocks.CYSTIC_COLUMN)));

        if (!activePuzzle.isEmpty()) {

            for( ArchiveLogButton button: archiveLogs){
                if (button!=null)
                    button.visible = false;
            }

            int puzzleOffsetX = 8+((fillAreaSize- puzzleCells[0].length * puzzleButtonSize)/2);
            int puzzleOffsetY = 8+((fillAreaSize - puzzleCells.length * puzzleButtonSize)/2);


            int buttonIndex = archiveLogs.length + 1;
            for (int r = 0; r < puzzleCells.length; r++) {
                for (int c = 0; c < puzzleCells[0].length; c++) {
                    puzzleCells[r][c] = this.addDrawableChild(new PuzzleCellButton(x + puzzleOffsetX + c*puzzleButtonSize, y + puzzleOffsetY + r*puzzleButtonSize, buttonIndex, (button) -> {
                        if (button instanceof PuzzleCellButton) {
                            ((PuzzleCellButton) button).advanceState();
                            if(this.handler.checkPuzzle(activePuzzle, puzzleOutput(puzzleCells))) {
                                CorruArchitect.LOGGER.info("puzzle correct");
                                ClientPlayNetworking.send(new GrantAdvancementPayload(activePuzzle));
                            }
                        }
                    }));
                    buttonIndex++;
                }
            }
        } else {
            for (ArchiveLogButton button : archiveLogs) {
                if (button!=null && isPointWithinBounds(8,8,fillAreaSize-puzzleButtonSize,fillAreaSize-puzzleButtonSize,button.getX(),button.getY()))
                    button.visible = true;
                else if (button!=null)
                    button.visible = false;
            }
        }
    }

    private int[][] puzzleOutput(PuzzleCellButton[][] puzzleCells) {
        int rows = puzzleCells.length;
        int columns = puzzleCells[0].length;
        int[][] newOutput = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newOutput[i][j] = puzzleCells[i][j].getState();
            }
        }
        return newOutput;
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

    public static class ArchiveLogButton extends ButtonWidget {
        final int index;
        final String puzzleName;
        public ArchiveLogButton(final int x, final int y, final int index, final PressAction onPress, String puzzleName) {
            super(x, y, 20, 20, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
            this.puzzleName = puzzleName;
        }

    }




}
