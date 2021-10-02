package dev.CodeWizz.Luqara.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.CodeWizz.Luqara.Luqara;
import dev.CodeWizz.Luqara.items.Item;
import dev.CodeWizz.Luqara.items.items.WoodLog;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.gfx.particles.Particle;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.NormalMaps;
import dev.CodeWizz.engine.util.Sounds;
import dev.CodeWizz.engine.util.Textures;

public class Tree extends GameObject {

	private Image image1;
	private Image image2;
	private float shake;
	private Random r;

	private int height;
	private int direction;
	private int length;
	private int stump = 2;
	private boolean hasStump = false;
	private boolean nest = false;
	private boolean shaking = false;

	private int counter = 0;

	public Tree(float x, float y) {
		super(x, y);

		this.health = 10;

		this.id = ID.Tree;

		setupVars();
		image1 = setupTexture();
		image2 = setupTextureLeaves();
	}

	@Override
	public void tick(GameContainer gc) {
		super.tick(gc);

		if (shaking) {
			if (counter < 15) {
				counter++;
				shake = r.nextInt(8) - 4;
			} else {
				shake = 0;
				counter = 0;
				shaking = false;
			}
		}
	}

	@Override
	public void die(GameContainer gc) {

		// spawn items
		for (int i = 0; i < height; i++) {
			Item.add(new Item(x - 24 + r.nextInt(32) - 16, y - height * 16 - length * 4, new WoodLog(2)));
		}

		// spawn leave particles
		for (int i = 0; i < 2; i++) {
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff3a9c33, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff7aca2d, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff1c6739, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff267c2d, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff3a9c33, 2, 120, 1));
		}

		// spawn wood particles
		for (int i = 0; i < height; i++) {
			Particle.add(
					new Particle((int) x - 24, (int) y - height * 16 - length * 4 + i * 16, 0xff5c422d, 2, 120, 1));
			Particle.add(
					new Particle((int) x - 24, (int) y - height * 16 - length * 4 + i * 16, 0xff705534, 2, 120, 1));
			Particle.add(
					new Particle((int) x - 24, (int) y - height * 16 - length * 4 + i * 16, 0xff533c2b, 2, 120, 1));
		}

		if (nest) {
			for (int i = -1; i < 2; i++) {
				Luqara.inst.getWorld().spawnEntity(gc, new Balrups((int) x - 24 + 16 * i, (int) y - height * 16 - length * 4), true);
			}
		}

		super.die(gc);
	}

	public void hit(GameContainer gc) {
		if (!shaking) {
			shaking = true;

			damage(gc, 1);
			
			Sounds.get("treeHit").play();

			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff3a9c33, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff7aca2d, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff1c6739, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff267c2d, 2, 120, 1));
			Particle.add(new Particle((int) x - 24, (int) y - height * 16 - length * 4, 0xff3a9c33, 2, 120, 1));

			if (nest) {
				if (r.nextInt(10) == 3) {
					gc.handler.addObject(new Balrups((int) x - 24, (int) y - height * 16 - length * 4));
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x - 32, (int) y - (int) h + 16, (int) w - 64, (int) h);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(image1, (int) (x - 64 + shake), (int) y - (int) h + 16);
		r.drawImage(image2, (int) (x - 64 + shake), (int) y - (int) h + 16);

		if (GameContainer._debug)
			r.drawRect(getBounds(), 0xffff0000);
	}

	private void addBirdNest() {
		if (r.nextInt(3) == 1) {
			nest = true;
		}
	}

	private void setupVars() {
		r = new Random();

		height = r.nextInt(7 - 3) + 3;
		direction = r.nextInt(3 - 1) + 1;
		length = (r.nextInt(0 + 3) + 2);

		if (direction == 2)
			direction = -1;

		if (height > 4 && length < 3)
			length = 3;

		if (length > height)
			length = height - 1;

		if (r.nextInt(5) >= 1) {
			if (height > 3) {
				stump = r.nextInt(height - 3) + 3;
				hasStump = true;
				addBirdNest();
			} else if (height == 3) {
				stump = 3;
				hasStump = true;
				addBirdNest();
			}
		}

		w = 80;
		h = (length * 16 + height * 16);
	}

	private Image setupTextureLeaves() {
		BufferedImage leaves = new BufferedImage((int) w, (int) length * 16, BufferedImage.TYPE_INT_ARGB);
		BufferedImage leavesN = new BufferedImage((int) w, (int) length * 16, BufferedImage.TYPE_INT_ARGB);
		Graphics gl = leaves.createGraphics();
		Graphics lN = leavesN.createGraphics();

		if (length == 2) {
			if (direction == 1) {
				gl.drawImage(Textures.leaves, 32, 0, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 32, 0, null);
				gl.drawImage(Textures.leaves, 16, 0, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 16, 0, null);
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), 16 * i, 16, null);
				}
			} else {
				gl.drawImage(Textures.leaves, 48, 0, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 48, 0, null);
				gl.drawImage(Textures.leaves, 32, 0, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 32, 0, null);
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16 + 16, 16, null);
				}
			}
		} else if (length == 3) {
			if (direction == 1) {
				for (int i = 0; i < 3; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 0, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16 + 16, 0, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16, 16, null);
				}
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 32, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16 + 16, 32, null);
				}

				gl.drawImage(Textures.leaves, 48, 48, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 48, 48, null);
			} else {
				for (int i = 0; i < 3; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 0, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16 + 16, 0, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16, 16, null);
				}
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16, 32, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i * 16, 32, null);
				}
				gl.drawImage(Textures.leaves, 16, 48, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 16, 48, null);
			}
		} else if (length == 4) {
			if (direction == 1) {
				for (int i = 0; i < 3; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 0, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16 + 16, 0, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16, 16, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 32, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16, 32, null);
				}
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 48, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16 + 16, 48, null);
				}

				gl.drawImage(Textures.leaves, 48, 64, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 48, 64, null);

			} else {
				for (int i = 0; i < 3; i++) {
					gl.drawImage(Textures.leaves, i * 16 + 16, 0, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16 + 16, 0, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 16, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16, 16, null);
				}
				for (int i = 0; i < 5; i++) {
					gl.drawImage(Textures.leaves, i * 16, 32, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16, 32, null);
				}
				for (int i = 0; i < 4; i++) {
					gl.drawImage(Textures.leaves, i * 16, 48, 16, 16, null);
					lN.drawImage(NormalMaps.get("leaves").getImage(), i*16, 48, null);
				}
				gl.drawImage(Textures.leaves, 16, 64, 16, 16, null);
				lN.drawImage(NormalMaps.get("leaves").getImage(), 16, 64, null);
			}
		}
		Image image = new Image(leaves);
		image.setLightBlock(Light.FULL);
		image.setNormalMap(new Image(leavesN));
		return image;
	}

	private Image setupTexture() {
		BufferedImage texture = new BufferedImage((int) w, (int) h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = texture.createGraphics();

		for (int i = 0; i < height + 1; i++) {
			if (direction == 1) {
				g.drawImage(Textures.log, 32, (int) texture.getHeight() - 16 * i - 16, 16, 16, null);
			} else {
				g.drawImage(Textures.log, 32 + 16, (int) texture.getHeight() - 16 * i - 16, -16, 16, null);
			}
		}

		if (height > 2) {
			if (hasStump && !nest) {
				if (direction == 1) {
					g.drawImage(Textures.stump, (int) 32 - 16 * direction, (int) texture.getHeight() - stump * 16, 32,
							16, null);
				} else {
					g.drawImage(Textures.stump, (int) 32 - 16 * direction - 16 + 32,
							(int) texture.getHeight() - stump * 16, -32, 16, null);
				}
			} else if (nest) {
				if (direction == 1) {
					g.drawImage(Textures.nest, (int) 32 - 16 * direction, (int) texture.getHeight() - stump * 16, 32,
							32, null);
				} else {
					g.drawImage(Textures.nest, (int) 32 - 16 * direction - 16 + 32,
							(int) texture.getHeight() - stump * 16, -32, 32, null);
				}
			}
		}

		return new Image(texture);
	}
}
