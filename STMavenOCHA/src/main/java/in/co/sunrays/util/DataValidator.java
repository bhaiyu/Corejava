package in.co.sunrays.util;

import in.co.sunrays.util.DataUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator {
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	public static boolean isInteger(String val) {
		//String number="[\\p{Digit} && [123456789]]+";
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				
				
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	public static boolean isName(String val) {

		String emailreg = "^[_A-Za-z]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}
	public static boolean isCorrectDate(String dob)
	{
	Date today=new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	boolean currectDate=false;
	try {
 
		Date date = formatter.parse(dob);
		System.out.println("Selected Date : " + formatter.format(date));
		System.out.println("Today Date : " + formatter.format(today));
		currectDate=date.before(today);
		System.out.println(currectDate);
		
 
	} catch (Exception e) {
		e.printStackTrace();
	}	
	return currectDate;

}
	
	public static void main(String[] args) {

		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
	}

}
