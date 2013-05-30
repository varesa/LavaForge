package fi.dy.esav.LavaForge;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.block.*;

public class LavaForgeEntityListener implements Listener {

	private LavaForge plugin;
	
	public LavaForgeEntityListener(LavaForge plugin) {
		this.plugin = plugin;
	}
	
	EntityCombustEvent e;
	Location loc;
	int x;
	Sign sign;

	int[] xmod = { -1, 0, 1 };
	int[] zmod = { -1, 0, 1 };

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityCombust(EntityCombustEvent e) {
		if (e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation())
				.getType() == Material.LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation())
						.getRelative(BlockFace.DOWN).getType() == Material.LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation()).getType() == Material.STATIONARY_LAVA
				|| e.getEntity().getWorld()
						.getBlockAt(e.getEntity().getLocation())
						.getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_LAVA) {
			this.e = e;
			lavaBurned();
		}


	}

	public void lavaBurned() {
		if (e.getEntity() instanceof Item) {
			if (checkIfIsFurnace()) {
				
				ItemStack in = ((Item) e.getEntity()).getItemStack();
				ItemStack out = null;
				ItemStack out2 = null;
				
				FurnaceRecipe r = (plugin.recipes.getRecipe(in.getTypeId()));
				
				if(r == null) {
					out = in.clone();
				} else {
					
					int amount = (int) Math.floor(in.getAmount() / r.getInput().getAmount());
					int excess = (int) in.getAmount() - amount*r.getInput().getAmount(); 
					
					if(amount > 0) {
						out = new ItemStack(r.getResult().getType(), r.getResult().getAmount()*amount, r.getResult().getDurability());
					}
					out2 = new ItemStack(in.getType(), excess, in.getDurability());
					
				}
				
				//plugin.getServer().broadcastMessage("Recipe found \nin: " + r.getInput().getType().name() + 
				//		", out: " + r.getResult().getType().name());
				
				
				
				/*List<Recipe> recipes = plugin.getServer().getRecipesFor(i.getItemStack());
				for (Recipe recipe : recipes) {
					if(recipe instanceof FurnaceRecipe) {
						System.out.println("Furnace recipe for id: " + String.valueOf(((FurnaceRecipe) recipe).getResult().getTypeId()));
					}
				}*/
				
				/*if (i.getItemStack().getTypeId() == Material.COBBLESTONE.getId()) {
					is = new ItemStack(Material.STONE.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.SAND.getId()) {
					is = new ItemStack(Material.GLASS.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.GOLD_ORE.getId()) {
					is = new ItemStack(Material.GOLD_INGOT.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.IRON_ORE.getId()) {
					is = new ItemStack(Material.IRON_INGOT.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.LOG.getId()) {
					is = new ItemStack(Material.COAL.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.CLAY_BALL.getId()) {
					is = new ItemStack(Material.CLAY_BRICK.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.RAW_FISH.getId()) {
					is = new ItemStack(Material.COOKED_FISH.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.PORK.getId()) {
					is = new ItemStack(Material.GRILLED_PORK.getId(), 1);
				} else if (i.getItemStack().getTypeId() == Material.CACTUS.getId()) {
					is = new ItemStack(Material.INK_SACK.getId(), 1, (short) 2);
				} else if (i.getItemStack().getTypeId() == Material.DIAMOND_ORE.getId()) {
					is = new ItemStack(Material.DIAMOND.getId(), 1);
				} else {

					is = i.getItemStack();
				}*/
				e.getEntity().remove();
				loc.getWorld().dropItem(new Location(loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY(),loc.getBlockZ()+0.5), out);
				loc.getWorld().dropItem(new Location(loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY(),loc.getBlockZ()+0.5), out2);
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
								}
								
							} else {
								
								if (loc.getWorld().getBlockAt(loc.getBlockX() + xm,loc.getBlockY(),loc.getBlockZ() + zm).getType() == Material.OBSIDIAN 
										 && loc.getWorld().getBlockAt(loc.getBlockX() + xm,loc.getBlockY()+1,loc.getBlockZ() + zm).getType() == Material.OBSIDIAN) {
									

								}


								
							}
									
						}
					}
					return true;
				}
			}
			loc.setY(loc.getY() - 1);
		}
		return false;

	}
}
