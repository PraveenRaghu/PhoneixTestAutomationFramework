package com.api.services;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import io.restassured.response.Response;

public class UserService {

	private static final String USER_DETAILS = "/userdetails";
	
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	public Response userDetails(Role role) {
		LOGGER.info("Making request to the {} for the role {}",USER_DETAILS,role);
	Response response=	given().spec(requestSpecWithAuth(role))

				.when().get(USER_DETAILS);
	
	return response;
		
	}

}
