package dev.CodeWizz.Luqara.input;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Slot;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.objects.FallingTile;
import dev.CodeWizz.Luqara.objects.Tree;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.Luqara.world.tiles.air;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class MouseInput {

	public static Slot mouseSlot;

	public MouseInput() {
		mouseSlot = new Slot(0, 0);
	}

	public void update(GameContainer gc) {

		int x = gc.getInput().getMouseX();
		int y = gc.getInput().getMouseY();
		
		mouseSlot.setX(x - gc.camera.getX() - 24);
		mouseSlot.setY(y - gc.camera.getY() - 24);
		
		if (gc.getInput().isButtonDown(1)) {
			
			gc.handler.addObject(new FallingTile(x, y, "dirt"));

			if (gc.getPlayer().getInv().isOpen()) {
				invClick(x - gc.camera.getX(), y - gc.camera.getY());
			} else {
				
				gc.getPlayer().getCurrentItem().click(gc, x, y, true, gc.getPlayer().getCurrentItem());
				
				for (Tile tile : gc.handler.tile) {
					if (tile.getBounds()
							.intersects(new Rectangle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1, 1))
							&& tile.getId() == TileID.Solid) {
						for (int i = 0; i < tile.getChunk().tiles.length; i++) {
							for (int j = 0; j < tile.getChunk().tiles[i].length; j++) {
								if (tile.getChunk().tiles[i][j].getX() == tile.getX()
										&& tile.getChunk().tiles[i][j].getY() == tile.getY()) {
									tile.getChunk().tiles[i][j] = new air(tile.getX(), tile.getY(), tile.getChunk());
									tile.getChunk().setUpdate(true);
								}
							}
						}
					}
				}
				for(GameObject object : gc.handler.object) {
					if(object.getId() == ID.Tree) {
						if(object.getBounds().intersects(new Rectangle(x, y, 1, 1))) {
							((Tree) object).hit(gc);
						}
					}
				}
			}
		}
	}
	
	private void invClick(int x, int y) {
		for (Slot slots : Inventory.allSlots) {
			if(!slots.equals(mouseSlot)) {
				// if (new Rectangle(x, y, 1,
				// 1).intersects(gc.getPlayer().getInv().getBounds())) {

				if (new Rectangle(x, y, 1, 1).intersects(slots.getBounds())) {
					if (slots.getItem().getType() == Type.Air) {
						if (slots.setItem(mouseSlot.getItem())) {
							mouseSlot.clear();
						}

					} else {
						if (slots.getItem().getType() == mouseSlot.getItem().getType()) {
							if (slots.getItem().getSize() + mouseSlot.getItem().getSize() <= slots.getItem()
									.getMaxSize()) {
								slots.getItem().setSize(slots.getItem().getSize() + mouseSlot.getItem().getSize());
								mouseSlot.clear();
							} else {
								int d = slots.getItem().getMaxSize() - slots.getItem().getSize();
								if (d < 0)
									d *= -1;
								slots.getItem().setSize(slots.getItem().getMaxSize());
								mouseSlot.getItem().setSize(mouseSlot.getItem().getSize() - d);
							}
						} else {
							ItemStack j = slots.getItem();

							if (slots.setItem(mouseSlot.getItem())) {
								mouseSlot.setItem(j);

							}
						}
					}
					// }
				}
			}
		}
	}
}
