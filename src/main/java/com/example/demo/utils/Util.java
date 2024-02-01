package com.example.demo.utils;

import java.util.regex.Pattern;

public class Util {
	private static final Pattern VALID_DATE = 
			Pattern.compile("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|"
			+ "(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$");
	
	public static boolean isValidDate(String date) {
		return VALID_DATE.matcher(date).matches();
	}
}
