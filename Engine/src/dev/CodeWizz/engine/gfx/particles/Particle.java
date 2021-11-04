package dev.CodeWizz.engine.gfx.particles;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Particle {

	private ParticleType type;
	private float x, y, velX, velY;
	private int decayTime;
	private Image image;
	private int size;
	private int color;
	private final float gravity = 0.1f;
	private boolean falling = true;
	private int timer = 0;
	

	public static List<Particle> list = new CopyOnWriteArrayList<>();

	public Particle(float x, float y, int color, int size, int decayTime, int vel) {
		type = ParticleType.Color;

		this.x = x;
		this.y = y;
		
		Random r = new Random();

		this.decayTime = decayTime;
		this.color = color;
		this.size = size;
		
		//result = true || false ? x : y;
		
		velX = r.nextBoolean() ? (r.nextInt(vel) + r.nextFloat() / 2) : -(r.nextInt(vel) + r.nextFloat() / 2);
		velY = -(r.nextInt(vel) + r.nextFloat() / 2);
		
		
	}

	public static void add(Particle p) {
		list.add(p);
	}

	public Particle(float x, float y, Image image, int decayTime, int vel) {
		type = ParticleType.Image;

		this.x = x;
		this.y = y;
		
		Random r = new Random();

		this.decayTime = decayTime;
		this.image = image;
		
		//result = true || false ? x : y;
		
		velX = r.nextBoolean() ? (r.nextInt(vel) + r.nextFloat() / 2) : -(r.nextInt(vel) + r.nextFloat() / 2);
		velY = -(r.nextInt(vel) + r.nextFloat() / 2);
		
		image.setAlpha(false);
		image.setLightBlock(Light.NONE);
		
	}
	
	public void update(GameContainer gc) {

		
		
		if(falling) {
			velY += gravity;
		}
		
		
		x += velX;
		y += velY;
		
		for (GameObject object : gc.handler.object) {
			if (object.getBounds().intersects(getBounds()) && object.getId() == ID.Box) {
				falling = false;
				velY = 0;
				velX = 0;
			}
		}
		
		if(timer < decayTime)
			timer++;
		else
			list.remove(this);
		
	}

	public void render(GameContainer gc, Renderer r) {
		if(type == ParticleType.Color) {
			r.fillRect((int)x, (int)y, size, size, color, Light.NONE);
		} else if(type == ParticleType.Image) {
			r.drawImage(image, (int)x, (int)y);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}
}
