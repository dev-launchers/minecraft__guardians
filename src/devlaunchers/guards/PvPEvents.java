package devlaunchers.guards;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import net.minecraft.server.v1_16_R3.EntityLiving;

public class PvPEvents implements Listener {

	public static LinkedList<GuardGolem> golems = new LinkedList<>();

	@EventHandler
	public void onPvE(EntityDamageByEntityEvent edb) {
		EntityLiving el = ((CraftLivingEntity) edb.getDamager()).getHandle();
		if (!(el instanceof GuardGolem)) {
			for (GuardGolem gg : golems) {
				if (gg.h(el) < 160) {
					Bukkit.broadcastMessage("Setting Goal Target!!");
					//Will set Entity as Target for said Guardian
					gg.setGoalTarget(el, TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, false);
				}
			}
		}
	}

}
