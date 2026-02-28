package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	private static final String DETAILS_ENDPOINT ="/dashboard/details";

	public Response  count(Role role) {
		
		return given()
		.spec(requestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
	}
	
	public Response countWithNoAuth() {
		
		return given()
		.spec(requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
	}
	
	public Response details(Role role, Object payload) {
		return given()
				.spec(requestSpecWithAuth(role))
				.when()
				.body(payload)
				.post(DETAILS_ENDPOINT);
	}

}
