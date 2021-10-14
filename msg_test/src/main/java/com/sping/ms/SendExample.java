package com.sping.ms;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendExample {
   
	  public static void main(String[] args) {
		    String api_key = "NCSI2S3WUDOYORNN";
		    String api_secret = "FL3O4QN3K2X9BYZR5CSZXHH48LU21OAN";
		    Message coolsms = new Message(api_key, api_secret);
		    
		    
		    Random rand  = new Random();
	        String num = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            num+=ran;
	        }
	        
		    // 4 params(to, from, type, text) are mandatory. must be filled
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", "01040513806");	// 수신전화번호
		    params.put("from", "01099027725");	// 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		    params.put("type", "SMS");
		    params.put("text", "메롱");
		    params.put("app_version", "test app 1.2"); // application name and version

		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
		  }

}