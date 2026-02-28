package com.api.services;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import io.restassured.response.Response;

public class UserService {

	private static final String USER_DETAILS = "/userdetails";

	public Response userDetails(Role role) {

	Response response=	given().spec(requestSpecWithAuth(role))

				.when().get(USER_DETAILS);
	
	return response;
		
	}

}
