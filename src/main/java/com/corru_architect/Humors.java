package com.corru_architect;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.ArmadilloEntity;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import org.joml.Vector3f;

import java.util.function.IntFunction;

public enum Humors implements StringIdentifiable {
    BONE("bone"),
    CLAWS("claws"),
    EYES("eyes"),
    ICHOR("ichor"),
    LIGHT("light"),
    NONE("none");

    private final String name;
    private final Text translatableName;

    Humors(String name) {
        this.name = name;
        this.translatableName = Text.translatable(CorruArchitect.MOD_ID+"."+name);
    }

    public Text getTranslatableName() {
        return translatableName;
    }

    @Override
    public String asString() {
        return name;
    }

    public static final IntFunction<Humors> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(Humors::ordinal, values(), ValueLists.OutOfBoundsHandling.ZERO);
    public static final PacketCodec<ByteBuf, Humors> HUMORS_PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, Humors::ordinal);
    public static final TrackedDataHandler<Humors> HUMORS_TRACKED_DATA_HANDLER = TrackedDataHandler.create(HUMORS_PACKET_CODEC) ;

}