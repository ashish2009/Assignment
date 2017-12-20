package com.plivo.sendMessage.Improvement_1;

import org.testng.annotations.Test;
import com.plivo.request.SendTextMessage;

public class SendMessageTest {
	
	@Test
	public void verifySMSDelivery(String s){
		new SendTextMessage()
		.sendMessage(s)
		.getMessage()
		.verifyMessageState()
		.verifyFromNumber()
		.verifyToNumber()
		.verify();
	}
	
}
