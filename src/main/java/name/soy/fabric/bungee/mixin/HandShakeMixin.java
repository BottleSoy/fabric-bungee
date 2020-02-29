package name.soy.fabric.bungee.mixin;

import java.net.InetSocketAddress;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;

import name.soy.fabric.bungee.BungeeHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerHandshakeNetworkHandler;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.packet.HandshakeC2SPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

@Mixin(ServerHandshakeNetworkHandler.class)
public class HandShakeMixin {
	@Shadow
	@Final
	private ClientConnection connection;

	@Inject(method = "onHandshake", at = @At("TAIL"))
	public void handshake(HandshakeC2SPacket packet, CallbackInfo info) {
		BungeeHandler handler = BungeeHandler.bungeehandler;
		// 如果不是那就是被踢了
		if (connection.getPacketListener() instanceof ServerLoginNetworkHandler && handler.isEnabled()) {
			String address = ((HandshakeC2SPacketMixin) packet).getAddress();
			String[] split = address.split("\000");
			if ((split.length == 3 || split.length == 4)&& connection.getAddress().toString().contains("127.0.0.1")) {
				((HandshakeC2SPacketMixin) packet).setAddress(split[0]);
				((ClientConnectionMixin) this.connection).setAddress(
						new InetSocketAddress(split[1], ((InetSocketAddress) this.connection.getAddress()).getPort()));
				handler.handledUUID.put(connection, UUIDTypeAdapter.fromString(split[2]));
			} else {
//				Text chatmessage = new LiteralText(
//						"你的想法有点危险啊\n----by soy_bottle\n错误码:7715244");
				//this.connection.send(new DisconnectS2CPacket(chatmessage));
				//this.connection.disconnect(chatmessage);
				return;
			}
			if (split.length == 4)
				handler.handledProperty.put(connection, new Gson().fromJson(split[3], Property[].class));
		}
		
	}

}
