package com.corru_architect.base_manager;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseResourceType {

    private static final List<BaseResourceType> resourceTypeList = new ArrayList<>();

    private final String name;
    private final Map<Item,Integer> resourceItems;
    private final Map<TagKey<Item>,Integer> resourceTags;
    BaseResourceType(String resourceName, @NotNull Map<?,Integer> resourceItems){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = resourceItems.keySet().stream().findFirst().get() instanceof Item ? (Map<Item,Integer>)resourceItems : new HashMap<>();
        this.resourceTags = resourceItems.keySet().stream().findFirst().get() instanceof TagKey ? (Map<TagKey<Item>,Integer>)resourceItems : new HashMap<>();
    }
    BaseResourceType(String resourceName, @NotNull List<?> resourceItems, List<Integer> resourceValues){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceTags = new HashMap<>();
        for (int i = 0; i < resourceItems.size(); i++) {
            if(resourceItems.get(i) instanceof Item item)
                this.resourceItems.put(item,resourceValues.get(i));
            else if(resourceItems.get(i) instanceof TagKey item)
                this.resourceTags.put(item,resourceValues.get(i));
        }
    }
    BaseResourceType(String resourceName, @NotNull List<?> resourceItems){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceTags = new HashMap<>();
        for (Object resourceItem : resourceItems) {
            if (resourceItem instanceof Item item)
                this.resourceItems.put(item, 1);
            else if (resourceItem instanceof TagKey item)
                this.resourceTags.put(item, 1);
        }
    }
    BaseResourceType(String resourceName, Item resourceItem,int resourceValue){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceItems.put(resourceItem,resourceValue);
        this.resourceTags = new HashMap<>();
    }
    BaseResourceType(String resourceName, Item resourceItem){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceItems.put(resourceItem,1);
        this.resourceTags = new HashMap<>();
    }
    BaseResourceType(String resourceName, TagKey<Item> resourceItem,int resourceValue){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceTags = new HashMap<>();
        this.resourceTags.put(resourceItem,resourceValue);
    }
    BaseResourceType(String resourceName, TagKey<Item> resourceItem){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceTags = new HashMap<>();
        this.resourceTags.put(resourceItem,1);
    }

    BaseResourceType(String resourceName){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = new HashMap<>();
        this.resourceTags = new HashMap<>();
    }

    public static void loadTagValues() {
        for( BaseResourceType type :BaseResourceType.resourceTypeList) {
            if(!type.resourceTags.isEmpty())
                type.resourceTags.forEach(((tagKey, integer) ->
                        Registries.ITEM.forEach((item) ->{
                            if(new ItemStack(item,1).isIn(tagKey)){
                                type.addResourceItem(item,integer);
                            }
                        })
                ));
        }
    }

    public String getName() {
        return name;
    }

    public Map<Item, Integer> getResourceItems() {
        return resourceItems;
    }

    public Map<TagKey<Item>, Integer> getResourceTags() {
        return resourceTags;
    }

    public void addResourceItem(Item item, int value){
        this.resourceItems.put(item,value);
    }
    public void addResourceTag(TagKey<Item> tag, int value){
        this.resourceTags.put(tag,value);
    }

    public List<Item> getItemList(){
        return resourceItems.keySet().stream().toList();
    }

    public static List<BaseResourceType> getResourceTypeList() {
        return resourceTypeList;
    }
}
