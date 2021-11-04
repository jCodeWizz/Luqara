package dev.CodeWizz.engine.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WDebug {

	public static String date;
	private static DateFormat dateFormat;
	
	
	public static void setupDate() {
		Date d = Calendar.getInstance().getTime();  
        dateFormat = new SimpleDateFormat("hh:mm:ss");  
        date = dateFormat.format(d);
	}
	
	public static void refreshDate() {
		Date d = Calendar.getInstance().getTime();  
		date = dateFormat.format(d);
	}
	
	
	
	
	public static void log(String text) {
		refreshDate();
		System.out.println("[" + date + "] " + text);
	}

	public static void log(int val) {
		refreshDate();
		System.out.println("[" + date + "] Value is: " + val);
	}

	public static void log(float val) {
		refreshDate();
		System.out.println("[" + date + "] Value is: " + val);
	}

	public static void log(boolean bool) {
		refreshDate();
		System.out.println("[" + date + "] Boolean is: " + bool);
	}

}
