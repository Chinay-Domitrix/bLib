package net.badbird5907.blib.menu;

import net.badbird5907.blib.menu.buttons.Button;
import net.badbird5907.blib.menu.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static net.badbird5907.blib.menu.MenuManager.getOpenedMenus;
import static org.bukkit.event.EventPriority.HIGHEST;

public class MenuListener implements Listener {

	@EventHandler(priority = HIGHEST)
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Menu menu = getOpenedMenus().get(player.getUniqueId());
		if (menu == null) return;
		if (menu.isCancel()) event.setCancelled(true);
		if (event.getSlot() != event.getRawSlot()) return;
		if (!menu.hasSlot(event.getSlot())) return;
		Button slot = menu.getSlot(event.getSlot());
		slot.onClick(player, event.getSlot(), event.getClick());
	}

	@EventHandler(priority = HIGHEST)
	public void onClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		Menu menu = getOpenedMenus().get(player.getUniqueId());
		if (menu == null) return;
		menu.onClose(player);
		getOpenedMenus().remove(player.getUniqueId());
	}
}
