package devlaunchers.guards;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityIronGolem;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalDefendVillage;
import net.minecraft.server.v1_16_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R3.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;

public class GuardGolem extends EntityIronGolem {

	double spawnX, spawnY, spawnZ;

	protected GuardGolem(Location spawn) {
		super(EntityTypes.IRON_GOLEM, ((CraftWorld) spawn.getWorld()).getHandle());

		this.spawnX = spawn.getX();
		this.spawnY = spawn.getY();
		this.spawnZ = spawn.getZ();

		setPosition(spawn.getX(), spawn.getY(), spawn.getZ());

		setCustomName(new ChatComponentText("Guard"));
		setCustomNameVisible(true);
		
		PvPEvents.golems.add(this);
	}

	@Override
	public void initPathfinder() {
		//Goal Selectors act upon existing Targets (i.e. *walk* towards Enemy)
		
		//If possible attack Enemy via Melee
		this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1F, true));
		//Move towards target if needed
		this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.22F, 32.0F));
		//Walk back to it's Spawn
		this.goalSelector.a(4, new PathfinderGoalMoveToLocation(this, 1));
		
		//Quality of Life Improvements (Guards look lifely)
		this.goalSelector.a(5, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
		this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));

		//Will defend "Village" against Monsters
		this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
		//Targets anyone that attacks the Guard
		this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
		
		//Targets someone that is set as GoalTarget (by for example PvPEvents)  
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, 10,
				true, false, this::a_));
	}

	public double getSpawnBlockX() {
		return spawnX;
	}

	public double getSpawnBlockY() {
		return spawnY;
	}

	public double getSpawnBlockZ() {
		return spawnZ;
	}

}
