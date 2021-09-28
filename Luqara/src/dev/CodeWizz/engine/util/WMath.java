package dev.CodeWizz.engine.util;

public class WMath {

	
	public static float clamb(float a, float max, float min) {
		if(a > max)
			return max;
		else if(a < min)
			return min;
		
		
		return a;
	}
	
	public static int clamb(int a, int max, int min) {
		if(a > max)
			return max;
		else if(a < min)
			return min;
		
		
		return a;
	}
	
	public static float distance(int x1, int y1, int x2, int y2) {
		return (float) Math.abs(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
	}
	
	public static float remap(float value, float oldmin, float oldmax, float newmin, float newmax) {
		return((value - oldmin) / (oldmax - oldmin)) * (newmax - newmin) + newmin;
	}
}