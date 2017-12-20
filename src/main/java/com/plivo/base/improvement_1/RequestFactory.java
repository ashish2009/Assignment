package com.plivo.base.improvement_1;

import java.util.HashMap;

import com.plivo.base.BuildPayload;
import com.plivo.util.PropertyReader;

public class RequestFactory {
	static ApiBase request;
	static SendRequest sendRequest;
	static GetRequest getRequest;
	static BuildPayload load;
	static HashMap<String, String> prop = PropertyReader.getProperties();
	
	public static ApiBase init(String type){
		if(type.equals(Request.POST)){
			request = new SendRequest(prop.get("authId"), prop.get("authToken"));
		}else if(type.equals(Request.GET)){
			request = new GetRequest(prop.get("authId"), prop.get("authToken"));
		}else{
			request = null;
		}
		
		request.setHeader(prop.get("headers"))
		.setURI(prop.get("URI"), prop.get("basePath"))
		.setType(prop.get("smsType"));
		return request;
	}

}
