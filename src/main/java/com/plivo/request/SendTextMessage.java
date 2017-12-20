package com.plivo.request;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;
import com.plivo.base.BuildPayload;
import com.plivo.base.improvement_1.GetRequest;
import com.plivo.base.improvement_1.Request;
import com.plivo.base.improvement_1.RequestFactory;
import com.plivo.base.improvement_1.SendRequest;
import com.plivo.requestObject.SendMessage;
import com.plivo.util.PropertyReader;

public class SendTextMessage {
	
	HashMap<String, String> prop = PropertyReader.getProperties();
	SoftAssert test = new SoftAssert();
	private SendRequest send;
	private GetRequest get;
	private BuildPayload load;
	private JSONObject payload;
	private List<String> UUID;
	private String messageUUID;
	private Response sendMessageResponse;
	private Response getMessageResponse;
	
	public SendTextMessage(){
		send = (SendRequest)RequestFactory.init(Request.POST);
		send.setPath();
		get = (GetRequest)RequestFactory.init(Request.GET);
		load = new BuildPayload(prop.get("messagePayload"), SendMessage.class);
	}
	
	public SendTextMessage sendMessage(String s) {
		HashMap<String, String> input = load.inputPayload(s);
	    payload = load.setPayload().update(input);
	    sendMessageResponse = send.setPayload(payload.toString()).returnResp();
		System.out.println(sendMessageResponse.asString());
		UUID = send.getParameter(sendMessageResponse, "message_uuid");
		messageUUID = UUID.get(0);
		System.out.println("UUID: "+UUID.get(0));
		return this;
	}
	
	public SendTextMessage getMessage() {
		if(messageUUID != null){
			getMessageResponse = get.setPath(messageUUID).returnResp();
			System.out.println(getMessageResponse.asString());
		}else{
			System.out.println("Message UUID is not valid");
		}
		return this;
	}
	
	public SendTextMessage verifyMessageState(){
		test.assertEquals(get.getParameter(getMessageResponse,"message_state"), "sent",
				"Expected message status is sent but found "+
		         get.getParameter(getMessageResponse,"message_state"));
		return this;
	}
	
    public SendTextMessage verifyFromNumber(){
    	test.assertEquals(get.getParameter(getMessageResponse,"from_number"),
				String.valueOf(payload.getLong("src")),
				"Expected from_number is "+String.valueOf(payload.getLong("src"))+" but found "+
		         get.getParameter(getMessageResponse,"from_number"));
    	return this;
	}
    
    public SendTextMessage verifyToNumber(){
    	test.assertEquals(get.getParameter(getMessageResponse,"to_number"),
				String.valueOf(payload.getLong("dst")),
				"Expected from_number is "+String.valueOf(payload.getLong("dst"))+" but found "+
		         get.getParameter(getMessageResponse,"to_number"));
    	return this;
	}
    
    public void verify(){
		test.assertAll();
	}

}
