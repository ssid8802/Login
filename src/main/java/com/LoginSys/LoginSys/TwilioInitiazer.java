package com.LoginSys.LoginSys;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitiazer {

	private final com.LoginSys.LoginSys.Twilioproperties twilioproperties;

	@Autowired
	public TwilioInitiazer(Twilioproperties twilioproperties)
	{

		this.twilioproperties = twilioproperties;
	//	System.out.println(twilioproperties.getAccountSid()+"ssssssssssss");
		Twilio.init("AC6714886793825474e03fbaf68769f349", "58c7d648e159c6fd2f0f00c0ce2f017e");
		System.out.println("Twilio initialized with account-"+twilioproperties.getAccountSid());
	}
}
