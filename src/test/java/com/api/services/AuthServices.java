package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class AuthServices {
	private static final String LOGIN_ENDPOINT ="/login";
	
	public Response login(Object userCredentials) {
	Response response=	given()
		.spec(requestSpec(userCredentials))
		//.body(userCredentials)
	    .when()
		.post(LOGIN_ENDPOINT);
	
	return response;
		
	}

}
