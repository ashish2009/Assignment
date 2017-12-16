package com.plivo.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildPayload {
	
	private Object data;
	private String file;
	private Class<?> c;
	
	public BuildPayload(String file,Class<?> c){
		this.file = file;
		this.c = c;
	}
	
	/*
	 * This method will map payload pojo with payload json
	 * which will be passed while creation of object of this
	 * class.
	 */
	
	public BuildPayload setPayload() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		
		try {
			InputStream input = new FileInputStream(file);
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, StandardCharsets.UTF_8);
			String theString = writer.toString();
			data = objectMapper.readValue(theString, c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/*
	 * This method will take string(one line) passed by dataProvider
	 * and stores it in Map so default pojo object can be updated
	 * with new value in map
	 */
	
	public HashMap<String, String> inputPayload(String s) {
		HashMap<String, String> a = new HashMap<String, String>();
		String[] load = s.split(";");
		for (int i = 0; i < load.length; i++) {
			String[] l = load[i].split(":");
			a.put(l[0], l[1]);
		}
		return a;
	}
	
	/*
	 * This method convert default pojo to map and update
	 * each key with new value and return JSON object
	 * with updated value.
	 */
	
	@SuppressWarnings("unchecked")
	public JSONObject update(HashMap<String, String> a) {
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, Object> map = objectMapper.convertValue(data, HashMap.class);
		for (String s : a.keySet()) {
			if(isNumberOrNot(a.get(s))){
				map.put(s,Long.parseLong(a.get(s)) );
			}else{
				map.put(s, a.get(s));	
			}
		}
		return new JSONObject(map);
	}
	
	/*
	 * This method check if string can be converted to long.
	 * There are parameter in paylod which is long.So it should
	 * be passed as long while creating JSON object of payload
	 */
	
	private boolean isNumberOrNot(String input)
	{
	    try
	    {
	        Long.parseLong(input);
	    }
	    catch(NumberFormatException ex)
	    {
	        return false;
	    }
	    return true;
	}

}
