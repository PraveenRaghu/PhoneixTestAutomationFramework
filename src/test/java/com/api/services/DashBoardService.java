package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	private static final String DETAILS_ENDPOINT ="/dashboard/details";
	
	private static final Logger LOGGER = LogManager.getLogger(DashBoardService.class);

	public Response  count(Role role) {
		LOGGER.info("Making reuest to the {} for the role {}",COUNT_ENDPOINT,role);
		return given()
		.spec(requestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
	}
	
	public Response countWithNoAuth() {
		LOGGER.info("Making reuest to the {} with no auth",COUNT_ENDPOINT);
		
		return given()
		.spec(requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
	}
	
	public Response details(Role role, Object payload) {
		LOGGER.info("Making reuest to the {} for the role {} with payload{}",DETAILS_ENDPOINT,role,payload);
		return given()
				.spec(requestSpecWithAuth(role))
				.when()
				.body(payload)
				.post(DETAILS_ENDPOINT);
	}

}
