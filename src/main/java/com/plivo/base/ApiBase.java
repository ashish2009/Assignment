package com.plivo.base;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class ApiBase {
	
	public RequestSpecification spec;
	
	public ApiBase setAuth(String authId,String token){
	    spec = given().auth().basic(authId, token);
	    return this;
	}
	
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
	
	public ApiBase setURI(String URI,String path){
		spec.baseUri(URI);
		spec.basePath(path);
		return this;
	}
	
	public RequestSpecification getSpec(){
		return spec;
	}
	
}
