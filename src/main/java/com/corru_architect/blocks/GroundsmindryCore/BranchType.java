package com.corru_architect.blocks.GroundsmindryCore;

import com.corru_architect.CorruArchitect;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.Box;


import java.util.List;
import java.util.function.IntFunction;

public enum BranchType implements StringIdentifiable {
    //I for inside, D for door, d for door-able, E for empty, X for overwrite wall for door adapter
    HALLWAY_SHORT("hallway_short", new char[][]{
            {'D', 'I', 'D'},
            {'D', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 3))),
    HALLWAY_MED("hallway_med", new char[][]{
            {'D', 'I', 'd', 'I', 'D'},
            {'D', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 5))),
    HALLWAY_LONG("hallway_long", new char[][]{
            {'D', 'I', 'd', 'I', 'd', 'I', 'D'},
            {'D', 'I', 'd', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 7))),
    HALLWAY_TURN("hallway_turn", new char[][]{
            {'D', 'I', 'I'},
            {'D', 'I', 'I'},
            {'E', 'D', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 3),
                    new Box(3, 0, 2, 3, 0, 3))),
    HALLWAY_T("hallway_t", new char[][]{
            {'D', 'I', 'I', 'D'},
            {'D', 'I', 'I', 'D'},
            {'E', 'D', 'D', 'E'}},
            List.of(new Box(0, 0, 0, 2, 0, 4),
                    new Box(3, 0, 2, 3, 0, 3))),


    HALLWAY_THIN_SHORT("hallway_thin_short", new char[][]{
            {'D', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 1, 0, 3))),
    HALLWAY_THIN_MED("hallway_thin_med", new char[][]{
            {'D', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 1, 0, 5))),
    HALLWAY_THIN_LONG("hallway_thin_long", new char[][]{
            {'D', 'I', 'd', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 1, 0, 7))),
    HALLWAY_THIN_TURN("hallway_thin_turn", new char[][]{
            {'D', 'I'},
            {'E', 'D'}},
            List.of(new Box(0, 0, 0, 1, 0, 2),
                    new Box(2, 0, 2, 2, 0, 2))),
    HALLWAY_THIN_T("hallway_thin_t", new char[][]{
            {'D', 'I', 'D'},
            {'E', 'D', 'E'}},
            List.of(new Box(0, 0, 0, 1, 0, 3),
                    new Box(2, 0, 2, 2, 0, 2))),


    HALLWAY_THICK_SHORT("hallway_thick_short", new char[][]{
            {'D', 'I', 'D'},
            {'D', 'I', 'D'},
            {'D', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 3, 0, 3))),
    HALLWAY_THICK_MED("hallway_thick_med", new char[][]{
            {'D', 'I', 'd', 'I', 'D'},
            {'D', 'I', 'I', 'I', 'D'},
            {'D', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 3, 0, 5))),
    HALLWAY_THICK_LONG("hallway_thick_long", new char[][]{
            {'D', 'I', 'd', 'I', 'd', 'I', 'D'},
            {'D', 'I', 'I', 'I', 'I', 'I', 'D'},
            {'D', 'I', 'd', 'I', 'd', 'I', 'D'}},
            List.of(new Box(0, 0, 0, 3, 0, 7))),
    HALLWAY_THICK_TURN("hallway_thick_turn", new char[][]{
            {'D', 'I', 'I', 'I'},
            {'D', 'I', 'I', 'I'},
            {'D', 'I', 'I', 'I'},
            {'E', 'D', 'D', 'D'}},
            List.of(new Box(0, 0, 0, 3, 0, 4),
                    new Box(4, 0, 2, 4, 0, 4))),
    HALLWAY_THICK_T("hallway_thick_t", new char[][]{
            {'D', 'I', 'I', 'I', 'D'},
            {'D', 'I', 'I', 'I', 'D'},
            {'D', 'I', 'I', 'I', 'D'},
            {'E', 'D', 'D', 'D', 'E'}},
            List.of(new Box(0, 0, 0, 3, 0, 5),
                    new Box(4, 0, 2, 4, 0, 4))),


    SMALL_MED_ADAPTER("small_med_adapter", new char[][]{
            {'D', 'D'},
            {'X', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 2))),
    MED_LARGE_ADAPTER("med_large_adapter", new char[][]{
            {'D', 'D', 'D'},
            {'X', 'D', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 3))),
    SMALL_LARGE_ADAPTER_1("small_large_adapter_1", new char[][]{
            {'D', 'D', 'D'},
            {'E', 'D', 'E'}},
            List.of(new Box(0, 0, 0, 2, 0, 3))),
    SMALL_LARGE_ADAPTER_2("small_large_adapter_2", new char[][]{
            {'D', 'D', 'D'},
            {'D', 'X', 'D'}},
            List.of(new Box(0, 0, 0, 2, 0, 2))),

    GROUNDSMIND_SMALL("groundsmind_small", new char[][]{
            {'E', 'E', 'E'},
            {'E', 'I', 'E'},
            {'E', 'D', 'E'}},
            List.of(new Box(2, 0, 2, 3, 0, 2))),


    GROUNDSMIND_MED("groundsmind_med", new char[][]{
            {'I', 'd', 'I'},
            {'d', 'I', 'd'},
            {'I', 'D', 'I'}},
            List.of(new Box(0, 0, 0, 3, 0, 3))),

    GROUNDSMIND_LARGE("groundsmind_large", new char[][]{
            {'E', 'I', 'd', 'I', 'E'},
            {'I', 'I', 'I', 'I', 'I'},
            {'d', 'I', 'I', 'I', 'd'},
            {'I', 'I', 'I', 'I', 'I'},
            {'E', 'D', 'D', 'D', 'E'}},
            List.of(new Box(0, 0, 0, 5, 0, 5)));


    private static final int blocksPerCell=3;
    private static final int roomHeight=5+2; //+2 for ceiling and floor
    private final String name;
    private final Text translatableName;
    private final List<Box> boxList;
    private final char[][] tiles;

    BranchType(String name, char[][] tiles, List<Box> boxList) {
        this.name = name;
        this.translatableName = Text.translatable(CorruArchitect.MOD_ID + "." + name);
        this.tiles = tiles;
        boxList.forEach((box -> box.stretch(blocksPerCell,0,blocksPerCell)));
        boxList.forEach((box -> box.expand(0,roomHeight,0)));
        this.boxList = boxList;
    }

    public char[][] getTiles() {
        return tiles;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public Text getTranslatableName() {
        return translatableName;
    }

    @Override
    public String asString() {
        return name;
    }

    public static final IntFunction<BranchType> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(BranchType::ordinal, values(), ValueLists.OutOfBoundsHandling.ZERO);
    public static final PacketCodec<ByteBuf, BranchType> BRANCH_TYPE_PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, BranchType::ordinal);
}

