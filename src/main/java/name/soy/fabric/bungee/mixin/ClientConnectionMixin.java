package name.soy.fabric.bungee.mixin;

import java.net.SocketAddress;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.network.ClientConnection;

@Mixin(ClientConnection.class)
public interface ClientConnectionMixin {
	
	@Accessor("address")
	void setAddress(SocketAddress address);
}
