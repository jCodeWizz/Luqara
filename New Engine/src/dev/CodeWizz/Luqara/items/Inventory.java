package dev.CodeWizz.Luqara.items;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.items.items.Air;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public abstract class Inventory {

	protected Slot[] slots;
	protected boolean open;

	public static List<Slot> allSlots = new CopyOnWriteArrayList<>();

	public Inventory(int size) {
		slots = new Slot[size];
		for (int i = 0; i < slots.length; i++) {
			slots[i] = new Slot(i + 5 * 32, 100);
			slots[i].setItem(new Air(1));
		}
	}
	

	
	public void render(GameContainer gc, Renderer r) {
		
	}
	
	public void loadFromSlots(Slot[] slots) {
		for(int i = 0; i < slots.length; i++) {
			this.slots[i].setItem(slots[i].getItem());
		}
	}
	
	public void open() {
		for (Slot slot : slots) {
			allSlots.add(slot);
		}
		open = true;
	}

	public void close() {
		open = false;
		for (Slot slot : slots) {
			allSlots.remove(slot);
		}
	}

	public ItemStack setItem(ItemStack item, int slot) {
		for (int i = 0; i < slots.length; i++) {
			slots[slot].setItem(item);
		}

		return slots[slot].getItem();
	}

	public boolean addItem(ItemStack item) {

		boolean invHasItem = false;

		for (int i = 0; i < slots.length; i++) {
			if (slots[i].getItem().getType() == item.getType()) {
				if (slots[i].getItem().size < slots[i].getItem().maxSize) {
					invHasItem = true;
				}
			}
		}

		if (!invHasItem || item.isUnstackable()) {
			for (int i = 0; i < slots.length; i++) {
				if (slots[i].getItem().getType() == Type.Air) {

					slots[i].setItem(item);
					return true;
				}
			}
		}

		for (int i = 0; i < slots.length; i++) {
			if (slots[i].getItem().getType() == item.getType()) {
				if (slots[i].getItem().getSize() + item.getSize() <= slots[i].getItem().getMaxSize()) {
					slots[i].getItem().setSize(slots[i].getItem().getSize() + item.getSize());
					return true;
				}
			}
		}

		for (int i = 0; i < slots.length; i++) {

			for (int j = 0; j < slots.length; j++) {
				if (slots[j].getItem().getType() == Type.Air) {
					slots[j].setItem(item);
					int d = item.maxSize - slots[i].getItem().size;
					slots[j].getItem().size = item.size - d;
					slots[i].getItem().setSize(slots[i].getItem().getMaxSize());

					return true;
				}
			}
		}

		return false;
	}

	public boolean removeItem(Type type, int amount) {
		if(checkForItem(type, amount)) {
			int a = amount;
			
			for(Slot slot : slots) {
				if(slot.getItem().getType() == type) {
					if(a >= slot.getItem().getSize()) {
						a -= slot.getItem().getSize();
						slot.setItem(new Air(1));
						if(a == 0)
							return true;
					} else {
						slot.getItem().setSize(slot.getItem().getSize() - a);
						return true;
					}
				}
			}
			
			
			
			return false;
		} else {
			return false;
		}
		
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 1920, 1080);
	}

	public boolean checkForItem(Type type, int amount) {
		int a = 0;

		for (Slot slot : slots) {
			if (slot.getItem().getType() == type) {
				a += slot.getItem().getSize();
			}
		}

		if (a < amount) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isOpen() {
		return open;
	}

	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}
	
}
