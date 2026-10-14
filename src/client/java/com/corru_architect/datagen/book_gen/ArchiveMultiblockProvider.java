package com.corru_architect.datagen.book_gen;

import com.klikli_dev.modonomicon.Modonomicon;
import com.klikli_dev.modonomicon.api.datagen.MultiblockProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.tag.BlockTags;

public class ArchiveMultiblockProvider extends MultiblockProvider {
    public ArchiveMultiblockProvider(DataOutput packOutput, String modid) {
        super(packOutput, modid);
    }

    public void buildMultiblocks() {
        this.add(this.modLoc("demo_tag"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{" WWW ", "N G S", "NG0GS", "N G S", " EEE "}).blockstate('N', () -> Blocks.OAK_STAIRS, "[facing=north]").blockstate('S', () -> Blocks.OAK_STAIRS, "[facing=south]").blockstate('W', () -> Blocks.OAK_STAIRS, "[facing=west]").blockstate('E', () -> Blocks.OAK_STAIRS, "[facing=east]").tag('G', BlockTags.CANDLES, () -> Blocks.WHITE_CANDLE).block('0', () -> Blocks.SKELETON_SKULL));
        this.add(this.modLoc("demo_block_entity"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{" WWW ", "NFXFS", "NF0FS", "NFKFS", " EEE "}).blockstate('N', () -> Blocks.OAK_STAIRS, "[facing=north]").blockstate('S', () -> Blocks.OAK_STAIRS, "[facing=south]").blockstate('W', () -> Blocks.OAK_STAIRS, "[facing=west]").blockstate('E', () -> Blocks.OAK_STAIRS, "[facing=east]").block('F', () -> Blocks.FURNACE).blockstate('X', () -> Blocks.SKELETON_SKULL, "[rotation=2]").blockstate('K', () -> Blocks.SKELETON_SKULL, ""));
        this.add(this.modLoc("demo_dense"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{" WWW ", "N   S", "N 0 S", "N   S", " EEE "}).blockstate('N', () -> Blocks.OAK_STAIRS, "[facing=north]").blockstate('S', () -> Blocks.OAK_STAIRS, "[facing=south]").blockstate('W', () -> Blocks.OAK_STAIRS, "[facing=west]").blockstate('E', () -> Blocks.OAK_STAIRS, "[facing=east]"));
        this.add(this.modLoc("demo_predicate"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{" ttt ", "t a t", "ta0as", "s a s", " sss "}).block('0', () -> Blocks.DIRT).predicate('t', Modonomicon.loc("non_solid"), false, () -> Blocks.TORCH).predicate('s', Modonomicon.loc("non_solid"), true, () -> Blocks.SHORT_GRASS).predicate('a', Modonomicon.loc("non_solid"), true, () -> Blocks.AIR));
        this.add(this.modLoc("test_non_square"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{"       s   ", "           ", "ce  0     r", "           ", "       s   "}).block('c', () -> Blocks.BAMBOO).block('e', () -> Blocks.GRANITE).block('s', () -> Blocks.SAND).block('r', () -> Blocks.ACACIA_WOOD).block('0', () -> Blocks.BELL).build());
        this.add(this.modLoc("demo_fluid"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{" LLL ", "W   W", "W 0 W", "W   W", " LLL "}).block('L', () -> Blocks.LAVA).block('W', () -> Blocks.WATER));
        this.add(this.modLoc("demo_transparency"), (new MultiblockProvider.DenseMultiblockBuilder()).layer(new String[]{"G F", " 0 ", "I C"}).block('G', () -> Blocks.GLASS_PANE).block('F', () -> Blocks.GRAY_STAINED_GLASS_PANE).block('I', () -> Blocks.IRON_BARS).block('C', () -> Blocks.CHAIN));
    }
}
