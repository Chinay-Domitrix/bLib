package net.badbird5907.blib.util;

import java.util.HashMap;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

public class Cooldown {
	private static final HashMap<String, HashMap<UUID, Long>> cooldown = new HashMap<>();

	public static void createCooldown(String k) {
		if (cooldown.containsKey(k.toLowerCase())) return;
		cooldown.put(k.toLowerCase(), new HashMap<>());
	}

	public static HashMap<UUID, Long> getCooldownMap(String k) {
		return cooldown.getOrDefault(k.toLowerCase(), null);
	}

	public static void addCooldown(String k, UUID p, int seconds) {
		if (!cooldown.containsKey(k.toLowerCase())) cooldown.put(k.toLowerCase(), new HashMap<>());
		cooldown.get(k.toLowerCase()).put(p, currentTimeMillis() + (seconds * 1000L));
	}

	public static boolean isOnCooldown(String k, UUID p) {
		return (cooldown.containsKey(k.toLowerCase()) && cooldown.get(k.toLowerCase()).containsKey(p) && (currentTimeMillis() <= (Long) ((HashMap) cooldown.get(k.toLowerCase())).get(p)));
	}

	public static int getCooldownForPlayerInt(String k, UUID p) {
		return (int) ((Long) ((HashMap) cooldown.get(k.toLowerCase())).get(p) - currentTimeMillis()) / 1000;
	}

	public static long getCooldownForPlayerLong(String k, UUID p) {
		return (int) ((Long) ((HashMap) cooldown.get(k.toLowerCase())).get(p) - currentTimeMillis());
	}

	public static void removeCooldown(String k, UUID p) {
		if (!cooldown.containsKey(k.toLowerCase())) cooldown.put(k.toLowerCase(), new HashMap<>());
		if (cooldown.get(k.toLowerCase()).containsKey(p)) ((HashMap) cooldown.get(k.toLowerCase())).remove(p);
	}

	public static boolean wasOnCooldown(String k, UUID p) {
		if (!cooldown.containsKey(k.toLowerCase())) cooldown.put(k.toLowerCase(), new HashMap<>());
		return cooldown.get(k.toLowerCase()).containsKey(p);
	}

	public static boolean cooldownExists(String k) {
		return cooldown.containsKey(k);
	}
}
