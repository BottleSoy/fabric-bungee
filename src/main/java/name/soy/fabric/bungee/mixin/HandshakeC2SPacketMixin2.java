package name.soy.fabric.bungee.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.network.NetworkState;
import net.minecraft.server.network.packet.HandshakeC2SPacket;
import net.minecraft.util.PacketByteBuf;

@Mixin(HandshakeC2SPacket.class)
public class HandshakeC2SPacketMixin2 {
	@Shadow
	private int protocolVersion;
	@Shadow
	private String address;
	@Shadow
	private int port;
	@Shadow
	private NetworkState intendedState;

	@Overwrite
	public void read(PacketByteBuf buf) throws IOException {
		this.protocolVersion = buf.readVarInt();
		this.address = buf.readString(65536);
		this.port = buf.readUnsignedShort();
		this.intendedState = NetworkState.byId(buf.readVarInt());
	}
}
