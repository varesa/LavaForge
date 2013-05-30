package fi.dy.esav.LavaForge;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;

public class Recipes {
	
	LavaForge plugin;
	
	HashMap<Integer, FurnaceRecipe> recipes = new HashMap<Integer,FurnaceRecipe>();
	
	public Recipes(LavaForge plugin) {
		this.plugin = plugin;
	}
	
	public void initRecipes() {
		Iterator<?> itr = plugin.getServer().recipeIterator();
		while(itr.hasNext()) {
			Object o = itr.next();
			if(o instanceof FurnaceRecipe) {
				FurnaceRecipe r = (FurnaceRecipe) o;
				recipes.put(r.getInput().getTypeId(), r);
				//System.out.println("Found recipe:" + ((FurnaceRecipe)o).getResult().getTypeId());
			}
			//System.out.println(o.toString());
			
		}
	}
	
	public FurnaceRecipe getRecipe(int inputId) {
		return recipes.get(inputId);
	}
}
