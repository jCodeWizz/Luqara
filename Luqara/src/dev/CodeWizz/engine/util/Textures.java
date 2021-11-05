package dev.CodeWizz.engine.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import dev.CodeWizz.engine.gfx.Image;

public class Textures {

	private static HashMap<String, Image> list = new HashMap<>();
	
	public static BufferedImage leaves;
	public static BufferedImage log;
	public static BufferedImage stump;
	public static BufferedImage nest;
	
	public void load() {
		WDebug.log("[System]: Loading textures...");
		
		leaves = getImage("/assets/textures/objects/tree/leaves.png");
		log = getImage("/assets/textures/objects/tree/log.png");
		nest = getImage("/assets/textures/objects/tree/nest.png");
		stump = getImage("/assets/textures/objects/tree/stump.png");
		
		list.put("recipe_woodenplanks", new Image("/assets/textures/items/recipes/recipe_woodenplanks.png"));
		list.put("recipe_sharpenedstick", new Image("/assets/textures/items/recipes/recipe_sharpenedstick.png"));
		
		list.put("berrybush0", new Image("/assets/textures/objects/berrybush/berrybush0.png"));
		list.put("berrybush1", new Image("/assets/textures/objects/berrybush/berrybush1.png"));
		list.put("berrybush2", new Image("/assets/textures/objects/berrybush/berrybush2.png"));
		list.put("berrybush3", new Image("/assets/textures/objects/berrybush/berrybush3.png"));
		list.put("berrybush4", new Image("/assets/textures/objects/berrybush/berrybush4.png"));
		
		list.put("rock", new Image("/assets/textures/objects/rock/rock.png"));
		list.put("rockBroken", new Image("/assets/textures/objects/rock/broken.png"));
		
		list.put("balrups1", new Image("/assets/textures/objects/balrups/fly1.png"));
		list.put("balrups2", new Image("/assets/textures/objects/balrups/fly2.png"));
		list.put("balrups3", new Image("/assets/textures/objects/balrups/fly3.png"));
		list.put("balrups4", new Image("/assets/textures/objects/balrups/fly4.png"));
		list.put("balrupsHurt1", new Image("/assets/textures/objects/balrups/flyhurt1.png"));
		list.put("balrupsHurt2", new Image("/assets/textures/objects/balrups/flyhurt2.png"));
		list.put("balrupsHurt3", new Image("/assets/textures/objects/balrups/flyhurt3.png"));
		list.put("balrupsHurt4", new Image("/assets/textures/objects/balrups/flyhurt4.png"));
		
		list.put("cowIdle", new Image("/assets/textures/objects/cow/cowIdle.png"));
		list.put("cow1", new Image("/assets/textures/objects/cow/cow1.png"));
		list.put("cow2", new Image("/assets/textures/objects/cow/cow2.png"));
		list.put("cow3", new Image("/assets/textures/objects/cow/cow3.png"));
		list.put("cowHurtIdle", new Image("/assets/textures/objects/cow/cowHurtIdle.png"));
		list.put("cowHurt1", new Image("/assets/textures/objects/cow/cowHurt1.png"));
		list.put("cowHurt2", new Image("/assets/textures/objects/cow/cowHurt2.png"));
		list.put("cowHurt3", new Image("/assets/textures/objects/cow/cowHurt3.png"));
		
		list.put("air", new Image("/assets/textures/items/air.png"));
		list.put("cutwood", new Image("/assets/textures/items/cutwood.png"));
		list.put("smallrock", new Image("/assets/textures/items/smallrock.png"));
		list.put("twigbasket", new Image("/assets/textures/items/twigbasket.png"));
		list.put("woodlog", new Image("/assets/textures/items/woodlog.png"));
		list.put("crate", new Image("/assets/textures/items/crate.png"));
		list.put("craftingstation", new Image("/assets/textures/items/craftingstation.png"));
	
		list.put("sharprockIcon", new Image("/assets/textures/items/sharprock/icon.png"));
		list.put("sharprockIdle", new Image("/assets/textures/items/sharprock/idle.png"));
		list.put("sharprockRun", new Image("/assets/textures/items/sharprock/run.png"));
		
		list.put("sharpenedstickIcon", new Image("/assets/textures/items/sharpenedstick/icon.png"));
		list.put("sharpenedstickIdle", new Image("/assets/textures/items/sharpenedstick/idle.png"));
		list.put("sharpenedstickRun", new Image("/assets/textures/items/sharpenedstick/run.png"));
		list.put("sharpenedstickAttack1", new Image("/assets/textures/items/sharpenedstick/attack1.png"));
		list.put("sharpenedstickAttack2", new Image("/assets/textures/items/sharpenedstick/attack2.png"));
		list.put("sharpenedstickAttack3", new Image("/assets/textures/items/sharpenedstick/attack3.png"));
		list.put("sharpenedstickAttack4", new Image("/assets/textures/items/sharpenedstick/attack4.png"));
		list.put("sharpenedstickAttack5", new Image("/assets/textures/items/sharpenedstick/attack5.png"));
		
		list.put("basicaxeIcon", new Image("/assets/textures/items/basicaxe/icon.png"));
		list.put("basicaxeIdle", new Image("/assets/textures/items/basicaxe/idle.png"));
		list.put("basicaxeAttack1", new Image("/assets/textures/items/basicaxe/attacks1.png"));
		list.put("basicaxeAttack2", new Image("/assets/textures/items/basicaxe/attacks2.png"));
		list.put("basicaxeAttack3", new Image("/assets/textures/items/basicaxe/attacks3.png"));
		list.put("basicaxeAttack4", new Image("/assets/textures/items/basicaxe/attacks4.png"));
		list.put("basicaxeAttack5", new Image("/assets/textures/items/basicaxe/attacks5.png"));

		list.put("basicpickaxeIcon", new Image("/assets/textures/items/basicpickaxe/icon.png"));
		list.put("basicpickaxeIdle", new Image("/assets/textures/items/basicpickaxe/idle.png"));
		
		list.put("inventoryUI", new Image("/assets/textures/ui/inventory/basis.png"));
		list.put("crateUI", new Image("/assets/textures/ui/inventory/crate.png"));
		list.put("hotbarUI", new Image("/assets/textures/ui/inventory/hotbar.png"));
		
		list.put("stone", new Image("/assets/textures/tiles/stone.png"));
		list.put("grassBlock", new Image("/assets/textures/tiles/grassBlock.png"));
		list.put("dirt", new Image("/assets/textures/tiles/dirt.png"));
		list.put("water", new Image("/assets/textures/tiles/water.png"));
		list.put("grass", new Image("/assets/textures/tiles/grass1.png"));
		list.put("woodenplanks", new Image("/assets/textures/tiles/woodenplanks.png"));
		list.put("ironbrazier", new Image("/assets/textures/tiles/ironbrazier.png"));
		
		list.put("playerIdle", new Image("/assets/textures/player/rompIdle.png"));
		list.put("playerRun1", new Image("/assets/textures/player/romp1.png"));
		list.put("playerRun2", new Image("/assets/textures/player/romp2.png"));
		list.put("playerRun3", new Image("/assets/textures/player/romp3.png"));
		list.put("playerRun4", new Image("/assets/textures/player/romp4.png"));
		list.put("playerRun5", new Image("/assets/textures/player/romp5.png"));
		list.put("playerRun6", new Image("/assets/textures/player/romp6.png"));
		
		
		WDebug.log("[System]: Loaded in " + list.size() + " textures!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: Texture requested for name: " + name + " but wasn't found!");
			return null;
		}
	}
	
	private BufferedImage getImage(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
}
