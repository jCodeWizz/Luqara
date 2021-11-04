package dev.CodeWizz.engine.gfx;

public class Font {

	public static final Font DETAILED = new Font("/assets/fonts/detailed.png", 95);
	public static final Font STANDARD = new Font("/assets/fonts/standard.png", 59);
	
	
	private Image fontImage;
	private int[] offsets;
	private int[] widths;
	private int size;
	
	public Font(String path, int size) {
		fontImage = new Image(path);
		
		this.size = size;
		
		offsets = new int[size];
		widths = new int[size];
		
		int unicode = 0;
		
		for(int i = 0; i < fontImage.getW(); i++) {
			if(fontImage.getP()[i] == 0xff0000ff) {
				offsets[unicode] = i;
			}
			
			if(fontImage.getP()[i] == 0xffffff00) {
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
		
	}
	
	public int getWidth(String text) {
		int o = 0;
		for(int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;
			o += getWidths()[unicode];
		}
		
		return o;
	}

	public Image getFontImage() {
		return fontImage;
	}

	public void setFontImage(Image fontImage) {
		this.fontImage = fontImage;
	}

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getWidths() {
		return widths;
	}

	public void setWidths(int[] widths) {
		this.widths = widths;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
