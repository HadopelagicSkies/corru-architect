package com.corru_architect.blocks.GroundsmindryCore;

import java.util.ArrayList;
import java.util.List;

public class SpireSegment{
    public final int layer;
    public final String name;
    public List<SpireBranch> spireBranches = new ArrayList<>();
    public SpireSegment(int layer,String name){
        this.layer = layer;
        this.name = name;
    }

    public List<SpireBranch> getSpireBranches() {
        return spireBranches;
    }

    public void setSpireBranches(List<SpireBranch> spireBranches) {
        this.spireBranches = spireBranches;
    }

}
