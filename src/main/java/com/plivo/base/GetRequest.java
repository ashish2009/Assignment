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
	
	/*
	 * This method will create path for Get request based on 
	 * type of request and auth.Auth Id should be specified in
	 * request as per API doc.With this path we can get all
	 * messages
	 */
	
	public GetRequest setPath(){
		this.path="/"+authId+"/"+smsType;
		return this;
	}
	
	/*
	 * This method will create path for Get request based on 
	 * type of request,auth and message uuid.Auth Id should be specified in
	 * request as per API doc.With this path we can get
	 * messages has specific message uuid
	 */
	
	public GetRequest setPath(String id){
		this.path="/"+authId+"/"+smsType+"/"+id;
		return this;
	}
	
	/*
	 * This method is used to send Get request based on request specification
	 * created in Api Base and path set using setPath method
	 */
	public Response getRequest(RequestSpecification spec){
		Response response = spec.get(path).then()
				.statusCode(200).extract().response();
		return response;
	}
	
	/*
	 * This method will extact parameter value as string from response
	 * with given jsonPath 
	 */
	
	public String getStringParameter(Response r,String path){
		String param = (String) r.body()
				.jsonPath().get(path);
		return param;
	}
}
