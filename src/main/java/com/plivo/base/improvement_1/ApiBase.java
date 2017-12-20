package com.plivo.base.improvement_1;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

public abstract class ApiBase {
	
	public RequestSpecification spec;
	private String authId;
	private String smsType;
	public String path;
	
	public ApiBase(String authId,String token){
		this.authId = authId;
		spec = given().auth().basic(authId, token);
	}
	
	public ApiBase setType(String smsType){
		this.smsType = smsType;
		return this;
	}
		
	/*
	 * This method is used to set header and common for
	 * all request.There may be more then one header for request
	 * so headers set all headers,it should be specified in 
	 * config file with format like ex: header:value;header:value
	 */
	
	public ApiBase setHeader(String h){
		 HashMap<String, String> head = new HashMap<String, String>();
		 String[] headers = h.split(";");
		 for(int i=0;i<headers.length;i++){
			 String[] header = headers[i].split(":");
			 head.put(header[0], header[1]);
		 }
		 spec.headers(head);
		 return this;
	}
	
	/*
	 * This method will set base URI and base Path
	 * to spec which will be common for all request
	 * base URI and base path are specified in config.properties.
	 */
	
	public ApiBase setURI(String URI,String path){
		spec.baseUri(URI);
		spec.basePath(path);
		return this;
	}
	
	public ApiBase setPath(){
		path = "/"+authId+"/"+smsType+"/";
		return this;
	}
	
	public abstract Response returnResp();
	
	public abstract Object getParameter(Response r,String path);
	
}
