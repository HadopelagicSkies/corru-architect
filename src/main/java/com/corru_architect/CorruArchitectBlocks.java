package com.corru_architect;

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

import java.util.function.Function;


public class CorruArchitectBlocks {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CorruArchitectItems.CORRU_ARCHITECT_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(CorruArchitectBlocks.WILD_CORRU.asItem());
        });

        WILD_CORRU.setTooltipDetails("wawawa", Formatting.LIGHT_PURPLE);
    }

    public static <T extends Block> T register(Function<Block.Settings, T> constructor, Block.Settings blockSettings, String name) {
        Identifier id = Identifier.of(CorruArchitect.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);
        T block = constructor.apply(settings);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));

        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Block register(Block.Settings blockSettings, String name) {
        return register(Block::new,blockSettings,name);
    }

    public static SimpleTooltipBlock WILD_CORRU = register(SimpleTooltipBlock::new,
            AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE),
            "wild_corru"
    );

}