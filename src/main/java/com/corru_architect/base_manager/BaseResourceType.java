package com.corru_architect.base_manager;

import com.corru_architect.CorruArchitect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BaseResourceType {

    public static final RegistryKey<Registry<BaseResourceType>> BASE_RESOURCE_REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.of(CorruArchitect.MOD_ID,"base_resource"));
    public static final Registry<BaseResourceType> BASE_RESOURCE_REGISTRY = Registries.create(BASE_RESOURCE_REGISTRY_KEY,registry -> register(new BaseResourceType("", Blocks.AIR,0)));
    private RegistryEntry<BaseResourceType> registryEntry;

    private final String name;
    private final Block managerBlock;
    private final Map<Item,Float> resourceItems;
    private final Map<TagKey<Item>,Float> resourceTags;
    private final float initCapacity;

    // All inputs as Maps
    BaseResourceType(String resourceName, Block managerBlock, Map<Item, Float> resourceItems, @Nullable Map<TagKey<Item>, Float> resourceTags, float initCapacity){
        this.name = resourceName;
        this.managerBlock = managerBlock;
        this.resourceItems = resourceItems!=null ? resourceItems:new HashMap<>();
        this.resourceTags = resourceTags!=null ? resourceTags:new HashMap<>();
        this.initCapacity = initCapacity;
    }

    // All inputs as Lists
    BaseResourceType(String resourceName, Block managerBlock, @Nullable List<Item> resourceItems,@Nullable List<Float> itemValues,@Nullable List<TagKey<Item>> resourceTags,@Nullable List<Float> tagValues, float initCapacity) {
        this(resourceName,managerBlock, mapValues(resourceItems!=null ? resourceItems:new ArrayList<>(),itemValues!=null ? itemValues:new ArrayList<>()), mapValues(resourceTags!=null ? resourceTags:new ArrayList<>(),tagValues!=null ? tagValues:new ArrayList<>()),initCapacity);
    }
    // All inputs as Lists with Separate Single Values
    BaseResourceType(String resourceName, Block managerBlock,List<Item> resourceItems, float itemValue,List<TagKey<Item>> resourceTags, float tagValue, float initCapacity) {
        this(resourceName,managerBlock, mapValues(resourceItems,listValue(resourceTags,itemValue)), mapValues(resourceTags,listValue(resourceTags,tagValue)),initCapacity);
    }
    // All inputs as Lists with Single Shared Value
    BaseResourceType(String resourceName, Block managerBlock,List<Item> resourceItems,List<TagKey<Item>> resourceTags, float value, float initCapacity) {
        this(resourceName,managerBlock, mapValues(resourceItems,listValue(resourceTags,value)), mapValues(resourceTags,listValue(resourceTags,value)),initCapacity);
    }

    // Single Item/Tag Inputs
    BaseResourceType(String resourceName, Block managerBlock,Item resourceItem,float itemValue, float initCapacity) {
        this(resourceName,managerBlock, Map.of(resourceItem,itemValue),null,initCapacity);
    }
    BaseResourceType(String resourceName, Block managerBlock,TagKey<Item> resourceTag,float tagValue, float initCapacity) {
        this(resourceName,managerBlock,null, Map.of(resourceTag,tagValue),initCapacity);
    }


    //No associated items
    BaseResourceType(String resourceName, Block managerBlock, float initCapacity){
        this(resourceName,managerBlock, null,null,initCapacity);
    }

    private static <V> Map<V,Float> mapValues(List<V> keys, List<Float> values) {
        Map<V, Float> map = new HashMap<>();
        for (int i = 0; i < Math.min(keys.size(), values.size()); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
    private static <V> List<Float> listValue(List<V> keys, float value) {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            list.add(value);
        }
        return list;
    }


    public static void loadTagValues() {
        for( RegistryKey<BaseResourceType> typeKey :BASE_RESOURCE_REGISTRY.getKeys()) {
            BaseResourceType type = BASE_RESOURCE_REGISTRY.get(typeKey);
            if(!type.resourceTags.isEmpty())
                type.resourceTags.forEach(((tagKey, value) ->
                        Registries.ITEM.forEach((item) ->{
                            if(item.getRegistryEntry().isIn(tagKey)){
                                type.addResourceItem(item,value);
                            }
                        })
                ));
        }
    }

    public String getName() {
        return name;
    }

    public Block getManagerBlock() {
        return managerBlock;
    }

    public Map<Item, Float> getResourceItems() {
        return resourceItems;
    }

    public Map<TagKey<Item>, Float> getResourceTags() {
        return resourceTags;
    }

    public void addResourceItem(Item item, float value){
        this.resourceItems.put(item,value);
    }
    public void addResourceTag(TagKey<Item> tag, float value){
        this.resourceTags.put(tag,value);
    }

    public List<Item> getItemList(){
        return resourceItems.keySet().stream().toList();
    }

    public float getInitCapacity() {
        return initCapacity;
    }

    public RegistryEntry<BaseResourceType> getRegistryEntry() {
        return registryEntry;
    }

    //For codec use
    private static BaseResourceType codecItemConvert(String resourceName, Block managerBlock, Map<RegistryEntry<Item>, Float> resourceItems, Map<TagKey<Item>, Float> resourceTags, Float initCapacity) {
        return new BaseResourceType(resourceName,managerBlock,itemsFromRegistry(resourceItems.keySet().stream().toList()), (List<Float>) resourceItems.values(), resourceTags.keySet().stream().toList(), (List<Float>) resourceTags.values(),initCapacity);
    }

    private static List<Item> itemsFromRegistry(List<RegistryEntry<Item>> registryEntries){
        List<Item> list = new ArrayList<>();
        for (RegistryEntry<Item> registryEntry : registryEntries) {
            list.add(registryEntry.value());
        }
        return list;
    }
    private static Map<RegistryEntry<Item>, Float> itemsToRegistry(BaseResourceType baseResourceType){
        Map<RegistryEntry<Item>, Float> entryMap = new HashMap<>();
        baseResourceType.resourceItems.forEach((item,value) -> entryMap.put(item.getRegistryEntry(),value));
        return entryMap;
    }

    public static BaseResourceType register(BaseResourceType baseResourceType){
        Registry.register(BASE_RESOURCE_REGISTRY,Identifier.of(CorruArchitect.MOD_ID,baseResourceType.name),baseResourceType);
        baseResourceType.registryEntry = BASE_RESOURCE_REGISTRY.getEntry(baseResourceType);
        return baseResourceType;
    }

    public static final Codec<BaseResourceType> BASE_RESOURCE_TYPE_CODEC = RecordCodecBuilder.create(baseResourceTypeInstance -> baseResourceTypeInstance.group(
            Codec.STRING.fieldOf("name").forGetter(BaseResourceType::getName),
            Block.CODEC.fieldOf("managerBlock").forGetter(BaseResourceType::getManagerBlock),
            Codec.unboundedMap(Item.ENTRY_CODEC,Codec.FLOAT).fieldOf("resourceItems").forGetter(BaseResourceType::itemsToRegistry),
            Codec.unboundedMap(TagKey.codec(RegistryKeys.ITEM),Codec.FLOAT).fieldOf("resourceTags").forGetter(BaseResourceType::getResourceTags),
            Codec.FLOAT.fieldOf("initCapacity").forGetter(BaseResourceType::getInitCapacity)
            ).apply(baseResourceTypeInstance, BaseResourceType::codecItemConvert));
}
