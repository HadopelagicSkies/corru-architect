package com.corru_architect;


import com.corru_architect.items.SimpleTooltipItem;
import com.klikli_dev.modonomicon.Modonomicon;
import com.klikli_dev.modonomicon.registry.DataComponentRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import com.klikli_dev.modonomicon.item.ModonomiconItem;

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
                    itemGroup.add(CorruArchitectItems.PORTABLE_ARCHIVE);
                    itemGroup.add(CorruArchitectItems.WILD_CORRU_CHUNK);
                    itemGroup.add(CorruArchitectItems.MINDSPIKE);
                    itemGroup.add(CorruArchitectItems.MINDSPIKE_HELMET);
                    itemGroup.add(CorruArchitectItems.ECHO_CORRUCYST);
                    itemGroup.add(CorruArchitectItems.SFER_CUBE);
                });
        WILD_CORRU_CHUNK.setTooltipDetails(Formatting.LIGHT_PURPLE,"1234567890","lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "," ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."," duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."," excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        SFER_CUBE.setTooltipDetails(Formatting.LIGHT_PURPLE,"LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISCING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. "," UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT."," DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."," EXCEPTEUR SINT OCCAECAT CUPIDATAT NON PROIDENT, SUNT IN CULPA QUI OFFICIA DESERUNT MOLLIT ANIM ID EST LABORUM.");
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

    public static ModonomiconItem PORTABLE_ARCHIVE = register(ModonomiconItem::new,
            new Item.Settings()
                    .component(DataComponentRegistry.BOOK_ID.get(), Identifier.of(CorruArchitect.MOD_ID,CorruArchitect.BOOK_ID))
                    .rarity(Rarity.EPIC),
            "portable_archive"
    );

    public static SimpleTooltipItem WILD_CORRU_CHUNK = register(SimpleTooltipItem::new,
            new Item.Settings().rarity(Rarity.EPIC),
            "wild_corru_chunk"
    );

    public static SimpleTooltipItem MINDSPIKE = register(SimpleTooltipItem::new,
            new Item.Settings().maxCount(1),
            "mindspike"
    );

    public static SimpleTooltipItem SFER_CUBE = register(SimpleTooltipItem::new,
            new Item.Settings(),
            "sfer_cube"
    );

    public static SimpleTooltipItem ECHO_CORRUCYST = register(SimpleTooltipItem::new,
            new Item.Settings(),
            "echo_corrucyst"
    );

    public static SimpleTooltipItem MINDSPIKE_HELMET = register(SimpleTooltipItem::new,
            ArmorMaterials.IRON.applySettings(new Item.Settings(), EquipmentType.HELMET),
            "mindspike_helmet"
    );

}