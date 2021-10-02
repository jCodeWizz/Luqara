package dev.CodeWizz.engine.util;

import java.util.HashMap;

import dev.CodeWizz.engine.sfx.SoundClip;

public class Sounds {
	
	private static HashMap<String, SoundClip> list = new HashMap<>();

	public void load() {
		System.out.println("[System]: Loading sounds...");
		
		list.put("balrupsHit", new SoundClip("/assets/sounds/objects/balrups/hit.wav"));
		list.put("treeHit", new SoundClip("/assets/sounds/objects/tree/hit.wav"));
		list.put("itemPickup", new SoundClip("/assets/sounds/player/pickup.wav"));
		
		System.out.println("[System]: Loaded in " + list.size() + " sounds!");
		System.out.println("[System]: Setting up volumes now!");
		setupVolumes();
	}
	
	public void setupVolumes() {
		
		
		System.out.println("[System]: Set up volumes succesfully!");
	}
	
	public static SoundClip get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			System.out.println("[ERROR]: Sound requested for name: " + name + " but wasn't found!");
			return null;
		}
	}
}
