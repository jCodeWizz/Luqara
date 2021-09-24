package dev.CodeWizz.Luqara;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.Item;
import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.inventories.PlayerInventory;
import dev.CodeWizz.Luqara.items.items.BasicAxe;
import dev.CodeWizz.Luqara.items.items.BasicPickaxe;
import dev.CodeWizz.Luqara.items.items.CrateItem;
import dev.CodeWizz.Luqara.items.items.CutWood;
import dev.CodeWizz.Luqara.items.items.SharpRock;
import dev.CodeWizz.Luqara.items.items.SharpenedStick;
import dev.CodeWizz.Luqara.items.items.SmallRock;
import dev.CodeWizz.Luqara.items.items.Tool;
import dev.CodeWizz.Luqara.items.items.TwigBasket;
import dev.CodeWizz.Luqara.items.items.WoodLog;
import dev.CodeWizz.Luqara.util.IAction;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.WMath;

public class Player {

	private float _acc = 0.5f;
	private float _dcc = 0.5f;

	private float _accj = 0.1f;
	private float _dccj = 0.1f;
	private final float maxMovementSpeed = 1.5f;

	private int slot = 0;

	private boolean hitting = false;

	static GameObject obj = null;

	private PlayerInventory inv;

	private Animation walkAnim;

	private float x, y, w, h, velX, velY;
	private boolean jumping, falling;
	private final int maxVelY = 15;
	private float gravity = 0.2f;

	private Light light;

	private GameContainer gc;

	public Player(GameContainer gc) {
		this.gc = gc;

		this.x = 100;

		w = 16;
		h = 32;

		light = new Light(150, 0xffe6bc05);

		walkAnim = new Animation(7, Textures.get("playerRun1"), Textures.get("playerRun2"), Textures.get("playerRun3"),
				Textures.get("playerRun4"), Textures.get("playerRun5"), Textures.get("playerRun6"));

		inv = new PlayerInventory(gc, 16);

		inv.addItem(new WoodLog(1));
		inv.addItem(new BasicAxe(1));
		inv.addItem(new BasicPickaxe(1));
		inv.addItem(new CrateItem(1));
		inv.addItem(new CutWood(1));
		inv.addItem(new SharpenedStick(1));
		inv.addItem(new SharpRock(1));
		inv.addItem(new SmallRock(1));
		inv.addItem(new TwigBasket(1));

	}

	public void update(GameContainer gc) {

		// GRAVITY
		if (falling || jumping) {
			velY += gravity;
			if (velY > maxVelY) {
				velY = maxVelY;
			}
		}

		walkAnim.tick();

		// MOVEMENT
		velX();

		// COLLISION
		collisionX();
		collisionY();

		// ITEM CHECK
		pickupItems();

		// JUMPING
		if (gc.getInput().isKey(KeyEvent.VK_SPACE) && !jumping) {
			jump();
		}

		// ITEM HITTING ANIMATION
		if (hitting) {
			((Tool) getCurrentItem()).getAttackAnim().tick();
		}

		if(getCurrentItem() instanceof Tool) {
			if (((Tool) inv.getCurrentSlot().getItem()).getAttackAnim().hasCycled()) {
				hitting = false;
			}
		}

		velX = WMath.clamb(velX, 2.5f, -2.5f);
		velY = WMath.clamb(velY, 10f, -10f);
	}

	private void pickupItems() {
		for (Item item : Item.items) {
			if (this.getBounds().intersects(item.getBounds()) && !item.onCooldown()) {
				if (inv.addItem(item.getItems())) {
					Item.remove(item);
				}
			}
		}
	}

	public void hit() {

		hitting = true;
		((Tool) getCurrentItem()).getAttackAnim().reset();
	}

	public void renderUI(GameContainer gc, Renderer r) {
		inv.render(gc, r);
	}

	public void render(GameContainer gc, Renderer r) {
		if (velX != 0) {
			r.drawImage(walkAnim.getFrame(), (int) x - 8, (int) y - 8);
		} else
			r.drawImage(Textures.get("playerIdle"), (int) x - 8, (int) y - 8);

		if (getCurrentItem() instanceof Tool && !hitting) {
			r.drawImage(inv.getCurrentSlot().getItem().getIdleTexture(), (int) x - 24, (int) y - 8);
		} else if(!hitting) {
			r.drawImage(inv.getCurrentSlot().getItem().getIdleTexture(), (int) x - 8, (int) y - 8);
		}

		if(getCurrentItem() instanceof Tool && hitting) {
			r.drawImage(((Tool)getCurrentItem()).getAttackAnim().getFrame(), (int)x - 24, (int)y - 8);
		}

		if (GameContainer._debug) {
			r.drawRect(getBounds(), 0xffff0000);
		}

		r.drawLight(light, (int) x, (int) y, true);

	}

	private void jump() {
		velY = -3;
		if (!GameContainer._debug)
			jumping = true;
	}

	public void startAction() {
		float distance = Float.MAX_VALUE;

		for (GameObject object : gc.handler.object) {
			if (object instanceof IAction) {
				float d = WMath.distance((int) x, (int) y, (int) object.getX() + 16, (int) object.getY());
				if (d < distance && d < 48) {
					distance = d;
					obj = object;
				}
			}
		}

		((IAction) obj).startAction(gc);

		if (obj != null) {
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("STARTED!");
					((IAction) obj).endAction(gc);
					// close the thread
					t.cancel();
				}
			}, 2000);
		}
	}

	public ItemStack getCurrentItem() {
		if (slot == 0) {
			return inv.getCurrentSlot().getItem();
		} else if (slot == 2) {
			return inv.getToolSlot().getItem();
		} else {
			return inv.getWeaponSlot().getItem();
		}
	}

	public void setCurrentItem(ItemStack item) {
		if (slot == 0) {
			inv.getCurrentSlot().setItem(item);
		} else if (slot == 2) {
			inv.getToolSlot().setItem(item);
		} else {
			inv.getWeaponSlot().setItem(item);
		}
	}

	private void velX() {

		if (velX <= 0.25f && velX >= -0.25f) {
			velX = 0;
		}

		if (jumping) {
			if (gc.getInput().isKey(KeyEvent.VK_D)) {
				if (!gc.getInput().isKey(KeyEvent.VK_A) && velX < maxMovementSpeed) {
					velX += _accj;
				} else {
					if (velX > 0)
						velX -= _dccj;
					else if (velX < 0)
						velX += _dccj;
				}
			} else if (gc.getInput().isKey(KeyEvent.VK_A)) {
				if (!gc.getInput().isKey(KeyEvent.VK_D) && velX > -maxMovementSpeed) {
					velX -= _accj;
				} else {
					if (velX > 0)
						velX -= _dccj;
					else if (velX < 0)
						velX += _dccj;
				}
			} else if (!gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isKey(KeyEvent.VK_A)) {

				if (velX > 0)
					velX -= _dccj;
				else if (velX < 0)
					velX += _dccj;

			}
		} else {
			if (gc.getInput().isKey(KeyEvent.VK_D)) {
				if (!gc.getInput().isKey(KeyEvent.VK_A) && velX < maxMovementSpeed) {
					velX += _acc;
				} else {
					if (velX > 0)
						velX -= _dcc;
					else if (velX < 0)
						velX += _dcc;
				}
			} else if (gc.getInput().isKey(KeyEvent.VK_A)) {
				if (!gc.getInput().isKey(KeyEvent.VK_D) && velX > -maxMovementSpeed) {
					velX -= _acc;
				} else {
					if (velX > 0)
						velX -= _dcc;
					else if (velX < 0)
						velX += _dcc;
				}
			} else if (!gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isKey(KeyEvent.VK_A)) {

				if (velX > 0)
					velX -= _dcc;
				else if (velX < 0)
					velX += _dcc;

			}
		}

		if (velX > 0 && velX > maxMovementSpeed)
			velX -= 0.0005f;
		else if (velX < 0 && velX < -maxMovementSpeed)
			velX += 0.0005f;

	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 2, (int) (w / 2), (int) h - 4);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + (int) (w / 2), (int) y + 2, (int) (w / 2), (int) h - 4);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + 2, (int) y + (int) (h / 2), (int) w - 4, (int) (h / 2));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + 2, (int) y, (int) w - 4, (int) (h / 2));
	}

	private void collisionY() {
		boolean collided = false;

		if (velY > 0) {
			for (int i = 0; i < velY; i++) {
				for (Tile object : gc.handler.tile) {
					if (object.getId() == TileID.Solid) {
						if (new Rectangle((int) x, (int) y + 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					velY = 0;
					falling = false;
					jumping = false;
					continue;
				} else {
					falling = true;
					y++;
				}
			}
		} else if (velY < 0) {
			for (int i = 0; i > velY; i--) {
				for (Tile object : gc.handler.tile) {
					if (object.getId() == TileID.Solid) {
						if (new Rectangle((int) x, (int) y - 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					falling = false;
					jumping = false;
					velY = 0;
					continue;
				} else {
					falling = true;
					y--;
				}
			}
		} else {
			for (Tile object : gc.handler.tile) {
				if (object.getId() == TileID.Solid) {
					if (new Rectangle((int) x, (int) y + 1, (int) w, (int) h).intersects(object.getBounds())) {
						collided = true;
						continue;
					}
				}
			}

			if (collided) {
				falling = false;
				jumping = false;
				velY = 0;
			} else {
				falling = true;
			}
		}
	}

	private void collisionX() {
		boolean collided = false;

		if (velX > 0) {
			for (int i = 0; i < velX; i++) {
				for (Tile object : gc.handler.tile) {
					if (object.getId() == TileID.Solid) {
						if (new Rectangle((int) x + 1, (int) y, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					velX = 0;
					continue;
				} else {
					x++;
				}
			}
		} else if (velX < 0) {
			for (int i = 0; i > velX; i--) {
				for (Tile object : gc.handler.tile) {
					if (object.getId() == TileID.Solid) {
						if (new Rectangle((int) x - 1, (int) y, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					velX = 0;
					continue;
				} else {
					x--;
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) w, (int) h);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public Inventory getInv() {
		return inv;
	}

	public boolean isHitting() {
		return hitting;
	}

	public void setHitting(boolean hitting) {
		this.hitting = hitting;
	}

}
