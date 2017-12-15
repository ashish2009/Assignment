package com.plivo.base;

import java.util.List;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SendRequest extends ApiBase{

	private String payload;
	private String path;
	private String authId;
	private String smsType;
	private Response response;
	
	public SendRequest(String authId,String smsType){
		this.authId = authId;
		this.smsType = smsType;
	}
	
	public Response returnResp(RequestSpecification spec) {
		try {
			 response = spec.body(payload).when()
					    .post(path).then()
					    .extract().response();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	public SendRequest setPath(){
		this.path = "/"+authId+"/"+smsType+"/";
		return this;
	}
	
	public SendRequest setPayload(String payload){
		this.payload=payload;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListParameter(Response r,String path){
		List<String> param = (List<String>) r.body()
				.jsonPath().get(path);
		return param;
	}

}
