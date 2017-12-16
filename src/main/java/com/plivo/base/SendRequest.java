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
	
	/*
	 * This method will send post request with request specification
	 * and payload and it will return reponse of request.
	 * We can check status code here so that it exctract response
	 * when given status code is matched.But for time being not
	 * using here to log each response so I can see errors.
	 * I checked status code in Get request.
	 */
	
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
	
	/*
	 * This method will create path for Get request based on 
	 * type of request and auth.Auth Id should be specified in
	 * request as per API doc.With this path we can get all
	 * messages
	 */
	
	public SendRequest setPath(){
		this.path = "/"+authId+"/"+smsType+"/";
		return this;
	}
	
	/*
	 * This method will set payload so it can be used with
	 * Post request
	 */
	
	public SendRequest setPayload(String payload){
		this.payload=payload;
		return this;
	}
	
	/*
	 * This method will extact parameter value as list from response
	 * with given jsonPath.There will key in json has string or list
	 * So based on data type we can create method to extract value
	 * from response.Ex-message uuid is stored in array.
	 */
	
	@SuppressWarnings("unchecked")
	public List<String> getListParameter(Response r,String path){
		List<String> param = (List<String>) r.body()
				.jsonPath().get(path);
		return param;
	}

}
