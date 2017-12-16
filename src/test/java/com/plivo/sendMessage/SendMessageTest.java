package com.plivo.sendMessage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.plivo.base.ApiBase;
import com.plivo.base.BuildPayload;
import com.plivo.base.GetRequest;
import com.plivo.base.SendRequest;
import com.plivo.requestObject.SendMessage;
import com.plivo.util.PropertyReader;

public class SendMessageTest {

	HashMap<String, String> prop = PropertyReader.getProperties();
	SoftAssert test = new SoftAssert();
	GetRequest getMessage;
	SendRequest sendMessage;
	RequestSpecification apiSpec;
	BuildPayload load;
	JSONObject payload;
	List<String> UUID;
	String messageUUID;

	@BeforeClass
	public void setupSendMessageTest() {
		apiSpec = new ApiBase()
				.setAuth(prop.get("authId"), prop.get("authToken"))
				.setHeader(prop.get("headers"))
				.setURI(prop.get("URI"), prop.get("basePath")).getSpec();
		load = new BuildPayload(prop.get("messagePayload"), SendMessage.class);
	}

	@Test
	public void verifySMSDelivery(String s){
		sendMessage(s);
		Response resp = getMessage();
		System.out.println(resp.asString());
		
		test.assertEquals(getMessage.getStringParameter(resp,"message_state"), "sent",
				"Expected message status is sent but found "+
		         getMessage.getStringParameter(resp,"message_state"));
		
		test.assertEquals(getMessage.getStringParameter(resp,"from_number"),
				String.valueOf(payload.getLong("src")),
				"Expected from_number is "+String.valueOf(payload.getLong("src"))+" but found "+
		         getMessage.getStringParameter(resp,"from_number"));
		
		test.assertEquals(getMessage.getStringParameter(resp,"to_number"),
				String.valueOf(payload.getLong("dst")),
				"Expected from_number is "+String.valueOf(payload.getLong("dst"))+" but found "+
		         getMessage.getStringParameter(resp,"to_number"));
		test.assertAll();
	}
		
	private void sendMessage(String s) {
		HashMap<String, String> input = load.inputPayload(s);
	    payload = load.setPayload().update(input);
		sendMessage = new SendRequest(prop.get("authId"),prop.get("smsType"));
		Response r = sendMessage.setPath().setPayload(payload.toString()).returnResp(apiSpec);
		System.out.println(r.asString());
		UUID = sendMessage.getListParameter(r, "message_uuid");
		messageUUID = UUID.get(0);
		System.out.println("UUID: "+UUID.get(0));
	}
	
	private Response getMessage() {
		getMessage = new GetRequest(prop.get("authId"),prop.get("smsType"));
		return getMessage.setPath(messageUUID).getRequest(apiSpec);
	}

}
