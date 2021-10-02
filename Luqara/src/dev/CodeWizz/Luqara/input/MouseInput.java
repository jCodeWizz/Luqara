package dev.CodeWizz.Luqara.input;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.CodeWizz.Luqara.HUD;
import dev.CodeWizz.Luqara.Luqara;
import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Slot;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.items.items.Air;
import dev.CodeWizz.Luqara.items.items.ITilePlacable;
import dev.CodeWizz.Luqara.objects.Cow;
import dev.CodeWizz.Luqara.objects.FallingTile;
import dev.CodeWizz.Luqara.objects.Tree;
import dev.CodeWizz.Luqara.world.tiles.ITileEntity;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.Luqara.world.tiles.grassBlock;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class MouseInput {

	public static Slot mouseSlot;
	public static Tile mouseTile;

	public MouseInput() {
		mouseSlot = new Slot(0, 0);
	}

	public void update(GameContainer gc) {

		int x = gc.getInput().getMouseX();
		int y = gc.getInput().getMouseY();

		mouseSlot.setX(x - gc.camera.getX() - 24);
		mouseSlot.setY(y - gc.camera.getY() - 24);

		for (Tile tile : gc.handler.tile) {
			if (tile.getBounds().intersects(new Rectangle(x, y, 1, 1))) {
				mouseTile = tile;
			}
		}
		
		if(gc.getInput().isButtonDown(1)) {
			if(gc.getInput().isKey(KeyEvent.VK_SHIFT)) {
				Luqara.inst.getWorld().spawnEntity(gc, new Cow(x-16, y-16), false);
			} else if(gc.getInput().isKey(KeyEvent.VK_CONTROL)) {
				gc.handler.addObject(new FallingTile(x - 8, y - 8, new grassBlock(0, 0, 0, 0, null)));
			}
		}

		if (gc.getInput().isButtonDown(1)) {
			if (gc.getPlayer().getInv().isOpen()) {
				invClick(x - gc.camera.getX(), y - gc.camera.getY());
			} else {

				//
				// Luqara.inst.getWorld().spawnEntity(gc, new Cow(x-16, y-16), false);

				
				
				if (gc.getPlayer().getCurrentItem() instanceof ITilePlacable) {
					if (mouseTile.getId() != TileID.Solid) {
						((ITilePlacable) gc.getPlayer().getCurrentItem()).place(mouseTile);

						if (gc.getPlayer().getCurrentItem().getSize() > 1) {
							gc.getPlayer().getCurrentItem().setSize(gc.getPlayer().getCurrentItem().getSize() - 1);
						} else {
							gc.getPlayer().setCurrentItem(new Air(1));
						}
					}
				} else if(mouseTile instanceof ITileEntity) {
					if(!HUD.chat.isOpen() && !gc.getPlayer().isDoingAction()) {
						((ITileEntity) mouseTile).click(gc);
					}
				} else {
					gc.getPlayer().getCurrentItem().click(gc, x, y, true, gc.getPlayer().getCurrentItem());
				}

				for (GameObject object : gc.handler.object) {
					if (object.getId() == ID.Tree) {
						if (object.getBounds().intersects(new Rectangle(x, y, 1, 1))) {
							((Tree) object).hit(gc);
						}
					}
				}
			}
		}
	}

	private void invClick(int x, int y) {
		for (Slot slots : Inventory.allSlots) {
			if (!slots.equals(mouseSlot)) {
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
