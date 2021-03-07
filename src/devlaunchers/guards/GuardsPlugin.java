package devlaunchers.guards;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GuardsPlugin extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PvPEvents(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent pje) {
		((CraftWorld) pje.getPlayer().getWorld()).getHandle().addEntity(new GuardGolem(pje.getPlayer().getLocation()));
		Bukkit.broadcastMessage("Spawn Guard!");
	}

}
