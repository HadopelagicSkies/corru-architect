package com.corru_architect.screenhandlers;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectBlocks;
import com.corru_architect.RecipeUnlockMapping;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

import java.util.Map;

public class ArchiveScreenHandler extends ScreenHandler {

    private static final Integer[][] columnPuzzle = {
            {1,1,0,1,1},
            {0,1,1,1,0},
            {0,0,1,0,0},
            {0,1,1,1,0},
            {1,1,1,1,1}};

    private Map<String,Integer[][]> puzzleMap = Map.of(
            RecipeUnlockMapping.getUnlockName(CorruArchitectBlocks.CYSTIC_COLUMN), columnPuzzle);

    private PlayerEntity player = null;

    public ArchiveScreenHandler(int syncId, PlayerInventory inventory) {
        super(CorruArchitect.ARCHIVE_SCREEN_HANDLER, syncId);
        player = inventory.player;
    }

    public Integer[][] getPuzzleDetails(String puzzleName){
        return puzzleMap.get(puzzleName);
    }

    public boolean checkPuzzle(String puzzleName, int[][] puzzleProgress){
        Integer[][] puzzleAnswer = puzzleMap.get(puzzleName);
        for (int r = 0; r < puzzleProgress.length; r++) {
            for (int c = 0; c < puzzleProgress[0].length; c++) {
                if(puzzleAnswer[r][c] == 1 && puzzleProgress[r][c] !=1){
                    return false;
                }
                else if(puzzleAnswer[r][c] == 0 && puzzleProgress[r][c] == 1){
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return super.onButtonClick(player, id);
    }
}
