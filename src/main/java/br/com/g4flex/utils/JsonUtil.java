package br.com.g4flex.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
	public static String getJson(String[] header, List<Object[]> data, String reportname) {
		JSONObject obj = new JSONObject();
		obj.put("reportname", reportname);
		JSONArray jsarrayHeader = new JSONArray(header);
		JSONArray jsarrayData = new JSONArray(data);
		obj.put("header", jsarrayHeader);
		obj.put("data", jsarrayData);
		return obj.toString();
	}
}
