package dev.CodeWizz.Luqara.objects;

import java.awt.Rectangle;
import java.util.Random;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.object.IRandomTickable;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.WMath;

public class Cow extends GameObject implements IRandomTickable{
	
	private Animation walkAnim;
	
	private Random r;
	private int destination;
	private int counter = 0;
	private float lastX = x;
	private int timer = 0;

	public Cow(float x, float y) {
		super(x, y);
		
		this.id = ID.Cow;
		this.health = 10;
		
		this.hasCollision = true;
		this.hasGravity = true;
		
		this.tileCollisionID.add(TileID.Solid);
		this.r = new Random();
		
		walkAnim = new Animation(5, Textures.get("cow1"), Textures.get("cow2"), Textures.get("cow3"));
		
		w = 32;
		h = 32;
		
		this.offsetHitboxes = 2;
		
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		x+=velX;
		y+=velY;
		
		if(timer > 0)
			timer--;
		
		if(destination == 0) {
			velX = 0;
		} else {
			
			if(destination > x)
				velX = 1;
			else if(destination < x)
				velX = -1;
			else
				destination = 0;
			
			
		}
		
		
		if(counter > 0)
			counter--;
		else {
			
			counter = 15;
			if(WMath.distance(x, lastX) < 1.5f && destination != 0) {
				destination = 0;
			}
			
			lastX = x;
		}
		
		
		for(Tile tile : gc.handler.tile) {
			if(tile.getBounds().intersects(new Rectangle((int)x-16, (int)y+16, 64, 2)) && velY <= 1.5f && !jumping && tile.getId() == TileID.Solid && velX != 0 && destination != 0) {
				jumping = true;
				velY = -3;
			}
		}
		
		super.tick(gc);
		
		walkAnim.tick();
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		if(velX != 0) {
			r.drawImage(walkAnim.getFrame(), (int)x, (int)y);
		} else {
			r.drawImage(Textures.get("cowIdle"), (int)x, (int)y);
		}
		
		
		
		if(GameContainer._hitboxes) {
			//r.drawRect(getBounds(), 0xffff0000);
			r.drawRect(getBoundsLeft(), 0xffff0000);
			r.drawRect(getBoundsRight(), 0xffffff00);
			r.drawRect(getBoundsTop(), 0xff00ff00);
			r.drawRect(getBoundsBottom(), 0xffff00ff);
		}
	}

	@Override
	public void trigger(GameContainer gc) {
		if(r.nextInt(2) == 1) {
			destination = 0;
		} else {
			destination = (int) ((r.nextInt(23 - 9) + 9) * 16);
			if(r.nextInt(2) == 1) {
				destination *=-1;
			}
			destination += x;
		}		
	}
}