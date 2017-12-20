package com.plivo.base.improvement_1;

import java.util.List;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SendRequest extends ApiBase{

	private String payload;
	private Response response;
	
	public SendRequest(String authId, String token) {
		super(authId, token);
	}
	
	/*
	 * This method will send post request with request specification
	 * and payload and it will return reponse of request.
	 * We can check status code here so that it exctract response
	 * when given status code is matched.But for time being not
	 * using here to log each response so I can see errors.
	 * I checked status code in Get request.
	 */
	
	public Response returnResp() {
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
	public List<String> getParameter(Response r,String path){
		List<String> param = (List<String>) r.body()
				.jsonPath().get(path);
		return param;
	}

}
