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
		for(Slot slot : this.slots) {
			slot.render(gc, r);
		}
	}
	
	public void clear() {
		for(Slot slot : slots) {
			slot.clear();
		}
	}
	
	public void loadFromSlots(Slot[] slots) {
		for(int i = 0; i < slots.length; i++) {
			this.slots[i].setItem(slots[i].getItem());
		}
	}
	
	public void open(GameContainer gc) {
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

	public int addItem(ItemStack item) {
		int leftover = item.size;
		
			boolean invHasItem = hasSpaceForItem(item.type);
			
			if (!invHasItem || item.isUnstackable() && !isFull()) {
				for (Slot slot : slots) {
					if (slot.getItem().getType() == Type.Air) {
						slot.setItem(item);
						return 0;
					}
				}
			}
			
			for(Slot slot : slots) {
				if(slot.getItem().getType() == item.type) {
					if(slot.getItem().size + item.size <= item.maxSize) {
						slot.getItem().setSize(slot.getItem().getSize() + item.size);
						return 0;
					}
				}
			}
			
			for(Slot slot : slots) {
				if(slot.getItem().getType() == item.type && slot.getItem().getSize() < slot.getItem().getMaxSize()) {
					int a = item.maxSize - slot.getItem().getSize();
					slot.getItem().setSize(item.maxSize);
					item.setSize(a);
					return addItem(item);
					
				}
			}
		
		return leftover;
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

		return (a >= amount);
	}
	
	public boolean isFull() {
		for (Slot slot : slots) {
			if (slot.getItem().getType() == Type.Air) {
				return false;
			}
		}

		return true;
	}
	
	public boolean hasSpaceForItem(Type type) {
		for (Slot slot : slots) {
			if (slot.getItem().getType() == type) {
				if(slot.getItem().size < slot.getItem().maxSize) {
					return true;
				}
			}
		}
		
		return false;
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
