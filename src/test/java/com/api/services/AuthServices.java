package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;

import io.restassured.response.Response;

public class AuthServices {
	private static final String LOGIN_ENDPOINT ="/login";
	private static final Logger LOGGER = LogManager.getLogger(AuthServices.class);
	
	public Response login(Object userCredentials)
	
	{
		
		LOGGER.info("Making login Request for the payload{}",((UserCredentials)userCredentials).username());
	Response response=	given()
			
			
		.spec(requestSpec(userCredentials))
		//.body(userCredentials)
	    .when()
		.post(LOGIN_ENDPOINT);
	
	return response;
		
	}

}
