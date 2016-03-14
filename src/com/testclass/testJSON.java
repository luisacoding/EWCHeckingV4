package com.testclass;

import com.alibaba.fastjson.JSON;
import com.baseclass.*;
public class testJSON {
	public static void main(String args[]){
		Group group = new Group();
		group.setId(0L);
		group.setName("admin");

		User guestUser = new User();
		guestUser.setId(2L);
		guestUser.setName("guest");

		User rootUser = new User();
		rootUser.setId(3L);
		rootUser.setName("root");

		group.addUser(guestUser);
		group.addUser(rootUser);
		
		// object to JSON
		String jsonString = JSON.toJSONString(group);
		System.out.println(jsonString);
		
		// JSON to object 
		Group jto = JSON.parseObject(jsonString, Group.class);
		System.out.println(jto);
		
		
	}

}
