package devlaunchers.guards;

import org.bukkit.Location;

import net.minecraft.server.v1_16_R3.Navigation;
import net.minecraft.server.v1_16_R3.PathfinderGoal;

public class PathfinderGoalMoveToLocation extends PathfinderGoal {
	private float speed;

	private GuardGolem entity;

	private Navigation navigation;

	public PathfinderGoalMoveToLocation(GuardGolem entity, float speed) {
		this.entity = entity;
		this.navigation = (Navigation) this.entity.getNavigation();
		this.speed = speed;
	}

	/**
	 * If this function returns true b() is executed
	 */
	@Override
	public boolean a() {
		if (entity.getGoalTarget() != null) {
			return false;
		}
		if (entity.h(entity.getSpawnBlockX(), entity.getSpawnBlockY(), entity.getSpawnBlockZ()) < 2) {
			return false;
		}
		return true;
	}

	/**
	 * This function will be executed as long as c() returns true
	 * Navigates Entity to its Spawn Location
	 */
	@Override
	public boolean b() {
		return !this.entity.getNavigation().m()
				&& entity.h(entity.getSpawnBlockX(), entity.getSpawnBlockY(), entity.getSpawnBlockZ()) > 1
				&& entity.getGoalTarget() == null;
	}

	/**
	 * Returns true as long as b() should execute
	 * i.e. as long as the Guardian isn't at it's target
	 */
	@Override
	public void c() {
		this.navigation.a(entity.getSpawnBlockX(), entity.getSpawnBlockY(), entity.getSpawnBlockZ(), speed);
	}

}