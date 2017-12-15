package com.plivo.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {

	private String path;
	private String authId;
	private String smsType;
	
	public GetRequest(String authId,String smsType){
		this.authId = authId;
		this.smsType = smsType;
	}
	
	public GetRequest setPath(){
		this.path="/"+authId+"/"+smsType;
		return this;
	}
	
	public GetRequest setPath(String id){
		this.path="/"+authId+"/"+smsType+"/"+id;
		return this;
	}
	
	
	public Response getRequest(RequestSpecification spec){
		Response response = spec.get(path).then()
				.statusCode(200).extract().response();
		return response;
	}
	
	public String getStringParameter(Response r,String path){
		String param = (String) r.body()
				.jsonPath().get(path);
		return param;
	}
}
