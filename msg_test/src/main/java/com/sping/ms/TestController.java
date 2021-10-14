package com.sping.ms;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	TestService TestService;
	

	@RequestMapping(value = "/message", method=RequestMethod.POST )
	@ResponseBody
	public String sendSMS(String phoneNumber) throws Exception{

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        TestService.certifiedPhoneNumber(phoneNumber,numStr);
        
        return numStr;
    }
	
	@RequestMapping(value = "/msg")
	public String smsForm() throws Exception{
		
		return "test/message";
	}
	
}
