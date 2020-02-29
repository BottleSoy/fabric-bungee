package name.soy.fabric.bungee;

import java.io.File;
public abstract class FunctionEntry {
	final String name;
	public String getName() {
		return name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	boolean enabled = false;
	public void setEnabled(boolean enabled) {
		if(this.enabled==enabled) {
			throw new IllegalStateException();
		}
		if(enabled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
		this.enabled = enabled;
	}
	public void reload() {
		this.onEnable();
		this.onDisable();
	}
	public final File getDataFolder() {
		File f = new File("mods\\"+name);
		if(!f.isDirectory())f.mkdirs();
		return f;
	}
	protected abstract void onEnable();
	
	protected abstract void onDisable();
	public FunctionEntry(String name) {
		this.name = name;
	}
	
	
}
