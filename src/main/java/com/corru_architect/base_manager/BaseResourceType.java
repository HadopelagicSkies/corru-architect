package com.corru_architect.base_manager;

import com.corru_architect.CorruArchitect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.mixin.registry.sync.RegistryKeysMixin;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BaseResourceType {

    public static final RegistryKey<Registry<BaseResourceType>> BASE_RESOURCE_REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.of(CorruArchitect.MOD_ID,"base_resource"));
    public static final Registry<BaseResourceType> BASE_RESOURCE_REGISTRY = Registries.create(BASE_RESOURCE_REGISTRY_KEY,registry -> register(new BaseResourceType("",0)));
    private RegistryEntry<BaseResourceType> registryEntry;

    private static final List<BaseResourceType> resourceTypeList = new ArrayList<>();

    private final String name;
    private final Map<Item,Integer> resourceItems;
    private final Map<TagKey<Item>,Integer> resourceTags;
    private final int initCapacity;

    // All inputs as Maps
    BaseResourceType(String resourceName, Map<Item, Integer> resourceItems, @Nullable Map<TagKey<Item>, Integer> resourceTags, int initCapacity){
        resourceTypeList.add(this);
        this.name = resourceName;
        this.resourceItems = resourceItems!=null ? resourceItems:new HashMap<>();
        this.resourceTags = resourceTags!=null ? resourceTags:new HashMap<>();
        this.initCapacity = initCapacity;
    }

    // All inputs as Lists
    BaseResourceType(String resourceName, @Nullable List<Item> resourceItems,@Nullable List<Integer> itemValues,@Nullable List<TagKey<Item>> resourceTags,@Nullable List<Integer> tagValues, int initCapacity) {
        this(resourceName, mapValues(resourceItems!=null ? resourceItems:new ArrayList<>(),itemValues!=null ? itemValues:new ArrayList<>()), mapValues(resourceTags!=null ? resourceTags:new ArrayList<>(),tagValues!=null ? tagValues:new ArrayList<>()),initCapacity);
    }
    // All inputs as Lists with Separate Single Values
    BaseResourceType(String resourceName,List<Item> resourceItems, int itemValue,List<TagKey<Item>> resourceTags, int tagValue, int initCapacity) {
        this(resourceName, mapValues(resourceItems,listValue(resourceTags,itemValue)), mapValues(resourceTags,listValue(resourceTags,tagValue)),initCapacity);
    }
    // All inputs as Lists with Single Shared Value
    BaseResourceType(String resourceName,List<Item> resourceItems,List<TagKey<Item>> resourceTags, int value, int initCapacity) {
        this(resourceName, mapValues(resourceItems,listValue(resourceTags,value)), mapValues(resourceTags,listValue(resourceTags,value)),initCapacity);
    }

    // Single Item/Tag Inputs
    BaseResourceType(String resourceName,Item resourceItem,int itemValue, int initCapacity) {
        this(resourceName, Map.of(resourceItem,itemValue),null,initCapacity);
    }
    BaseResourceType(String resourceName,TagKey<Item> resourceTag,int tagValue, int initCapacity) {
        this(resourceName,null, Map.of(resourceTag,tagValue),initCapacity);
    }


    //No associated items
    BaseResourceType(String resourceName, int initCapacity){
        this(resourceName, null,null,initCapacity);
    }

    private static <V> Map<V,Integer> mapValues(List<V> keys, List<Integer> values) {
        Map<V, Integer> map = new HashMap<>();
        for (int i = 0; i < Math.min(keys.size(), values.size()); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
    private static <V> List<Integer> listValue(List<V> keys, int value) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            list.add(value);
        }
        return list;
    }


    public static void loadTagValues() {
        for( BaseResourceType type :BaseResourceType.resourceTypeList) {
            if(!type.resourceTags.isEmpty())
                type.resourceTags.forEach(((tagKey, integer) ->
                        Registries.ITEM.forEach((item) ->{
                            if(item.getRegistryEntry().isIn(tagKey)){
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

    public int getInitCapacity() {
        return initCapacity;
    }

    public RegistryEntry<BaseResourceType> getRegistryEntry() {
        return registryEntry;
    }

    //For codec use
    private static BaseResourceType codecItemConvert(String resourceName, Map<RegistryEntry<Item>, Integer> resourceItems, Map<TagKey<Item>, Integer> resourceTags, int initCapacity) {
        return new BaseResourceType(resourceName,itemsFromRegistry(resourceItems.keySet().stream().toList()), (List<Integer>) resourceItems.values(), resourceTags.keySet().stream().toList(), (List<Integer>) resourceTags.values(),initCapacity);
    }

    private static List<Item> itemsFromRegistry(List<RegistryEntry<Item>> registryEntries){
        List<Item> list = new ArrayList<>();
        for (RegistryEntry<Item> registryEntry : registryEntries) {
            list.add(registryEntry.value());
        }
        return list;
    }
    private static Map<RegistryEntry<Item>, Integer> itemsToRegistry(BaseResourceType baseResourceType){
        Map<RegistryEntry<Item>, Integer> entryMap = new HashMap<>();
        baseResourceType.resourceItems.forEach((item,integer) -> entryMap.put(item.getRegistryEntry(),integer));
        return entryMap;
    }

    public static BaseResourceType register(BaseResourceType baseResourceType){
        Registry.register(BASE_RESOURCE_REGISTRY,Identifier.of(CorruArchitect.MOD_ID,baseResourceType.name),baseResourceType);
        baseResourceType.registryEntry = RegistryEntry.of(baseResourceType);
        return baseResourceType;
    }

    public static final Codec<BaseResourceType> BASE_RESOURCE_TYPE_CODEC = RecordCodecBuilder.create(baseResourceTypeInstance -> baseResourceTypeInstance.group(
            Codec.STRING.fieldOf("name").forGetter(BaseResourceType::getName),
            Codec.unboundedMap(Item.ENTRY_CODEC,Codec.INT).fieldOf("resourceItems").forGetter(BaseResourceType::itemsToRegistry),
            Codec.unboundedMap(TagKey.codec(RegistryKeys.ITEM),Codec.INT).fieldOf("resourceTags").forGetter(BaseResourceType::getResourceTags),
            Codec.INT.fieldOf("initCapacity").forGetter(BaseResourceType::getInitCapacity)
            ).apply(baseResourceTypeInstance, BaseResourceType::codecItemConvert));
}
