package dev.CodeWizz.engine;

import java.awt.Rectangle;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.ImageRequest;
import dev.CodeWizz.engine.gfx.ImageTile;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.gfx.light.LightRequest;

public class Renderer {

	private int pW, pH;

	public int ambientColor = 0xffabccff;
	private int dayColor = 0xffabccff;

	private Font font;

	// Color for lights: 0xff407dd6

	// both colors = 0xff3b414a : very dark
	// both colors = 0xffabccff : day light : 171, 204, 255!

	private int camX, camY;

	private int[] p;
	private int[] zb;
	private int[] lm;
	private int[] lb;

	private int zDepth = 0;
	private boolean processing = false;

	private ArrayList<ImageRequest> imageRequests = new ArrayList<>();
	private ArrayList<LightRequest> lightRequests = new ArrayList<>();

	public Renderer(GameContainer gc) {
		pW = gc.getWidth();
		pH = gc.getHeight();
		p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		zb = new int[p.length];
		lm = new int[p.length];
		lb = new int[p.length];

		font = Font.DETAILED;
	}

	public void setPixel(int x, int y, int value) {
		int alpha = ((value >> 24) & 0xff);

		int index = x + y * pW;

		if ((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0) {
			return;
		}

		if (zb[index] > zDepth) {
			return;
		}

		zb[index] = zDepth;

		if (alpha == 255) {
			p[index] = value;
		} else {
			int pixelColor = p[index];

			int newRed = ((pixelColor >> 16) & 0xff)
					- (int) ((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255f));
			int newGreen = ((pixelColor >> 8) & 0xff)
					- (int) ((((pixelColor >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha / 255f));
			int newBlue = (pixelColor & 0xff) - (int) ((((pixelColor) & 0xff) - ((value) & 0xff)) * (alpha / 255f));

			p[index] = (newRed << 16 | newGreen << 8 | newBlue);
		}

	}

	public void drawText(String text, float offX, int offY, int size) {
		
		int offset = 0;
		int color = 0xffffffff;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			if (unicode == 6 && i < text.length() - 1) {
				if (text.charAt(i + 1) == 'e') {
					color = 0xff9e0c02;
					i++;
				} else if (text.charAt(i + 1) == 'f') {
					color = 0xffffffff;
					i++;
				} else if (text.charAt(i + 1) == 'c') {
					color = 0xffa63e37;
					i++;
				} else if (text.charAt(i + 1) == 'a') {
					color = 0xff55de4e;
					i++;
				} else if (text.charAt(i + 1) == 'y') {
					color = 0xffe6d32e;
					i++;
				} else if (text.charAt(i + 1) == 'b') {
					color = 0xff1820b8;
					i++;
				} else if (text.charAt(i + 1) == 'v') {
					color = 0xff00ccc9;
					i++;
				} else if (text.charAt(i + 1) == 'l') {
					color = 0xff16b0f7;
					i++;
				} else if (text.charAt(i + 1) == 'g') {
					color = 0xfff0a400;
					i++;
				} 
			} else {
				for (int y = 0; y < font.getFontImage().getH(); y++) {
					for (int x = 0; x < font.getWidths()[unicode]; x++) {
						if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
								+ y * font.getFontImage().getW()] == 0xffffffff) {
							for (int j = 0; j < size; j++) {
								for (int k = 0; k < size; k++) {
									setPixel(x * size + j + (int)offX + offset, y * size + k + offY, color);
								}
							}
						}
					}
				}

				offset += font.getWidths()[unicode] * size;
			}

		}
	}

	public void drawText(String text, int offX, int offY) {

		int offset = 0;
		int color = 0xffffffff;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			if (unicode == 6 && i < text.length() - 1) {
				if (text.charAt(i + 1) == 'e') {
					color = 0xff9e0c02;
					i++;
				} else if (text.charAt(i + 1) == 'f') {
					color = 0xffffffff;
					i++;
				} else if (text.charAt(i + 1) == 'c') {
					color = 0xffa63e37;
					i++;
				} else if (text.charAt(i + 1) == 'a') {
					color = 0xff55de4e;
					i++;
				} else if (text.charAt(i + 1) == 'y') {
					color = 0xffe6d32e;
					i++;
				} else if (text.charAt(i + 1) == 'b') {
					color = 0xff1820b8;
					i++;
				} else if (text.charAt(i + 1) == 'v') {
					color = 0xff00ccc9;
					i++;
				} else if (text.charAt(i + 1) == 'l') {
					color = 0xff16b0f7;
					i++;
				} else if (text.charAt(i + 1) == 'g') {
					color = 0xfff0a400;
					i++;
				} 
			} else {
				for (int y = 0; y < font.getFontImage().getH(); y++) {
					for (int x = 0; x < font.getWidths()[unicode]; x++) {
						if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
								+ y * font.getFontImage().getW()] == 0xffffffff) {
							setPixel(x + offX + offset, y + offY, color);
						}
					}
				}

				offset += font.getWidths()[unicode];
			}

		}
	}

	public void drawText(String text, int offX, int offY, int size, int color) {
	
		int offset = 0;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			for (int y = 0; y < font.getFontImage().getH(); y++) {
				for (int x = 0; x < font.getWidths()[unicode]; x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
							+ y * font.getFontImage().getW()] == 0xffffffff) {
						for (int j = 0; j < size; j++) {
							for (int k = 0; k < size; k++) {
								setPixel(x * size + j + offX + offset, y * size + k + offY, color);
							}
						}
					}
				}
			}

			offset += font.getWidths()[unicode] * size;

		}
	}

	public void drawText(String text, int offX, int offY, int color) {

		int offset = 0;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			for (int y = 0; y < font.getFontImage().getH(); y++) {
				for (int x = 0; x < font.getWidths()[unicode]; x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
							+ y * font.getFontImage().getW()] == 0xffffffff) {
						setPixel(x + offX + offset, y + offY, color);
					}
				}
			}

			offset += font.getWidths()[unicode];

		}
	}
	
	public void drawText(String text, int offX, int offY, int color, boolean a) {
		offX -= camX;
		offY -= camY;
		
		
		int offset = 0;
		int halfOffset = 0;

		if(!a) {
			for (int i = 0; i < text.length(); i++) {
				int unicode = text.codePointAt(i) - 32;

				for (int y = 0; y < font.getFontImage().getH(); y++) {
					for (int x = 0; x < font.getWidths()[unicode]; x++) {
						if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
								+ y * font.getFontImage().getW()] == 0xffffffff) {
							setPixel(x + offX + offset, y + offY, color);
						}
					}
				}

				offset += font.getWidths()[unicode];

			}
		} else {
			for(int i = 0; i < text.length(); i++) {
				int unicode = text.codePointAt(i) - 32;
				halfOffset += font.getWidths()[unicode];
			}
			
			halfOffset /= 2;
			
			for (int i = 0; i < text.length(); i++) {
				int unicode = text.codePointAt(i) - 32;

				for (int y = 0; y < font.getFontImage().getH(); y++) {
					for (int x = 0; x < font.getWidths()[unicode]; x++) {
						if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
								+ y * font.getFontImage().getW()] == 0xffffffff) {
							setPixel(x + offX + offset - halfOffset, y + offY, color);
						}
					}
				}

				offset += font.getWidths()[unicode];

			}
			
			
		}
	}

	public void drawLight(Light l, int x, int y, boolean force) {
		if (force) {
			lightRequests.add(new LightRequest(l, x, y));
		} else if (lightRequests.size() < 10) {
			lightRequests.add(new LightRequest(l, x, y));
		}
	}

	public void drawLightRequest(Light l, int offX, int offY) {
		offX -= camX;
		offY -= camY;

		for (int i = 0; i < l.getDiameter(); i++) {
			drawLightLine(l, l.getRadius(), l.getRadius(), i, 0, offX, offY);
			drawLightLine(l, l.getRadius(), l.getRadius(), i, l.getDiameter(), offX, offY);
			drawLightLine(l, l.getRadius(), l.getRadius(), 0, i, offX, offY);
			drawLightLine(l, l.getRadius(), l.getRadius(), l.getDiameter(), i, offX, offY);
		}
	}

	public void drawLightLine(Light l, int x0, int y0, int x1, int y1, int offX, int offY) {
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

		int err = dx - dy;
		int e2;

		while (true) {

			int screenX = x0 - l.getRadius() + offX;
			int screenY = y0 - l.getRadius() + offY;

			if (screenX < 0 || screenX >= pW || screenY < 0 || screenY >= pH)
				return;

			int lightColor = l.getLightValue(x0, y0);
			if (lightColor == 0)
				return;

			if (lb[screenX + screenY * pW] == Light.FULL) {
				return;
			}

			setLightMap(screenX, screenY, lightColor);

			if (x0 == x1 && y0 == y1) {
				break;
			}

			e2 = 2 * err;

			if (e2 > -1 * dy) {
				err -= dy;
				x0 += sx;
			}

			if (e2 < dx) {
				err += dx;
				y0 += sy;
			}
		}
	}

	public void setLightMap(int x, int y, int value) {

		if (x < 0 || x >= pW || y < 0 || y >= pH) {
			return;
		}

		int baseColor = lm[x + y * pW];

		int maxRed = Math.max((baseColor >> 16) & 0xff, (value >> 16) & 0xff);
		int maxGreen = Math.max((baseColor >> 8) & 0xff, (value >> 8) & 0xff);
		int maxBlue = Math.max(baseColor & 0xff, value & 0xff);

		lm[x + y * pW] = (maxRed << 16 | maxGreen << 8 | maxBlue);
	}

	public void setLightBlock(int x, int y, int value) {

		if (x < 0 || x >= pW || y < 0 || y >= pH) {
			return;
		}

		if (zb[x + y * pW] > zDepth) {
			return;
		}
		if (lb[x + y * pW] == Light.NONE)
			lb[x + y * pW] = value;

	}

	public void process() {
		processing = true;

		Collections.sort(imageRequests, new Comparator<ImageRequest>() {

			@Override
			public int compare(ImageRequest o1, ImageRequest o2) {
				if (o1.zDepth < o2.zDepth) {
					return -1;
				}
				if (o1.zDepth > o2.zDepth) {
					return 1;
				}
				return 0;
			}

		});

		for (int i = 0; i < imageRequests.size(); i++) {
			ImageRequest ir = imageRequests.get(i);
			zDepth = ir.zDepth;
			drawImage(ir.image, ir.offX += camX, ir.offY += camY);
		}

		for (int i = 0; i < lightRequests.size(); i++) {
			LightRequest l = lightRequests.get(i);
			this.drawLightRequest(l.light, l.x, l.y);
		}

		for (int i = 0; i < p.length; i++) {
			float r = ((lm[i] >> 16) & 0xff) / 255f;
			float g = ((lm[i] >> 8) & 0xff) / 255f;
			float b = (lm[i] & 0xff) / 255f;

			p[i] = ((int) (((p[i] >> 16) & 0xff) * r) << 16 | (int) (((p[i] >> 8) & 0xff) * g) << 8
					| (int) ((p[i] & 0xff) * b));
		}

		imageRequests.clear();
		lightRequests.clear();
		processing = false;
	}

	public void drawImage(Image image, int x, int y) {
		x -= camX;
		y -= camY;

		if (image.isAlpha() && !processing) {
			imageRequests.add(new ImageRequest(image, zDepth, x, y));
			return;
		}

		// OFF SCREEN CODE
		if (x < -image.getW())
			return;
		if (y < -image.getH())
			return;
		if (x >= pW)
			return;
		if (y >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		// CLIPPING CODE
		if (x < 0) {
			newX -= x;
		}
		if (y < 0) {
			newY -= y;
		}
		if (newWidth + x > pW) {
			newWidth -= newWidth + x - pW;
		}
		if (newHeight + y > pH) {
			newHeight -= newHeight + y - pH;
		}

		for (int yy = newY; yy < newHeight; yy++) {
			for (int xx = newX; xx < newWidth; xx++) {
				setPixel(xx + x, yy + y, image.getP()[xx + yy * image.getW()]);
				if (image.getNormalMap() == null) {
					if (((image.getP()[xx + yy * image.getW()] >> 24) & 0xff) == 255) {
						setLightBlock(xx + x, yy + y, image.getLightBlock());
					}
				}
			}
		}

		if (image.hasNormalMap()) {

			for (int yy = newY; yy < newHeight; yy++) {
				for (int xx = newX; xx < newWidth; xx++) {
					if (image.getNormalMap().getP()[xx + yy * image.getNormalMap().getW()] == 0xff000000) {
						setLightBlock(xx + x, yy + y, Light.FULL);
					} else {
						setLightBlock(xx + x, yy + y, Light.NONE);
					}
				}
			}
		}

	}

	public void drawImageUI(Image image, int x, int y) {

		if (image.isAlpha() && !processing) {
			imageRequests.add(new ImageRequest(image, zDepth, x, y));
			return;
		}

		// OFF SCREEN CODE
		if (x < -image.getW())
			return;
		if (y < -image.getH())
			return;
		if (x >= pW)
			return;
		if (y >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		// CLIPPING CODE
		if (x < 0) {
			newX -= x;
		}
		if (y < 0) {
			newY -= y;
		}
		if (newWidth + x > pW) {
			newWidth -= newWidth + x - pW;
		}
		if (newHeight + y > pH) {
			newHeight -= newHeight + y - pH;
		}

		for (int yy = newY; yy < newHeight; yy++) {
			for (int xx = newX; xx < newWidth; xx++) {
				setPixel(xx + x, yy + y, image.getP()[xx + yy * image.getW()]);
				if (image.getNormalMap() != null) {
					// System.out.println(image.getNormalMap().getRGB(xx, yy));

					if (((image.getP()[xx + yy * image.getW()] >> 24) & 0xff) == 255) {
						setLightBlock(xx + x, yy + y, image.getLightBlock());
					}
				}
			}
		}
	}

	public void drawImage(Image image, int x, int y, int scale) {
		x -= camX;
		y -= camY;

		if (image.isAlpha() && !processing) {
			imageRequests.add(new ImageRequest(image, zDepth, x, y));
			return;
		}

		// OFF SCREEN CODE
		if (x < -image.getW())
			return;
		if (y < -image.getH())
			return;
		if (x >= pW)
			return;
		if (y >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		// CLIPPING CODE
		if (x < 0) {
			newX -= x;
		}
		if (y < 0) {
			newY -= y;
		}
		if (newWidth + x > pW) {
			newWidth -= newWidth + x - pW;
		}
		if (newHeight + y > pH) {
			newHeight -= newHeight + y - pH;
		}

		for (int yy = newY; yy < newHeight; yy++) {
			for (int xx = newX; xx < newWidth; xx++) {
				for (int i = 0; i < scale; i++) {
					setPixel(xx * scale + x + i, yy * scale + y + i, image.getP()[xx + yy * image.getW()]);
					if (image.getNormalMap() != null) {
						// System.out.println(image.getNormalMap().getRGB(xx, yy));

						if (((image.getP()[xx + yy * image.getW()] >> 24) & 0xff) == 255) {
							setLightBlock(xx * scale + x + i, yy * scale + i + y, image.getLightBlock());
						}
					}
				}
			}
		}
	}

	public void drawImageUI(Image image, int x, int y, int scale) {

		if (image.isAlpha() && !processing) {
			imageRequests.add(new ImageRequest(image, zDepth, x, y));
			return;
		}

		// OFF SCREEN CODE
		if (x < -image.getW())
			return;
		if (y < -image.getH())
			return;
		if (x >= pW)
			return;
		if (y >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		// CLIPPING CODE
		if (x < 0) {
			newX -= x;
		}
		if (y < 0) {
			newY -= y;
		}
		if (newWidth + x > pW) {
			newWidth -= newWidth + x - pW;
		}
		if (newHeight + y > pH) {
			newHeight -= newHeight + y - pH;
		}

		for (int yy = newY; yy < newHeight; yy++) {
			for (int xx = newX; xx < newWidth; xx++) {
				for (int i = 0; i < scale; i++) {
					for (int j = 0; j < scale; j++) {
						setPixel(xx * scale + x + i, yy * scale + y + j, image.getP()[xx + yy * image.getW()]);
						if (image.getNormalMap() != null) {
							// System.out.println(image.getNormalMap().getRGB(xx, yy));

							if (((image.getP()[xx + yy * image.getW()] >> 24) & 0xff) == 255) {
								setLightBlock(xx * scale + x + i, yy * scale + j + y, image.getLightBlock());
							}
						}
					}
				}
			}
		}
	}

	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		offX -= camX;
		offY -= camY;

		if (image.isAlpha() && !processing) {
			imageRequests.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, offX, offY));
			return;
		}

		// OFF SCREEN CODE
		if (offX < -image.getTileW())
			return;
		if (offY < -image.getTileH())
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getTileW();
		int newHeight = image.getTileH();

		// CLIPPING CODE
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY > pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY,
						image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
				setLightBlock(x + offX, y + offY, image.getLightBlock());

			}
		}
	}

	public void fillRect(int offX, int offY, int width, int height, int color, int lightblock) {
		offX -= camX;
		offY -= camY;

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				setPixel(x + offX, y + offY, color);
				setLightBlock(x + offX, y + offY, lightblock);
			}
		}
	}

	public void fillRectUI(int offX, int offY, int width, int height, int color, int lightblock) {

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				setPixel(x + offX, y + offY, color);
				setLightBlock(x + offX, y + offY, lightblock);
			}
		}
	}

	public void drawRect(int offX, int offY, int width, int height, int color, int lightblock) {
		offX -= camX;
		offY -= camY;

		for (int y = 0; y <= height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}

		for (int x = 0; x < width; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				setLightBlock(x + offX, y + offY, lightblock);
			}
		}
	}

	public void drawRectUI(int offX, int offY, int width, int height, int color, int lightblock) {

		for (int y = 0; y <= height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}

		for (int x = 0; x < width; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				setLightBlock(x + offX, y + offY, lightblock);
			}
		}
	}

	public void drawRect(Rectangle rectangle, int color) {
		int offX = rectangle.x;
		int offY = rectangle.y;

		int width = rectangle.width;
		int height = rectangle.height;
		offX -= camX;
		offY -= camY;

		for (int y = 0; y <= height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}

		for (int x = 0; x < width; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				setLightBlock(x + offX, y + offY, Light.NONE);
			}
		}
	}

	public void clear() {

		for (int i = 0; i < p.length; i++) {
			p[i] = dayColor;
			zb[i] = 0;
			lm[i] = ambientColor;
			lb[i] = 0;
		}

	}

	public int getzDepth() {
		return zDepth;
	}

	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}

	public int getCamX() {
		return camX;
	}

	public void setCamX(int camX) {
		this.camX = camX;
	}

	public int getCamY() {
		return camY;
	}

	public void setCamY(int camY) {
		this.camY = camY;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
