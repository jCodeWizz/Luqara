package dev.CodeWizz.engine.object;

import java.awt.Rectangle;
import java.util.ArrayList;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.particles.Particle;
import dev.CodeWizz.engine.util.WMath;

public abstract class GameObject {

	protected float x, y, w = 16, h = 16, velX, velY;
	protected ID id;
	protected float gravity = 0.2f, slide = 0.2f, bounce = 0.2f;
	protected float maxVelX = 5f, maxVelY = 7.5f;
	protected boolean falling, jumping;
	protected float health;
	
	protected boolean hurt;
	protected int hurtTime,  offsetHitboxes = 2;
	
	protected ArrayList<ID> gameObjectCollisionID = new ArrayList<>();
	protected ArrayList<TileID> tileCollisionID = new ArrayList<>();
	protected ArrayList<String> tags = new ArrayList<>();
	protected boolean canMove;

	protected boolean hasGravity, hasCollision, isSlippery, isBouncy;

	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void destroy(GameContainer gc) {
		gc.handler.removeObject(this);
	}
	
	public void die(GameContainer gc) {
		destroy(gc);
	}
	
	public void damage(GameContainer gc, float damage) {
		if(health - damage > 0) {
			health-=damage;
			hurt = true;
			hurtTime = 30;
		} else
			die(gc);
	}
	
	public void bloodParticles(int x, int y) {
		Particle.add(new Particle(x, y, 0xff913f3f, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffc96161, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xff802222, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffab2e2e, 3, 60, 1));
		Particle.add(new Particle(x, y, 0xffa64141, 3, 60, 1));
	}
	
	public void collided(GameContainer gc, GameObject object) {
		
	}
	
	public boolean hasTag(String tag) {
		if(tags.contains(tag))
			return true;
		
		return false;
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		if(tags.remove(tag)) {
			return true;
		}
		
		return false;
	}
	
	public void collided(GameContainer gc, Tile tile) {
		
	}

	public void tick(GameContainer gc) {
		if(hurtTime > 0) {
			hurtTime--;
		} else {
			hurt = false;
		}
		
		if(canMove) {
			if (hasGravity && (falling || jumping) && velY < maxVelY) {
				velY += gravity;
			}

			if (!gameObjectCollisionID.isEmpty() && hasCollision) {
				for (GameObject object : gc.handler.object) {
					if (!object.equals(this) && gameObjectCollisionID.contains(object.id)) {
						if(getBoundsBottom().intersects(object.getBounds())) {
							velY = 0;
							falling = false;
							jumping = false;
							y = object.getY() - h;
							collided(gc, object);
						} else {
							falling = true;
						}
						
						if(getBoundsTop().intersects(object.getBounds())) {
							velY = 0;
							falling = false;
							jumping = false;
							y = object.getY() + (int) object.getBounds().getHeight();
							collided(gc, object);

						}
						
						if(getBoundsLeft().intersects(object.getBounds())) {
							velX = 0;
							x = object.getX() + (int) object.getBounds().getWidth();
							collided(gc, object);
						}
						
						if(getBoundsRight().intersects(object.getBounds())) {
							velX = 0;
							x = object.getX() + w;
							collided(gc, object);
						}
					}
				}
			}
			
			if(hasCollision && !tileCollisionID.isEmpty()) {
				for(Tile tile : gc.handler.tile) {
					if(tileCollisionID.contains(tile.getId())) {
						if(getBoundsBottom().intersects(tile.getBounds())) {
							velY = 0;
							falling = false;
							jumping = false;
							y = tile.getY() - h;
							collided(gc, tile);
						} else {
							falling = true;
						}
						
						if(getBoundsTop().intersects(tile.getBounds())) {
							velY = 0;
							falling = false;
							jumping = false;
							y = tile.getY() + (int) tile.getBounds().getHeight();
							collided(gc, tile);
						}
						
						if(getBoundsLeft().intersects(tile.getBounds())) {
							velX = 0;
							x = tile.getX() + 16;
							collided(gc, tile);
						}
						
						if(getBoundsRight().intersects(tile.getBounds())) {
							velX = 0;
							x = tile.getX() - w;
							collided(gc, tile);
						}
					}
				}
			}
		}
		

		WMath.clamb(velX, maxVelX, -maxVelX);
		WMath.clamb(velY, maxVelY, -maxVelY);
	}
	
	public boolean isTouching(GameContainer gc, Rectangle rec) {
		for(Tile tile : gc.handler.tile) {
			if(tileCollisionID.contains(tile.getId()) && rec.getBounds().intersects(tile.getBounds())) {
				return true;
			}
		}
		
		return false;
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+offsetHitboxes, (int) (w/2), (int) h - offsetHitboxes*2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int)x + (int) (w/2), (int)y+offsetHitboxes, (int) (w/2), (int) h - offsetHitboxes*2);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int)x + offsetHitboxes, (int)y + (int) (h/2), (int) w - offsetHitboxes*2, (int) (h/2));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int)x + offsetHitboxes, (int)y, (int) w - offsetHitboxes*2, (int) (h/2));
	}

	public abstract void render(GameContainer gc, Renderer r);

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

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public boolean hasGravity() {
		return hasGravity;
	}

	public void setHasGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
	}

	public boolean hasCollision() {
		return hasCollision;
	}

	public void setHasCollision(boolean hasCollision) {
		this.hasCollision = hasCollision;
	}

	public boolean isSlippery() {
		return isSlippery;
	}

	public void setSlippery(boolean isSlippery) {
		this.isSlippery = isSlippery;
	}

	public boolean isBouncy() {
		return isBouncy;
	}

	public void setBouncy(boolean isBouncy) {
		this.isBouncy = isBouncy;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getMaxVelX() {
		return maxVelX;
	}

	public void setMaxVelX(float maxVelX) {
		this.maxVelX = maxVelX;
	}

	public float getMaxVelY() {
		return maxVelY;
	}

	public void setMaxVelY(float maxVelY) {
		this.maxVelY = maxVelY;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public ArrayList<ID> getGameObjectCollisionID() {
		return gameObjectCollisionID;
	}

	public void setGameObjectCollisionID(ArrayList<ID> gameObjectCollisionID) {
		this.gameObjectCollisionID = gameObjectCollisionID;
	}

	public ArrayList<TileID> getTileCollisionID() {
		return tileCollisionID;
	}

	public void setTileCollisionID(ArrayList<TileID> tileCollisionID) {
		this.tileCollisionID = tileCollisionID;
	}

	public boolean isHasGravity() {
		return hasGravity;
	}

	public boolean isHasCollision() {
		return hasCollision;
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
}
