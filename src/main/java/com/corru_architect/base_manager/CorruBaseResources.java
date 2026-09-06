package com.corru_architect.base_manager;

import com.corru_architect.CorruArchitect;
import com.corru_architect.CorruArchitectItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

public class CorruBaseResources {


    public static final BaseResourceType essentialMetalResource = new BaseResourceType("essentialMetalResource",500);
    public static final BaseResourceType deadMetalResource = new BaseResourceType("deadMetalResource",500);
    public static final BaseResourceType corruResource = new BaseResourceType("corruResource",500);
    public static final BaseResourceType soilResource = new BaseResourceType("soilResource",ItemTags.DIRT,1,500);
    public static final BaseResourceType stoneResource = new BaseResourceType("stoneResource",ItemTags.STONE_CRAFTING_MATERIALS,1,500);
    public static final BaseResourceType spireStabilityResource = new BaseResourceType("stability",0);

    public static void initialize(){
        ServerWorldEvents.LOAD.register((server, serverWorld) -> {
            CorruArchitect.LOGGER.info("Loading Tag Resource Costs");
            BaseResourceType.loadTagValues();
        });
        essentialMetalResource.addResourceItem(Items.COPPER_INGOT,9);
        essentialMetalResource.addResourceItem(Items.COPPER_BLOCK,81);
        essentialMetalResource.addResourceItem(Items.WAXED_COPPER_BLOCK,81);

        essentialMetalResource.addResourceItem(Items.GOLD_NUGGET,1);
        essentialMetalResource.addResourceItem(Items.GOLD_INGOT,9);
        essentialMetalResource.addResourceItem(Items.GOLD_BLOCK,81);

        deadMetalResource.addResourceItem(Items.IRON_NUGGET,1);
        deadMetalResource.addResourceItem(Items.IRON_INGOT,9);
        deadMetalResource.addResourceItem(Items.IRON_BLOCK,81);

        corruResource.addResourceItem(CorruArchitectItems.WILD_CORRU_CHUNK,1);

    }



}
