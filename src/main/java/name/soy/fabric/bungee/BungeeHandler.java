package name.soy.fabric.bungee;

import java.util.HashMap;
import java.util.UUID;

import com.mojang.authlib.properties.Property;

import net.fabricmc.api.ModInitializer;
import net.minecraft.network.ClientConnection;

public class BungeeHandler extends FunctionEntry implements ModInitializer {
	public static BungeeHandler bungeehandler;
	public HashMap<ClientConnection, UUID> handledUUID = new HashMap<>();
	public HashMap<ClientConnection, Property[]> handledProperty = new HashMap<>();
	public BungeeHandler() {
		super("bungee");
		bungeehandler = this;
	}

	@Override
	protected void onEnable() {
		
	}
	
	@Override
	protected void onDisable() {
		
	}

	@Override
	public void onInitialize() {
		setEnabled(true);
	}

}
