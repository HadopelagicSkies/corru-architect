package com.corru_architect.packet_payloads;

import com.corru_architect.CorruArchitect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record GrantAdvancementPayload (String puzzleName) implements CustomPayload {
    public static final Identifier GRANT_ADVANCEMENT_PACKET_ID = Identifier.of(CorruArchitect.MOD_ID,"grant_advancement");
    public static final CustomPayload.Id<GrantAdvancementPayload> ID = new CustomPayload.Id<>(GRANT_ADVANCEMENT_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf,GrantAdvancementPayload> GRANT_ADVANCEMENT_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, GrantAdvancementPayload::puzzleName,
            GrantAdvancementPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
