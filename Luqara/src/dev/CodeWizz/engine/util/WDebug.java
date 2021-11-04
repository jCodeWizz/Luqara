package dev.CodeWizz.engine.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WDebug {

	public static String date;
	
	
	
	public WDebug() {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  
        WDebug.date = dateFormat.format(date);
        
        System.out.println(WDebug.date);
	}
	
	
	public static void log(String text) {
		System.out.println("[" + date + "] " + text);
	}

	public static void log(int val) {

	}

	public static void log(float val) {

	}

	public static void log(boolean bool) {

	}

}
