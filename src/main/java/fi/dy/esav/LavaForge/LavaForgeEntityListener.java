package fi.dy.esav.LavaForge;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.*;

public class LavaForgeEntityListener extends EntityListener {

	EntityCombustEvent e;
	Location loc;
	int x;
	Sign sign;

	int[] xmod = { -1, 0, 1 };
	int[] zmod = { -1, 0, 1 };

	public void onEntityCombust(EntityCombustEvent e) {
		if (e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation())
				.getType() == Material.LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation())
						.getFace(BlockFace.DOWN, 1).getType() == Material.LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation()).getType() == Material.STATIONARY_LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation())
						.getFace(BlockFace.DOWN, 1).getType() == Material.STATIONARY_LAVA) {
			System.out.println("Something was thrown in to lava!");
			this.e = e;
			lavaBurned();
		}

		System.out.println(e.getEntity().getWorld()
				.getBlockAt(e.getEntity().getLocation()).getType());
		System.out.println(e.getEntity().getWorld()
				.getBlockAt(e.getEntity().getLocation())
				.getFace(BlockFace.DOWN, 1).getType());
	}

	public void lavaBurned() {
		if (e.getEntity() instanceof Item) {
			System.out.println("Is instanceof item");
			if (checkIfIsFurnace()) {
				
				Item i = (Item) e.getEntity();
				System.out.println("Dropping Item");
				e.getEntity().getWorld().dropItem(new Location(loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY()+0.5,loc.getBlockZ()+0.5), i.getItemStack());
			} else {
				
			}
		}

	}

	public boolean checkIfIsFurnace() {
		loc = e.getEntity().getLocation();
		for (x = 0; x < 4; x++) {
			if (loc.getBlock().getType() == Material.SIGN
					|| loc.getBlock().getType() == Material.SIGN_POST
					|| loc.getBlock().getType() == Material.WALL_SIGN) {
				sign = (Sign) loc.getBlock().getState();
				if (sign.getLines()[0].equalsIgnoreCase("Furnace")) {
					for (int xm : xmod) {
						for (int zm : zmod) {
							if (xm == 0 && zm == 0) {
								
								if (loc.getBlock().getType() == Material.LAVA || loc.getBlock().getType() == Material.STATIONARY_LAVA || loc.getBlock().getType() == Material.SIGN || loc.getBlock().getType() == Material.WALL_SIGN || loc.getBlock().getType() == Material.SIGN_POST) {
									System.out.println("1 is okay");
								}
								
							} else {
								
								if (loc.getWorld().getBlockAt(loc.getBlockX() + xm,loc.getBlockY(),loc.getBlockZ() + zm).getType() == Material.OBSIDIAN 
										 && loc.getWorld().getBlockAt(loc.getBlockX() + xm,loc.getBlockY()+1,loc.getBlockZ() + zm).getType() == Material.OBSIDIAN) {
									
									System.out.println("2 is okay");

								}


								
							}
							System.out.println(".");
									
						}
					}
					System.out.println("JA SEHÄN ON UUNI!");
					return true;
				}
			}
			loc.setY(loc.getY() - 1);
		}
		return false;

	}
}
