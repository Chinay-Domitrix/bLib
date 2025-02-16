package net.badbird5907.blib.spigotmc;

import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import static net.badbird5907.blib.bLib.getPlugin;
import static net.badbird5907.blib.util.Tasks.runAsync;

public class UpdateChecker {
	private final int resourceId;

	public UpdateChecker(int resourceId) {
		this.resourceId = resourceId;
	}

	public void getVersion(final Consumer<String> consumer) {
		if (resourceId == -1) return;
		runAsync(() -> {
			try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
				if (scanner.hasNext()) consumer.accept(scanner.next());
			} catch (IOException exception) {
				getPlugin().getLogger().info("Cannot look for updates: " + exception.getMessage());
			}
		});
	}
}
