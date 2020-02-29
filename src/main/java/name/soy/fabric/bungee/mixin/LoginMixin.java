package name.soy.fabric.bungee.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import name.soy.fabric.bungee.BungeeHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerLoginNetworkHandler;
@Mixin(ServerLoginNetworkHandler.class)
public class LoginMixin {
	@Shadow
	@Final
	private ClientConnection connection;

	@Overwrite
	public GameProfile toOfflineProfile(GameProfile profile) {
		BungeeHandler handler = BungeeHandler.bungeehandler;
		if (handler.isEnabled()&&handler.handledUUID.containsKey(connection)) {
			UUID uuid = handler.handledUUID.get(connection);
			GameProfile profile2 = new GameProfile(uuid, profile.getName());
			if(handler.handledProperty.containsKey(connection)) {
				for(Property pp:handler.handledProperty.get(connection)) {
					profile2.getProperties().put(pp.getName(), pp);
				}
			}
			handler.handledProperty.remove(connection);
			handler.handledUUID.remove(connection);
			return profile2;
		} else {
			UUID uUID = PlayerEntity.getOfflinePlayerUuid(profile.getName());
			return new GameProfile(uUID, profile.getName());
		}
		
	}
}
