package com.corru_architect.blocks.GroundsmindryCore;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.util.List;

public class SpireBranch{
    public Direction direction;
    public int xCoord;
    public int yCoord;
    public boolean growing;
    public BranchType type;
    public SpireBranch(int xCoord, int yCoord,  Direction direction, BranchType type){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.direction = direction;
        this.type = type;
        this.growing = false;
    }

    public List<Box> getBranchBoxes(){
        return type.getBoxList();
    }

}