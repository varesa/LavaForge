package fi.dy.esav.LavaForge;

import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityListener;

public class LavaForgeEntityListener extends EntityListener {

	public void onEntityCombust(EntityCombustEvent e) {
		System.out.println("Entity triggered!");
	}
}
