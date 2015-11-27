package in.co.sunrays.util;

import in.co.sunrays.ocha.bean.DropdownList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//HTML Utility class to produce HTML contents like Dropdown List.

public class HTMLUtility {

	// Create HTML SELECT list from MAP paramters values

	public static String getList(String name, String selectedVal,
			HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val
						+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	// Create HTML SELECT List from List parameter

	public static String getList(String name, String selectedVal, List list) {

		Collections.sort(list);

		List<DropdownList> dd = (List<DropdownList>) list;

		StringBuffer sb = new StringBuffer(
				"<select class='form-control' name='" + name + "'>");

		String key = null;
		String val = null;

		for (DropdownList obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val
						+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

}
