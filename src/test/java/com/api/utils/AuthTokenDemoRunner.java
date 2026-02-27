package com.api.utils;

import com.api.constant.Role;

public class AuthTokenDemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	String token=	AuthTokenProvider.getToken(Role.FD);
	for (int i=0;i<10;i++) {
	System.out.println(token);
	}

	}

}
