package dev.CodeWizz.Luqara.objects;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;

public class Balrups extends GameObject {

	private Animation flyAnim;
	private Animation flyHurtAnim;

	private final float speed = 1.5f;
	
	private boolean inRange;
	private boolean attacking;
	private boolean shouldLower;
	
	
	private int counter;
	
	public Balrups(float x, float y) {
		super(x, y);
	
		this.id = ID.Balrups;
		this.health = 10;
		
		w = 16;
		h = 16;
		
		this.hasGravity = true;
		this.hasCollision = true;
		
		this.tileCollisionID.add(TileID.Solid);
		this.tileCollisionID.add(TileID.Water);
		
		maxVelX = 3;
		maxVelY = 15;
		
		gravity = 0.3f;
		
		flyAnim = new Animation(6, Textures.get("balrups1"), Textures.get("balrups2"), Textures.get("balrups3"), Textures.get("balrups4"));
		flyHurtAnim = new Animation(6, Textures.get("balrupsHurt1"), Textures.get("balrupsHurt2"), Textures.get("balrupsHurt3"), Textures.get("balrupsHurt4"));
	}
	
	@Override
	public void damage(GameContainer gc, float damage) {
		super.damage(gc, damage);
		bloodParticles((int)x+8, (int)y + 8);
	}
	
	@Override
	public void tick(GameContainer gc) {
		super.tick(gc);
		flyAnim.tick();
		flyHurtAnim.tick();
		
		x+=velX;
		
		if (attacking)
			y += velY;
		else
			velY = 0;

		if (shouldLower) {
			y += 3;
			shouldLower = false;
		}

		if (isTouching(gc, new Rectangle((int) x + 8, (int) y, 1, 16 * 5))) {
			y -= 2.5;
		} else {
			shouldLower = true;
		}
		
		
		if (x - 32 < gc.getPlayer().getX() && x + 32 > gc.getPlayer().getX()) {
			inRange = true;
		} else {
			inRange = false;
		}

		if (inRange) {
			if (!attacking && velX == 0)
				velX = speed;
		} else {
			velX = x < gc.getPlayer().getX() ? speed : -speed;
		}

		if (counter < 100)
			counter++;
		else {
			counter = 0;
			if (inRange)
				attack();
		}
		
	}
	
	private void attack() {
		attacking = true;
	}

	@Override
	public void collided(GameContainer gc, Tile tile) {
		attacking = false;
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		if(hurt) {
			r.drawImage(flyHurtAnim.getFrame(), (int)x, (int)y);
		} else {
			r.drawImage(flyAnim.getFrame(), (int)x, (int)y);
		}
		
		if(GameContainer._debug) {
			r.drawRect(new Rectangle((int) x + 8, (int) y, 1, 16 * 5), 0xffff0000);
		}
	}
}
