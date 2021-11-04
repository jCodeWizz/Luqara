package dev.CodeWizz.engine.util;

import java.util.HashMap;

import dev.CodeWizz.engine.sfx.SoundClip;

public class Sounds {
	
	private static HashMap<String, SoundClip> list = new HashMap<>();

	public void load() {
		WDebug.log("[System]: Loading sounds...");
		
		list.put("balrupsHit", new SoundClip("/assets/sounds/objects/balrups/hit.wav"));
		list.put("treeHit", new SoundClip("/assets/sounds/objects/tree/hit.wav"));
		list.put("itemPickup", new SoundClip("/assets/sounds/player/pickup.wav"));
		
		WDebug.log("[System]: Loaded in " + list.size() + " sounds!");
		setupVolumes();
	}
	
	public void setupVolumes() {
		WDebug.log("[System]: Setting up volumes now!");
		
		WDebug.log("[System]: Set up volumes succesfully!");
	}
	
	public static SoundClip get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: Sound requested for name: " + name + " but wasn't found!");
			return null;
		}
	}
}
