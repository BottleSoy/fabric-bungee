package name.soy.fabric.bungee.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.server.network.packet.HandshakeC2SPacket;

@Mixin(HandshakeC2SPacket.class)
public interface HandshakeC2SPacketMixin {
	@Accessor("address")
	String getAddress();
	@Accessor("address")
	void setAddress(String address);
}
