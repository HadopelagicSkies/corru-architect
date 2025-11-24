package com.corru_architect.screenhandlers;

import com.corru_architect.CorruArchitect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

import java.util.Map;

public class ArchiveScreenHandler extends ScreenHandler {

    private static final Integer[][] pedestalPuzzle = {
            {1,1,0,1,1},
            {0,1,1,1,0},
            {0,0,1,0,0},
            {0,1,1,1,0},
            {1,1,1,1,1}};


    private Map<String,Integer[][]> puzzleMap = Map.of(
            "pedestal",pedestalPuzzle);

    public ArchiveScreenHandler(int syncId, PlayerInventory inventory) {
        super(CorruArchitect.ARCHIVE_SCREEN_HANDLER, syncId);
    }

    public Integer[][] getPuzzleDetails(String puzzleName){
        return puzzleMap.get(puzzleName);
    }

    public boolean checkPuzzle(String puzzleName, int[][] puzzleProgress){
        return false;
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
