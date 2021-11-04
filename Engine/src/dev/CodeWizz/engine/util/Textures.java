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
		System.out.println("[System]: Loading textures...");
		
		System.out.println("[System]: Loaded in " + list.size() + " textures!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			System.out.println("[ERROR]: Texture requested for name: " + name + " but wasn't found!");
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
