package com.corru_architect;


import com.corru_architect.items.SimpleTooltipItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;


public class CorruArchitectItems {

    public static final RegistryKey<ItemGroup> CORRU_ARCHITECT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(CorruArchitect.MOD_ID, "item_group"));
    public static final ItemGroup CORRU_ARCHITECT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CorruArchitectItems.WILD_CORRU_CHUNK))
            .displayName(Text.translatable("itemGroup.corru_architect"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CORRU_ARCHITECT_GROUP_KEY, CORRU_ARCHITECT_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CORRU_ARCHITECT_GROUP_KEY)
                .register((itemGroup) -> {
                    itemGroup.add(CorruArchitectItems.WILD_CORRU_CHUNK);
                });

        WILD_CORRU_CHUNK.setTooltipDetails("wawawa", Formatting.LIGHT_PURPLE);
    }

    private static <T extends Item> T register(Function<Item.Settings, T> constructor, Item.Settings itemSettings, String name) {
        Identifier id = Identifier.of(CorruArchitect.MOD_ID, name);

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        return Registry.register(Registries.ITEM, key, constructor.apply(settings));
    }

    private static Item register(Item.Settings itemSettings, String name) {
        return register(Item::new,itemSettings,name);
    }

    public static SimpleTooltipItem WILD_CORRU_CHUNK = register(SimpleTooltipItem::new,
            new Item.Settings().rarity(Rarity.EPIC),
            "wild_corru_chunk"
    );

}