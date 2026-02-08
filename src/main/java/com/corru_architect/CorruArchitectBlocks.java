package com.corru_architect;

import com.corru_architect.blocks.ArchiveCorrucystBlock;
import com.corru_architect.blocks.GroundsmindryCore.GroundsmindryCoreBlock;
import com.corru_architect.blocks.SimpleTooltipBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;


public class CorruArchitectBlocks {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CorruArchitectItems.CORRU_ARCHITECT_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(CorruArchitectBlocks.WILD_CORRU.asItem());
            itemGroup.add(CorruArchitectBlocks.ARCHIVE_CORRUCYST.asItem());
            itemGroup.add(CorruArchitectBlocks.CYSTIC_COLUMN.asItem());
            itemGroup.add(CorruArchitectBlocks.CORRU_SCULPTOR.asItem());
            itemGroup.add(CorruArchitectBlocks.SFER_REFINERY.asItem());
            itemGroup.add(CorruArchitectBlocks.SFER_BLOCK.asItem());
            itemGroup.add(CorruArchitectBlocks.GROUNDSMINDRY_CORE.asItem());
        });

        WILD_CORRU.setTooltipDetails("wawawa", Formatting.LIGHT_PURPLE);

        RecipeUnlockMapping.putAdvancementName(CYSTIC_COLUMN,"column_unlock");
    }

    public static <T extends Block> T register(Function<Block.Settings, T> constructor, Block.Settings blockSettings, String name, Rarity rarity) {
        Identifier id = Identifier.of(CorruArchitect.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);
        T block = constructor.apply(settings);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey)
                .rarity(rarity);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static <T extends Block> T register(Function<Block.Settings, T> constructor, Block.Settings blockSettings, String name) {
        return register(constructor,blockSettings,name, Rarity.COMMON);
    }

    private static Block register(Block.Settings blockSettings, String name) {
        return register(Block::new,blockSettings,name);
    }

    public static SimpleTooltipBlock WILD_CORRU = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE),
            "wild_corru"
    );

    public static ArchiveCorrucystBlock ARCHIVE_CORRUCYST = register(ArchiveCorrucystBlock::new,
            AbstractBlock.Settings.create(),
            "archive_corrucyst",
            Rarity.EPIC
    );

    public static SimpleTooltipBlock CYSTIC_COLUMN = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "cystic_column"
    );

    public static SimpleTooltipBlock CORRU_SCULPTOR = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "corru_sculptor"
    );

    public static SimpleTooltipBlock SFER_REFINERY = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "sfer_refinery"
    );

    public static SimpleTooltipBlock SFER_BLOCK = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "sfer_block"
    );

    public static SimpleTooltipBlock BASIC_ECHO_TERMINAL = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "basic_echo_terminal"
    );

    public static SimpleTooltipBlock ADVANCED_ECHO_TERMINAL = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "advanced_echo_terminal"
    );

    public static SimpleTooltipBlock BASIC_GOLEM_FOUNDRY = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "basic_golem_foundry"
    );

    public static SimpleTooltipBlock ADVANCED_GOLEM_FOUNDRY = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "advanced_golem_foundry"
    );

    public static SimpleTooltipBlock GROUNDSMINDRY_CORE = register(GroundsmindryCoreBlock::new,
            AbstractBlock.Settings.create(),
            "groundsmindry_core",
            Rarity.EPIC
    );

    public static SimpleTooltipBlock PALE_CORRU = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "pale_corru"
    );

    public static SimpleTooltipBlock BLACK_CORRU = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "black_corru"
    );

    public static SimpleTooltipBlock MOVEFRIEND = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.create(),
            "movefriend"
    );


}