package com.gamerforea.ic2;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gamerforea.eventhelper.util.ConvertUtils;
import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.FastUtils;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public final class ModUtils
{
	public static final GameProfile profile = new GameProfile(UUID.fromString("6c788982-d6ca-11e4-b9d6-1681e6b88ec1"), "[IC2]");
	private static FakePlayer player = null;

	public static final FakePlayer getModFake(World world)
	{
		if (player == null)
			player = FastUtils.getFake(world, profile);
		else
			player.worldObj = world;

		return player;
	}

	public static final boolean cantBreakOrNotInPrivate(EntityPlayer player, int x, int y, int z)
	{
		return !EventUtils.isInPrivate(player.worldObj, x, y, z) || EventUtils.cantBreak(player, x, y, z);
	}

	public static final boolean hasPermission(UUID playerId, String permission)
	{
		try
		{
			Player player = Bukkit.getPlayer(playerId);
			return player != null && player.hasPermission(permission);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static final boolean hasPermission(EntityPlayer player, String permission)
	{
		try
		{
			return ConvertUtils.toBukkitEntity(player).hasPermission(permission);
		}
		catch (Exception e)
		{
			return false;
		}
	}
}